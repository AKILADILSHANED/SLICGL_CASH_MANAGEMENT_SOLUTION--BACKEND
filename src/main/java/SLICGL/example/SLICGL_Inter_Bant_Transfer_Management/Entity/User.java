package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "User_Id", length = 6, nullable = false)
    private String userId;
    @Column(name = "User_Title", length = 5, nullable = false)
    private String userTitle;
    @Column(name = "User_First_Name", length = 20, nullable = false)
    private String userFirstName;
    @Column(name = "User_Last_Name", length = 20, nullable = false)
    private String userLastName;
    @Column(name = "Department", length = 20, nullable = false)
    private String department;
    @Column(name = "Section", length = 20, nullable = false)
    private String section;
    @Column(name = "user_position", length = 50, nullable = false)
    private String userPosition;
    @Column(name = "User_EPF", length = 10, nullable = false)
    private String userEpf;
    @Column(name = "User_Email", length = 50, nullable = false)
    private String userEmail;
    @Column(name = "User_Password", length = 100, nullable = false)
    private String userPassword;
    @Column(name = "User_Active_Status", length = 1, nullable = false)
    private int userActiveStatus; //1 - Active, 0 - Not Active
    @Column(name = "Is_First_Login", length = 1, nullable = false)
    private int isFirstLogin; // 1 - First Login, 0 - Not First Login
    @Column(name = "Login_Attempts", length = 1, nullable = false)
    private int loginAttempts;
    @Column(name = "Is_Admin", length = 1, nullable = false)
    private int userLevel; //User Levels (0 - General User, 1 - Administrator)
    @Column(name = "User_Created_Date", nullable = false)
    private LocalDateTime userCreatedDate;
    @ManyToOne
    @JoinColumn(name = "User_Created_By")
    private User userCreateBy;
    @Lob
    @Column(name = "user_signature", columnDefinition = "LONGBLOB", nullable = true)
    private byte[] userSignature;
    @ManyToOne
    @JoinColumn(name = "User_Deleted_By", nullable = true)
    private User userDeletedBy;
    @ManyToMany
    @JoinTable(
            name = "user_function",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "function_id")
    )
    private List<subFunction> subFunctions;

    public User() {
    }

    public User(String userId, String userTitle, String userFirstName, String userLastName, String department, String section, String userPosition, String userEpf, String userEmail, String userPassword, int userActiveStatus, int isFirstLogin, int loginAttempts, int userLevel, LocalDateTime userCreatedDate, User userCreateBy, byte[] userSignature, User userDeletedBy, List<subFunction> subFunctions) {
        this.userId = userId;
        this.userTitle = userTitle;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.department = department;
        this.section = section;
        this.userPosition = userPosition;
        this.userEpf = userEpf;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userActiveStatus = userActiveStatus;
        this.isFirstLogin = isFirstLogin;
        this.loginAttempts = loginAttempts;
        this.userLevel = userLevel;
        this.userCreatedDate = userCreatedDate;
        this.userCreateBy = userCreateBy;
        this.userSignature = userSignature;
        this.userDeletedBy = userDeletedBy;
        this.subFunctions = subFunctions;
    }

    public User(String userId, String userTitle, String userFirstName, String userLastName, String department, String section, String userPosition, String userEpf, String userEmail, String userPassword, int userActiveStatus, int isFirstLogin, int loginAttempts, int userLevel, LocalDateTime userCreatedDate, User userCreateBy, byte[] userSignature, User userDeletedBy) {
        this.userId = userId;
        this.userTitle = userTitle;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.department = department;
        this.section = section;
        this.userPosition = userPosition;
        this.userEpf = userEpf;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userActiveStatus = userActiveStatus;
        this.isFirstLogin = isFirstLogin;
        this.loginAttempts = loginAttempts;
        this.userLevel = userLevel;
        this.userCreatedDate = userCreatedDate;
        this.userCreateBy = userCreateBy;
        this.userSignature = userSignature;
        this.userDeletedBy = userDeletedBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUserEpf() {
        return userEpf;
    }

    public void setUserEpf(String userEpf) {
        this.userEpf = userEpf;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserActiveStatus() {
        return userActiveStatus;
    }

    public void setUserActiveStatus(int userActiveStatus) {
        this.userActiveStatus = userActiveStatus;
    }

    public int getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(int isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public LocalDateTime getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(LocalDateTime userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public User getUserCreateBy() {
        return userCreateBy;
    }

    public void setUserCreateBy(User userCreateBy) {
        this.userCreateBy = userCreateBy;
    }

    public User getUserDeletedBy() {
        return userDeletedBy;
    }

    public void setUserDeletedBy(User userDeletedBy) {
        this.userDeletedBy = userDeletedBy;
    }

    public List<subFunction> getSubFunctions() {
        return subFunctions;
    }

    public void setSubFunctions(List<subFunction> subFunctions) {
        this.subFunctions = subFunctions;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public byte[] getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(byte[] userSignature) {
        this.userSignature = userSignature;
    }
}
