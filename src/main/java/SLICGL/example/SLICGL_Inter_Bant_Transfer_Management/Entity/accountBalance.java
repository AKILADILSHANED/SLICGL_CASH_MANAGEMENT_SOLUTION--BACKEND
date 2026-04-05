package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account_balance")
public class accountBalance {
    @Id
    @Column(name = "balance_id", length = 15, nullable = false)
    private String balanceId;
    @Column(name = "balance_date", nullable = false)
    private LocalDate balanceDate;
    @Column(name = "balance_amount", length = 15, nullable = false)
    private float balanceAmount;
    @Column(name = "delete_status", length = 1, nullable = false)
    private int deleteStatus;
    @ManyToOne
    @JoinColumn(name = "entered_by", nullable = false)
    private User enteredBy;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private bankAccount account;
    @ManyToOne
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deletedBy;

    public accountBalance() {
    }

    public accountBalance(String balanceId, LocalDate balanceDate, float balanceAmount, int deleteStatus, User enteredBy, bankAccount account, User deletedBy) {
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
