package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.sessionRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class sessionIMPL implements sessionService {
    @Autowired
    sessionRepo sessionRepository;

    @Override
    @LogActivity(methodDescription = "This method will confirms whether any active session is already available for provided user id for current date")
    public int isActiveSessionAvailable(String userId) {
        return sessionRepository.isActiveSessionAvailable(userId, LocalDate.now());
    }

    @Override
    @LogActivity(methodDescription = "This method will confirms whether any active session is already available for provided user id and token for current date")
    public int isActiveSessionAvailableForGivenUserIdAndToken(String userId, LocalDate currentDateTime, String token) {
        return sessionRepository.isActiveSessionAvailableForGivenUserIdAndToken(userId, currentDateTime, token);
    }

    @Override
    @Transactional
    @LogActivity(methodDescription = "This method will save a new session into the database session table")
    public void saveNewSession(LocalDateTime createdDate, LocalDateTime lastActivityAt, String ipAddress, String status, String token, User user) {
        sessionRepository.save(new Session(
                token,
                ipAddress,
                createdDate,
                lastActivityAt,
                null,
                status,
                user
        ));
    }

    @Override
    @Transactional
    @LogActivity(methodDescription = "This method will update the session table, session as expired")
    public int expireSession(String token, String userId) {
        return sessionRepository.invalidateExistingSession(token, userId);
    }

    @Override
    @Transactional
    @LogActivity(methodDescription = "This method will update the list of sessions in session table as session is expired")
    public int expireSessionList(String token) {
        return sessionRepository.scheduledSessionExpiration(token);
    }

    @Override
    public List<String> getExpiredSessionList() {
        return sessionRepository.getExpiredSessionList();
    }

    @Override
    @Transactional
    public int updateLastActivity(String token, String userId) {
        return sessionRepository.updateLastActivity(token, userId);
    }

    @Override
    public void clearCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
