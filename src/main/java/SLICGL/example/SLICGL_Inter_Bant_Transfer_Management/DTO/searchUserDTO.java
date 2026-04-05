package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class searchUserDTO {
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userEpf;
    private String userEmail;
    private String userActiveStatus;
    private String userLevel; //User Levels (1 - Admin, 0 - General User)
    private LocalDateTime userCreatedDate;
    private String userCreateBy;
    private String userPosition;
    private String department;
    private String section;

    public searchUserDTO() {
    }

    public searchUserDTO(String userId, String userFirstName, String userLastName, String userEpf, String userEmail, String userActiveStatus, String userLevel, LocalDateTime userCreatedDate, String userCreateBy, String userPosition, String department, String section) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEpf = userEpf;
        this.userEmail = userEmail;
        this.userActiveStatus = userActiveStatus;
        this.userLevel = userLevel;
        this.userCreatedDate = userCreatedDate;
        this.userCreateBy = userCreateBy;
        this.userPosition = userPosition;
        this.department = department;
        this.section = section;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserActiveStatus() {
        return userActiveStatus;
    }

    public void setUserActiveStatus(String userActiveStatus) {
        this.userActiveStatus = userActiveStatus;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public LocalDateTime getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(LocalDateTime userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public String getUserCreateBy() {
        return userCreateBy;
    }

    public void setUserCreateBy(String userCreateBy) {
        this.userCreateBy = userCreateBy;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
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
}
