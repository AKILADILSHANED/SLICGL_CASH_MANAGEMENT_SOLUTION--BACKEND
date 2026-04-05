package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "forecast_request")
public class forecastRequest {
    //Sample ID = "FRC-202504-01"
    @Id
    @Column(name = "request_id", length = 13, nullable = false)
    private String requestId;
    @Column(name = "request_amount", length = 10, nullable = false)
    private BigDecimal requestAmount;
    @Column(name = "request_date", length = 30, nullable = false)
    private LocalDateTime requestDate;
    @Column(name = "required_date", length = 10, nullable = false)
    private LocalDate requiredDate;
    @Column(name = "delete_status", length = 1, nullable = false) // 0 = not deleted || 1 = deleted
    private int deleteStatus;
    @ManyToOne
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deleted_by;
    @ManyToOne
    @JoinColumn(name = "request_by", nullable = false)
    private User requestedBy;
    @ManyToOne
    @JoinColumn(name = "payment", nullable = false)
    private payment payment;
    @ManyToOne
    @JoinColumn(name = "bank_account")
    private bankAccount bankAccount;

    public forecastRequest() {
    }

    public forecastRequest(String requestId, BigDecimal requestAmount, LocalDateTime requestDate, LocalDate requiredDate, int deleteStatus, User deleted_by, User requestedBy, payment payment, bankAccount bankAccount) {
        this.requestId = requestId;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.deleteStatus = deleteStatus;
        this.deleted_by = deleted_by;
        this.requestedBy = requestedBy;
        this.payment = payment;
        this.bankAccount = bankAccount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(User deleted_by) {
        this.deleted_by = deleted_by;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }

    public payment getPayment() {
        return payment;
    }

    public void setPayment(payment payment) {
        this.payment = payment;
    }

    public bankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(bankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
