package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getFromRepoListDTO {
    private String repoId;
    private String closingBalance;

    public getFromRepoListDTO() {
    }

    public getFromRepoListDTO(String repoId, String closingBalance) {
        this.repoId = repoId;
        this.closingBalance = closingBalance;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }
}
