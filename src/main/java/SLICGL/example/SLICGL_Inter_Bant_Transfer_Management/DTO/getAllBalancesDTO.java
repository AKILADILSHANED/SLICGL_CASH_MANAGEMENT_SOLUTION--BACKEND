package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class getAllBalancesDTO {
    private String balanceId;
    private String bank;
    private String accountNumber;
    private LocalDate balanceDate;
    private float balanceAmount;
    private String deleteStatus;
    private String deletedBy;
    private String enteredBy;

    public getAllBalancesDTO() {
    }

    public getAllBalancesDTO(String balanceId, String bank, String accountNumber, LocalDate balanceDate, float balanceAmount, String deleteStatus, String deletedBy, String enteredBy) {
        this.balanceId = balanceId;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.balanceDate = balanceDate;
        this.balanceAmount = balanceAmount;
        this.deleteStatus = deleteStatus;
        this.deletedBy = deletedBy;
        this.enteredBy = enteredBy;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(LocalDate balanceDate) {
        this.balanceDate = balanceDate;
    }

    public float getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(float balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }
}
