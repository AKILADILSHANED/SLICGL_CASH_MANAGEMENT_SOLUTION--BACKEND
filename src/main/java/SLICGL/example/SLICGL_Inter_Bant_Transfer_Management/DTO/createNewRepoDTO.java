package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;


public class createNewRepoDTO {
    private String accountID;
    private Integer repoType;
    private String repoValue;
    private Integer eligibility;

    public createNewRepoDTO() {
    }

    public createNewRepoDTO(String accountID, Integer repoType, String repoValue, Integer eligibility) {
        this.accountID = accountID;
        this.repoType = repoType;
        this.repoValue = repoValue;
        this.eligibility = eligibility;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getRepoType() {
        return repoType;
    }

    public void setRepoType(Integer repoType) {
        this.repoType = repoType;
    }

    public String getRepoValue() {
        return repoValue;
    }

    public void setRepoValue(String repoValue) {
        this.repoValue = repoValue;
    }

    public Integer getEligibility() {
        return eligibility;
    }

    public void setEligibility(Integer eligibility) {
        this.eligibility = eligibility;
    }
}
