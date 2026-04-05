package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cross_adjustment")
public class crossAdjustments {
    @Id
    @Column(name = "Cross_Adjustment_Id", length = 18, nullable = false) // Eg - CRSADJ-202507-0001
    private String crossAdjustmentId;
    @Column(name = "Adjusted_Date", nullable = false)
    private LocalDateTime adjustmentDate;
    @Column(name = "Is_Reversed", length = 1, nullable = false)
    private int isReversed;
    @ManyToOne
    @JoinColumn(name = "Reversed_By", nullable = true)
    private User reversedBy;

    public crossAdjustments() {
    }

    public crossAdjustments(String crossAdjustmentId, LocalDateTime adjustmentDate, int isReversed, User reversedBy) {
        this.crossAdjustmentId = crossAdjustmentId;
        this.adjustmentDate = adjustmentDate;
        this.isReversed = isReversed;
        this.reversedBy = reversedBy;
    }

    public User getReversedBy() {
        return reversedBy;
    }

    public void setReversedBy(User reversedBy) {
        this.reversedBy = reversedBy;
    }

    public int getIsReversed() {
        return isReversed;
    }

    public void setIsReversed(int isReversed) {
        this.isReversed = isReversed;
    }

    public LocalDateTime getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDateTime adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getCrossAdjustmentId() {
        return crossAdjustmentId;
    }

    public void setCrossAdjustmentId(String crossAdjustmentId) {
        this.crossAdjustmentId = crossAdjustmentId;
    }
}
