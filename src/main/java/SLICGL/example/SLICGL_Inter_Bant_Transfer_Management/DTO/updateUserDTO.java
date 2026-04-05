package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class updateUserDTO {
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userEpf;
    private String userEmail;
    private String userPosition;
    @Nullable
    @JsonIgnore
    private MultipartFile userSignature;

    public updateUserDTO() {
    }

    public updateUserDTO(String userId, String userFirstName, String userLastName, String userEpf, String userEmail, String userPosition, MultipartFile userSignature) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEpf = userEpf;
        this.userEmail = userEmail;
        this.userPosition = userPosition;
        this.userSignature = userSignature;
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

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public MultipartFile getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(MultipartFile userSignature) {
        this.userSignature = userSignature;
    }
}
