package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface sessionService {
    public int isActiveSessionAvailable(String userId);

    public void saveNewSession(LocalDateTime createdDate, LocalDateTime lastActivityAt, String ipAddress, String status, String token, User user);

    public int expireSession(String token, String userId);

    public List<String> getExpiredSessionList();

    public int updateLastActivity(String token, String userId);

    public void clearCookie(HttpServletResponse response, String cookieName);

    public int expireSessionList(String token);

    public int isActiveSessionAvailableForGivenUserIdAndToken(String userId, LocalDate currentDateTime, String token);
}
