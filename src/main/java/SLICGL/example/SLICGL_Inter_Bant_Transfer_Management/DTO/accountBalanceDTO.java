package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bankAccount;

import java.time.LocalDate;

public class accountBalanceDTO {
    private String balanceId;
    private LocalDate balanceDate;
    private float balanceAmount;
    private int deleteStatus;
    private User enteredBy;
    private bankAccount account;
    private User deletedBy;

    public accountBalanceDTO() {
    }

    public accountBalanceDTO(String balanceId, LocalDate balanceDate, float balanceAmount, int deleteStatus, User enteredBy, bankAccount account, User deletedBy) {
        this.balanceId = balanceId;
        this.balanceDate = balanceDate;
        this.balanceAmount = balanceAmount;
        this.deleteStatus = deleteStatus;
        this.enteredBy = enteredBy;
        this.account = account;
        this.deletedBy = deletedBy;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
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

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(User enteredBy) {
        this.enteredBy = enteredBy;
    }

    public bankAccount getAccount() {
        return account;
    }

    public void setAccount(bankAccount account) {
        this.account = account;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }
}
