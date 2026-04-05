package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class balanceEnterDTO {
    private String accountId;
    private String bankName;
    private String bankBranch;
    private String accountNumber;

    public balanceEnterDTO() {
    }

    public balanceEnterDTO(String accountId, String bankName, String bankBranch, String accountNumber) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
