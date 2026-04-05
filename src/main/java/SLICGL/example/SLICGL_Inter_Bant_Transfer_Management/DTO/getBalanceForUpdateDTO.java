package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class getBalanceForUpdateDTO {
    private String balanceId;
    private String bank;
    private String accountNumber;
    private LocalDate balanceDate;
    private float balanceAmount;
    private float outstandingBalance;
    private int deleteStatus;

    public getBalanceForUpdateDTO() {
    }

    public getBalanceForUpdateDTO(String balanceId, String bank, String accountNumber, LocalDate balanceDate, float balanceAmount, float outstandingBalance, int deleteStatus) {
        this.balanceId = balanceId;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.balanceDate = balanceDate;
        this.balanceAmount = balanceAmount;
        this.outstandingBalance = outstandingBalance;
        this.deleteStatus = deleteStatus;
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

    public float getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(float outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
