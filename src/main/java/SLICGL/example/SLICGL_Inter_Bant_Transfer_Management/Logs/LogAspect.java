package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    @Autowired(required = false)
    HttpSession session;

    @Autowired(required = false)
    HttpRequestUtil httpRequest;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private static final List<String> SENSITIVE_FIELDS = Arrays.asList(
            "password", "userPassword", "confirmPassword", "currentPassword", "newPassword"
    );

    private String serializeParams(Object[] params, ObjectMapper prettyMapper) {
        try {
            Object[] sanitized = Arrays.stream(params)
                    .map(param -> {
                        if (param instanceof HttpServletRequest) {
                            return "[HttpServletRequest - Excluded]";
                        }
                        if (param instanceof HttpServletResponse) {
                            return "[HttpServletResponse - Excluded]";
                        }
                        try {
                            String json = prettyMapper.writeValueAsString(param);
                            com.fasterxml.jackson.databind.node.ObjectNode node =
                                    (com.fasterxml.jackson.databind.node.ObjectNode)
                                            prettyMapper.readTree(json);
                            // Iterate over sensitive field names and mask if present
                            for (String sensitiveField : SENSITIVE_FIELDS) {
                                if (node.has(sensitiveField)) {
                                    node.put(sensitiveField, "*** MASKED ***");
                                }
                            }
                            return prettyMapper.writeValueAsString(node);
                        } catch (Exception e) {
                            return "[Unserializable Param: " + param.getClass().getSimpleName() + "]";
                        }
                    })
                    .toArray();
            return prettyMapper.writeValueAsString(sanitized);
        } catch (Exception e) {
            return "[Param serialization failed]";
        }
    }

    /**
     * Check if there's an active web request context
     */
    private boolean hasActiveRequestContext() {
        try {
            RequestContextHolder.currentRequestAttributes();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    /**
     * Get session user safely - returns "System/Background" for scheduled tasks
     */
    private String getSessionUser() {
        if (!hasActiveRequestContext()) {
            return "System/Background";
        }

        try {
            return httpRequest.getSessionUser();
        } catch (Exception e) {
            logger.debug("Could not retrieve session user: {}", e.getMessage());
        }
        return "Anonymous";
    }

    /**
     * Get client IP safely - returns "N/A" for scheduled tasks
     */
    private String getClientIP() {
        if (!hasActiveRequestContext()) {
            return "N/A (Background Task)";
        }

        try {
            if (httpRequest != null) {
                return httpRequest.getClientIP();
            }
        } catch (Exception e) {
            logger.debug("Could not retrieve client IP: {}", e.getMessage());
        }
        return "Unknown";
    }

    /**
     * Log for background/scheduled tasks (no HTTP context)
     */
    private void logBackgroundTask(String action, LogActivity logActivity,
                                   Object[] params, ObjectMapper prettyMapper,
                                   String status, String message, Object result) throws Exception {
        String logMessage = "\n" + "-".repeat(90) + "\n" +
                "Log Date: " + LocalDate.now() + "\n" +
                "Log Time: " + LocalTime.now() + "\n" +
                "Context: BACKGROUND TASK" + "\n" +
                "User: System/Background" + "\n" +
                "Action: " + action + "\n" +
                "Status: " + status + "\n" +
                "Description: " + logActivity.methodDescription() + "\n" +
                "Message: " + message + "\n" +
                "Params: " + serializeParams(params, prettyMapper) + "\n" +
                "Return Result: " + (result != null ?
                (result instanceof String ? result : prettyMapper.writeValueAsString(result)) : "null") + "\n" +
                "IP Address: N/A (Background Task)" + "\n" +
                "-".repeat(90);

        if ("Failed".equals(status)) {
            logger.error(logMessage);
        } else {
            logger.info(logMessage);
        }
    }

    @Around("@annotation(logActivity)")
    public Object logActivity(ProceedingJoinPoint joinPoint, LogActivity logActivity) throws Throwable {
        String action = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();

        ObjectMapper prettyMapper = objectMapper.copy();
        prettyMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Check if we're in a web request context
        boolean hasRequestContext = hasActiveRequestContext();

        if (!hasRequestContext) {
            // Handle background/scheduled tasks
            try {
                logger.info("Executing background task: {}", action);
                logBackgroundTask(action, logActivity, params, prettyMapper, "Started", "N/A", null);

                Object result = joinPoint.proceed();

                logBackgroundTask(action, logActivity, params, prettyMapper, "Completed", "Success", result);
                return result;
            } catch (Exception e) {
                logBackgroundTask(action, logActivity, params, prettyMapper, "Failed", e.getMessage(), null);
                throw e;
            }
        }

        // Handle web request context
        String sessionUser = getSessionUser();
        String ipAddress = getClientIP();

        logger.info("\n" + "-".repeat(90) + "\n" +
                "Log Date: " + LocalDate.now() + "\n" +
                "Log Time: " + LocalTime.now() + "\n" +
                "User: " + sessionUser + "\n" +
                "Action: " + action + "\n" +
                "Status: " + "Started" + "\n" +
                "Description: " + logActivity.methodDescription() + "\n" +
                "Message: " + "N/A" + "\n" +
                "Params: " + serializeParams(params, prettyMapper) + "\n" +
                "Return Result: " + "null" + "\n" +
                "IP Address: " + ipAddress + "\n" +
                "-".repeat(90));

        try {
            Object result = joinPoint.proceed();
            if (result instanceof ResponseEntity) {
                ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
                Object body = responseEntity.getBody();
                if (body instanceof customAPIResponse) {
                    customAPIResponse<?> apiResponse = (customAPIResponse<?>) body;
                    if (apiResponse.isSuccess()) {
                        logger.info("\n" + "-".repeat(90) + "\n" +
                                "Log Date: " + LocalDate.now() + "\n" +
                                "Log Time: " + LocalTime.now() + "\n" +
                                "User: " + sessionUser + "\n" +
                                "Action: " + action + "\n" +
                                "Status: " + "Succeed" + "\n" +
                                "Description: " + logActivity.methodDescription() + "\n" +
                                "Message: " + apiResponse.getMessage() + "\n" +
                                "Params: " + serializeParams(params, prettyMapper) + "\n" +
                                "Return Result: " + (apiResponse.getResponseObject() != null ?
                                prettyMapper.writeValueAsString(apiResponse.getResponseObject()) : "null") + "\n" +
                                "IP Address: " + ipAddress + "\n" +
                                "-".repeat(90));
                        return result;
                    } else {
                        logger.error("\n" + "-".repeat(90) + "\n" +
                                "Log Date: " + LocalDate.now() + "\n" +
                                "Log Time: " + LocalTime.now() + "\n" +
                                "User: " + sessionUser + "\n" +
                                "Action: " + action + "\n" +
                                "Status: " + "Failed" + "\n" +
                                "Description: " + logActivity.methodDescription() + "\n" +
                                "Message: " + apiResponse.getMessage() + "\n" +
                                "Params: " + serializeParams(params, prettyMapper) + "\n" +
                                "Return Result: " + (apiResponse.getResponseObject() != null ?
                                prettyMapper.writeValueAsString(apiResponse.getResponseObject()) : "null") + "\n" +
                                "IP Address: " + ipAddress + "\n" +
                                "-".repeat(90));
                        return result;
                    }
                } else {
                    return result;
                }
            } else {
                logger.info("\n" + "-".repeat(90) + "\n" +
                        "Log Date: " + LocalDate.now() + "\n" +
                        "Log Time: " + LocalTime.now() + "\n" +
                        "User: " + sessionUser + "\n" +
                        "Action: " + action + "\n" +
                        "Status: " + "Executed" + "\n" +
                        "Description: " + logActivity.methodDescription() + "\n" +
                        "Message: " + "N/A" + "\n" +
                        "Params: " + serializeParams(params, prettyMapper) + "\n" +
                        "Return Result: " + result + "\n" +
                        "IP Address: " + ipAddress + "\n" +
                        "-".repeat(90));
                return result;
            }
        } catch (Exception e) {
            logger.error("\n" + "-".repeat(90) + "\n" +
                    "Log Date: " + LocalDate.now() + "\n" +
                    "Log Time: " + LocalTime.now() + "\n" +
                    "User: " + sessionUser + "\n" +
                    "Action: " + action + "\n" +
                    "Status: " + "Failed" + "\n" +
                    "Description: " + logActivity.methodDescription() + "\n" +
                    "Message: " + e.getMessage() + "\n" +
                    "Params: " + serializeParams(params, prettyMapper) + "\n" +
                    "Return Result: " + "null" + "\n" +
                    "IP Address: " + ipAddress + "\n" +
                    "-".repeat(90));
            throw e;
        }
    }
}