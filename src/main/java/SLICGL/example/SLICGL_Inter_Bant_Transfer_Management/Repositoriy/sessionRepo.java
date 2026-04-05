package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface sessionRepo extends JpaRepository<Session, String> {
    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM session ss WHERE ss.session_status = 'Active' AND ss.session_user = ?1 AND DATE(ss.created_at) = ?2) THEN true ELSE false END", nativeQuery = true)
    public int isActiveSessionAvailable(String userId, LocalDate currentDateTime);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM session ss WHERE ss.session_status = 'Active' AND ss.session_user = ?1 AND DATE(ss.created_at) = ?2) THEN true ELSE false END", nativeQuery = true)
    public int isActiveSessionAvailableForGivenUserIdAndToken(String userId, LocalDate currentDateTime, String token);

    @Modifying
    @Query(value = "UPDATE session ss SET ss.session_status = 'Expired', ss.expires_at = CURRENT_TIMESTAMP(6) WHERE ss.session_token = ?1 AND ss.session_user = ?2", nativeQuery = true)
    public int invalidateExistingSession(String token, String userId);

    @Modifying
    @Query(value = "UPDATE session ss SET ss.session_status = 'Expired', ss.expires_at = CURRENT_TIMESTAMP(6) WHERE ss.session_token = ?1", nativeQuery = true)
    public int scheduledSessionExpiration(String token);

    @Query(value = "SELECT ss.session_token FROM session ss WHERE ss.session_status = 'Active' AND ss.last_activity_at + INTERVAL 15 MINUTE < CURRENT_TIMESTAMP", nativeQuery = true)
    public List<String> getExpiredSessionList();

    @Modifying
    @Query(value = "UPDATE session ss SET ss.last_activity_at = CURRENT_TIMESTAMP(6) WHERE ss.session_token = ?1 AND ss.session_user = ?2", nativeQuery = true)
    public int updateLastActivity(String token, String userId);
}
