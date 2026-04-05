package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class userPasswordReset {
    private String userEmail;
    private String temporaryPassword;
    private String changedPassword;

    public userPasswordReset() {
    }

    public userPasswordReset(String userEmail, String temporaryPassword, String changedPassword) {
        this.userEmail = userEmail;
        this.temporaryPassword = temporaryPassword;
        this.changedPassword = changedPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    public void setChangedPassword(String changedPassword) {
        this.changedPassword = changedPassword;
    }
}
