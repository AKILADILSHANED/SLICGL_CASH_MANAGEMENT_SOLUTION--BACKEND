package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class getBalanceReportDTO {
    private String balanceId;
    private String balanceDate;
    private String bank;
    private String branch;
    private String account;
    private BigDecimal amount;
    private String status;
    private String deletedBy;
    private String enterBy;

    public getBalanceReportDTO() {
    }

    public getBalanceReportDTO(String balanceId, String balanceDate, String bank, String branch, String account, BigDecimal amount, String status, String deletedBy, String enterBy) {
        this.balanceId = balanceId;
        this.balanceDate = balanceDate;
        this.bank = bank;
        this.branch = branch;
        this.account = account;
        this.amount = amount;
        this.status = status;
        this.deletedBy = deletedBy;
        this.enterBy = enterBy;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getEnterBy() {
        return enterBy;
    }

    public void setEnterBy(String enterBy) {
        this.enterBy = enterBy;
    }
}
