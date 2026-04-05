package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class repoListForManualTransfersDTO {
    private String repoId;
    private String bankAccount;

    public repoListForManualTransfersDTO() {
    }

    public repoListForManualTransfersDTO(String repoId, String bankAccount) {
        this.repoId = repoId;
        this.bankAccount = bankAccount;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
