package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class fundSendingAccountsDTO {
    private String accountId;
    private float accountBalance;
    private String bank;

    public fundSendingAccountsDTO() {
    }

    public fundSendingAccountsDTO(String accountId, float accountBalance, String bank) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.bank = bank;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
