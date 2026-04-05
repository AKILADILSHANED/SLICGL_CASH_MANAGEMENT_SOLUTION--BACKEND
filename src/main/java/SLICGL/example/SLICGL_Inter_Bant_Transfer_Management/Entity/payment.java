package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class payment {
    //Sample ID = "PAY-2025-001"
    @Id
    @Column(name = "payment_id", length = 12, nullable = false)
    private String paymentId;
    @Column(name = "payment_type", length = 50, nullable = false)
    private String paymentType;
    @Column(name = "registered_date", length = 30, nullable = false)
    private LocalDateTime registeredDate;
    @ManyToOne
    @JoinColumn(name = "registered_by", nullable = false)
    private User registeredBy;
    @Column(name = "is_deleted", length = 1, nullable = false) // 0 = not deleted || 1 = deleted
    private int deleteStatus;
    @ManyToOne
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deletedBy;

    public payment() {
    }

    public payment(String paymentId, String paymentType, LocalDateTime registeredDate, User registeredBy, int deleteStatus, User deletedBy) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.registeredDate = registeredDate;
        this.registeredBy = registeredBy;
        this.deleteStatus = deleteStatus;
        this.deletedBy = deletedBy;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }
}
