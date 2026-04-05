package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getRepoClosingBalancesDTO {
    private String repoId;
    private String account;
    private String repoType;
    private String investValue;

    public getRepoClosingBalancesDTO() {
    }

    public getRepoClosingBalancesDTO(String repoId, String account, String repoType, String investValue) {
        this.repoId = repoId;
        this.account = account;
        this.repoType = repoType;
        this.investValue = investValue;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getInvestValue() {
        return investValue;
    }

    public void setInvestValue(String investValue) {
        this.investValue = investValue;
    }
}
