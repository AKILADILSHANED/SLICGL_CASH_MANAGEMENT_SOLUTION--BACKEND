package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class bankAccountRegisterDTO {

    private String bank;
    private String bankBranch;
    private Integer accountType; // 1 = Current Account, 2 = Saving Account;
    private String currency;
    private Integer glCode;
    private String accountNumber;

    public bankAccountRegisterDTO() {
    }

    public bankAccountRegisterDTO(String bank, String bankBranch, Integer accountType, String currency, Integer glCode, String accountNumber) {
        this.bank = bank;
        this.bankBranch = bankBranch;
        this.accountType = accountType;
        this.currency = currency;
        this.glCode = glCode;
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getGlCode() {
        return glCode;
    }

    public void setGlCode(Integer glCode) {
        this.glCode = glCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
