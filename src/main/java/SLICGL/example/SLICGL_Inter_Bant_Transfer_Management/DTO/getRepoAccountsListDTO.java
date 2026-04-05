package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getRepoAccountsListDTO {
    private String repoId;
    private String repoType;
    private String repoAccount;

    public getRepoAccountsListDTO() {
    }

    public getRepoAccountsListDTO(String repoId, String repoType, String repoAccount) {
        this.repoId = repoId;
        this.repoType = repoType;
        this.repoAccount = repoAccount;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getRepoAccount() {
        return repoAccount;
    }

    public void setRepoAccount(String repoAccount) {
        this.repoAccount = repoAccount;
    }
}
