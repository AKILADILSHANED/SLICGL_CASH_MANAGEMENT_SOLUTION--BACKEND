package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.SessionValidationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.TokenNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.sessionIMPL;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Aspect
@Component
@Order(2)
public class SessionAspect {
    @Autowired
    HttpSession httpSession;
    @Autowired
    sessionIMPL sessionIMPL;
    @Autowired
    UserRepo UserRepository;
    @Autowired
    HttpServletRequest request;

    @Transactional
    @Around("@annotation(session)")
    public Object checkSession(ProceedingJoinPoint joinPoint, Session session) throws Throwable {
        // check whether any active session available in database that needs to be expired
        List<String> sessionTokenList = sessionIMPL.getExpiredSessionList();
        if (!sessionTokenList.isEmpty()) {
            for (String token : sessionTokenList) {
                sessionIMPL.expireSessionList(token);
            }
        }
        // Get the token and userId received with http request
        Cookie[] cookies = request.getCookies();
        String token = null;
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Session_Token")) {
                    token = cookie.getValue();
                }
                if (cookie.getName().equals("Session_User")) {
                    userId = cookie.getValue();
                }
            }
            if (token != null || !token.isEmpty() || userId != null || !userId.isEmpty()) {
                // First check with session table, whether the relevant session token is already expired or not
                int availability = sessionIMPL.isActiveSessionAvailableForGivenUserIdAndToken(userId, LocalDate.now(), token);
                if (availability == 1) {
                    // Update last_active_at column in session table to current time stamp
                    int affectedRows = sessionIMPL.updateLastActivity(token, userId);
                    if (affectedRows == 0) {
                        throw new SessionValidationException("Failed to update Session Table on last_activity_at column");
                    } else {
                        return joinPoint.proceed();
                    }
                } else {
                    throw new SecurityException("Session Expired. Please Re-login to the system");
                }
            } else {
                throw new TokenNotFoundException("Request cannot complete due to unavailability of valid session token or session user");
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                    false,
                    "Unable to handle the request. No session details found. Please contact administrator",
                    false
            ));
        }
    }
}
