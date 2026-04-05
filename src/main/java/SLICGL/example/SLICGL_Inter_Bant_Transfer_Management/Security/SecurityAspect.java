package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.sessionRepo;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class SecurityAspect {
    @Autowired
    HttpSession session;
    @Autowired
    JdbcTemplate template;
    @Autowired
    sessionRepo sessionRepository;
    @Autowired
    UserRepo UserRepository;
    @Autowired
    HttpServletRequest request;

    @Transactional
    @Around("@annotation(requiresPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, RequiresPermission requiresPermission) throws Throwable {
        // Get the userId received with http request
        Cookie[] cookies = request.getCookies();
        String token = null;
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Session_User")) {
                    userId = cookie.getValue();
                }
            }
            String Sql = "SELECT COUNT(*) > 0 FROM user_function uf WHERE uf.user_id = ? AND uf.function_id = ?";
            Integer count = template.queryForObject(
                    Sql,
                    Integer.class,
                    userId,
                    requiresPermission.value()
            );
            if (count == null || count == 0) {
                throw new SecurityException("Access Denied: No permission to access this function");
            }
            return joinPoint.proceed();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                    false,
                    "Unable to handle the request. No session details found. Please contact administrator",
                    false
            ));
        }
    }
}
