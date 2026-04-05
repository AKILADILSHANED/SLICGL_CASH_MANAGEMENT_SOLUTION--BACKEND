package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.CookiesNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.SessionUserNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestUtil {
    @Autowired
    private HttpServletRequest request;

    public String getClientIP() {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            ipAddress = ipAddress.split(",")[0].trim();
        } else {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public String getSessionUser() {
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Session_User")) {
                    userId = cookie.getValue();
                    break;
                }
            }
            if (userId != null && !userId.isEmpty()) {
                return userId;
            } else {
                throw new SessionUserNotFoundException("No session user found. Please contact administrator");
            }
        } else {
            throw new CookiesNotFoundException("No session cookies found. Please contact administrator");
        }
    }
}
