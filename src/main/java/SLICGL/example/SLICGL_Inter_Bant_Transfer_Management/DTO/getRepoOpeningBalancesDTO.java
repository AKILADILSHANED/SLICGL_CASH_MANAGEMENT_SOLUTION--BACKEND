package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getRepoOpeningBalancesDTO {
    private String repoId;
    private String balance;
    private String bankAccount;

    public getRepoOpeningBalancesDTO() {
    }

    public getRepoOpeningBalancesDTO(String repoId, String balance, String bankAccount) {
        this.repoId = repoId;
        this.balance = balance;
        this.bankAccount = bankAccount;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
