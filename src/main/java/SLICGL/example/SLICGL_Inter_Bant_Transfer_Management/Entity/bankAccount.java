package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "bankAccount")
public class bankAccount {
    @Id
    @Column(name = "Account_ID", nullable = false, length = 7)
    private String accountId;
    @Column(name = "Bank_Branch", nullable = false, length = 50)
    private String bankBranch;
    @Column(name = "Account_Type", nullable = false, length = 1)
    private int accountType; // 1 = Current Account, 2 = Saving Account;
    @Column(name = "Currency", nullable = false, length = 10)
    private String currency;
    @ManyToOne
    @JoinColumn(name = "bank", nullable = false)
    private bank bank;
    @Column(name = "GL_Code", nullable = false, length = 10)
    private int glCode;
    @Column(name = "Account_Number", nullable = false, length = 30)
    private String accountNumber;
    @Column(name = "Delete_Status", nullable = false, length = 1)
    private int deleteStatus; // 0 = Not Deleted, 1 = Deleted;
    @ManyToOne
    @JoinColumn(name = "Deleted_By", nullable = true)
    private User deleteBy;
    @Column(name = "Registered_Date", nullable = false)
    private LocalDateTime registeredDate;
    @ManyToOne
    @JoinColumn(name = "Registered_By", nullable = false)
    private User registeredBy;

    public bankAccount() {
    }

    public bankAccount(String accountId, String bankBranch, int accountType, String currency, SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank bank, int glCode, String accountNumber, int deleteStatus, User deleteBy, LocalDateTime registeredDate, User registeredBy) {
        this.accountId = accountId;
        this.bankBranch = bankBranch;
        this.accountType = accountType;
        this.currency = currency;
        this.bank = bank;
        this.glCode = glCode;
        this.accountNumber = accountNumber;
        this.deleteStatus = deleteStatus;
        this.deleteBy = deleteBy;
        this.registeredDate = registeredDate;
        this.registeredBy = registeredBy;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(User deleteBy) {
        this.deleteBy = deleteBy;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

    public SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank getBank() {
        return bank;
    }

    public void setBank(SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank bank) {
        this.bank = bank;
    }
}
