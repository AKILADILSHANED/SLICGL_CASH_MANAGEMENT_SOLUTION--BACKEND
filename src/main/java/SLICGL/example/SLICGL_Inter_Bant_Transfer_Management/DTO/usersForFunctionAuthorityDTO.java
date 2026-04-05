package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class usersForFunctionAuthorityDTO {
    private String userId;
    private String userEpf;
    private String userFirstName;
    private String userLastName;

    public usersForFunctionAuthorityDTO() {
    }

    public usersForFunctionAuthorityDTO(String userId, String userEpf, String userFirstName, String userLastName) {
        this.userId = userId;
        this.userEpf = userEpf;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEpf() {
        return userEpf;
    }

    public void setUserEpf(String userEpf) {
        this.userEpf = userEpf;
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
}
