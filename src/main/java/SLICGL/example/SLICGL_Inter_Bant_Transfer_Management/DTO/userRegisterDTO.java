package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public class userRegisterDTO {
    private String userTitle;
    private Integer userLevel;
    private String userFirstName;
    private String userLastName;
    private String department;
    private String section;
    private String userPosition;
    private String userEmail;
    private String userEpf;
    @JsonIgnore
    private MultipartFile userSignature;

    public userRegisterDTO() {
    }

    public userRegisterDTO(String userTitle, Integer userLevel, String userFirstName, String userLastName, String department, String section, String userPosition, String userEmail, String userEpf, MultipartFile userSignature) {
        this.userTitle = userTitle;
        this.userLevel = userLevel;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.department = department;
        this.section = section;
        this.userPosition = userPosition;
        this.userEmail = userEmail;
        this.userEpf = userEpf;
        this.userSignature = userSignature;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEpf() {
        return userEpf;
    }

    public void setUserEpf(String userEpf) {
        this.userEpf = userEpf;
    }

    public MultipartFile getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(MultipartFile userSignature) {
        this.userSignature = userSignature;
    }
}
