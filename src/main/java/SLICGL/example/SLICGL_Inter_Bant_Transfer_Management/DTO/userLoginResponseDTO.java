package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.util.List;

public class userLoginResponseDTO {
    private String userTitle;
    private String userFirstName;
    private String userLastName;
    private int userLevel;
    private List<String> authorizedFunctions;

    public userLoginResponseDTO() {
    }

    public userLoginResponseDTO(String userTitle, String userFirstName, String userLastName, int userLevel, List<String> authorizedFunctions) {
        this.userTitle = userTitle;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userLevel = userLevel;
        this.authorizedFunctions = authorizedFunctions;
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

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public List<String> getAuthorizedFunctions() {
        return authorizedFunctions;
    }

    public void setAuthorizedFunctions(List<String> authorizedFunctions) {
        this.authorizedFunctions = authorizedFunctions;
    }
}
