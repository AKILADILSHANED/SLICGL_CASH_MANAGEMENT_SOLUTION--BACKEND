package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class searchAccountUpdateDTO {
    private String accountId;
    private String bankId;
    private String bank;
    private String bankBranch;
    private int accountType; // 1 = Current Account, 2 = Saving Account;
    private String currency;
    private int glCode;
    private String accountNumber;
    private LocalDateTime registeredDate;
    private String registeredBy;
    private int deleteStatus;

    public searchAccountUpdateDTO() {
    }

    public searchAccountUpdateDTO(String accountId, String bankId, String bank, String bankBranch, int accountType, String currency, int glCode, String accountNumber, LocalDateTime registeredDate, String registeredBy, int deleteStatus) {
        this.accountId = accountId;
        this.bankId = bankId;
        this.bank = bank;
        this.bankBranch = bankBranch;
        this.accountType = accountType;
        this.currency = currency;
        this.glCode = glCode;
        this.accountNumber = accountNumber;
        this.registeredDate = registeredDate;
        this.registeredBy = registeredBy;
        this.deleteStatus = deleteStatus;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
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

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getGlCode() {
        return glCode;
    }

    public void setGlCode(int glCode) {
        this.glCode = glCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
