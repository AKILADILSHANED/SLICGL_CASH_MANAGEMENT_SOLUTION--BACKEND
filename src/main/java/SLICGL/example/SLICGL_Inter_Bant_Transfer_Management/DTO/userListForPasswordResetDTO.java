package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class userListForPasswordResetDTO {
    private String userId;
    private String epf;
    private String firstName;
    private String lastName;
    private String email;

    public userListForPasswordResetDTO() {
    }

    public userListForPasswordResetDTO(String userId, String epf, String firstName, String lastName, String email) {
        this.userId = userId;
        this.epf = epf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEpf() {
        return epf;
    }

    public void setEpf(String epf) {
        this.epf = epf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
