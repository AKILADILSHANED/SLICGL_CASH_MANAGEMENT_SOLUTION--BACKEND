package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class toAccountsForTransferOptionsDTO {
    private String accountId;
    private String accountNumber;

    public toAccountsForTransferOptionsDTO() {
    }

    public toAccountsForTransferOptionsDTO(String accountId, String accountNumber) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
