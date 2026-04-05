package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class accountReportDTO {
    private String accountId;
    private String bankName;
    private String bankBranch;
    private String bankAccount;
    private String currency;
    private String glCode;
    private String accountType;
    private String status;
    private String deletedBy;
    private String registeredDate;
    private String registeredBy;;

    public accountReportDTO() {
    }

    public accountReportDTO(String accountId, String bankName, String bankBranch, String bankAccount, String currency, String glCode, String accountType, String status, String deletedBy, String registeredDate, String registeredBy) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.bankAccount = bankAccount;
        this.currency = currency;
        this.glCode = glCode;
        this.accountType = accountType;
        this.status = status;
        this.deletedBy = deletedBy;
        this.registeredDate = registeredDate;
        this.registeredBy = registeredBy;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }
}
