package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class userLoginDTO {
    private String userEmail;
    private String userPassword;

    public userLoginDTO() {
    }

    public userLoginDTO(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
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
}
