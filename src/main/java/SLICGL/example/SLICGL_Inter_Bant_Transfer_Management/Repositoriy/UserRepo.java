package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user WHERE user_email = ?1 AND user_active_status = 1", nativeQuery = true)
    public User getUserFromEmailAndPassword(String userEmail);

    @Query(value = "SELECT user_id from user ORDER BY user_created_date DESC LIMIT 1", nativeQuery = true)
    public String getLastUserId();

    @Modifying
    @Query(value = "UPDATE User SET user_active_status = 0, User_Deleted_By = ?1 WHERE user_id = ?2", nativeQuery = true)
    public int deleteUser(String deletedUser, String userId);

    @Query(value = "SELECT * FROM User WHERE user_email = ?1 AND user_active_status = 1", nativeQuery = true)
    public User getPasswordResetUser(String email);

    @Query(value = "SELECT user_password FROM user WHERE user_email = ?1 AND user_active_status = 1", nativeQuery = true)
    public String getEncryptedPassword(String email);

    @Modifying
    @Query(value = "UPDATE User SET user_password = ?1, is_first_login = 0 WHERE user_email = ?2 AND user_active_status = 1", nativeQuery = true)
    public int passwordChange(String changedPassword, String email);

    @Modifying
    @Query(value = "UPDATE user SET login_attempts = 0 WHERE user_email = ?1 AND user_password = ?2", nativeQuery = true)
    public void updateLoginAttempt(String email, String password);

    @Query(value = "SELECT user_email FROM user WHERE user_email = ?1 AND user_active_status = 1", nativeQuery = true)
    public String getUserFromEmail(String userEmail);

    @Query(value = "SELECT login_attempts FROM user WHERE user_email = ?1 AND user_active_status = 1", nativeQuery = true)
    public int getCurrentAttempt(String userEmail);

    @Modifying
    @Query(value = "UPDATE user SET login_attempts = ?1 WHERE user_email = ?2 AND user_active_status = 1", nativeQuery = true)
    public void updateLoginAttemptForEmail(int updatedAttempt, String email);

    @Modifying
    @Query(value = "UPDATE user SET login_attempts = 0 WHERE user_id = ?1", nativeQuery = true)
    public int unlockPassword(String userId);

    @Modifying
    @Query(value = "UPDATE user SET login_attempts = 0, is_first_login = 1, user_password = ?1 WHERE user_id = ?2", nativeQuery = true)
    public int resetPassword(String password, String userId);
}
