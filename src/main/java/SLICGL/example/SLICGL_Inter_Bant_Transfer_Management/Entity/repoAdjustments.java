package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "repo_adjustment")
public class repoAdjustments {
    @Id
    @Column(name = "Adjustment_Id", length = 18, nullable = false) // Eg - RPOADJ-202507-0001
    private String adjustmentId;
    @Column(name = "Adjustment_Date", length = 10, nullable = false)
    private LocalDateTime adjustmentDate;
    @Column(name = "Adjustment_Remark", length = 100, nullable = false)
    private String adjustmentRemark;
    @Column(name = "Adjustment_Amount", precision = 13, scale = 2, nullable = false)
    private BigDecimal adjustmentAmount;
    @ManyToOne
    @JoinColumn(name = "Adjusted_Repo", nullable = false)
    private Repos adjustedRepo;
    @ManyToOne
    @JoinColumn(name = "Transfer_Id", nullable = true)
    private transfers transferId;
    @ManyToOne
    @JoinColumn(name = "Cross_Adjustment_Id", nullable = true)
    private crossAdjustments crossAdjustmentId;

    public repoAdjustments() {
    }

    public repoAdjustments(String adjustmentId, LocalDateTime adjustmentDate, String adjustmentRemark, BigDecimal adjustmentAmount, Repos adjustedRepo, transfers transferId, crossAdjustments crossAdjustmentId) {
        this.adjustmentId = adjustmentId;
        this.adjustmentDate = adjustmentDate;
        this.adjustmentRemark = adjustmentRemark;
        this.adjustmentAmount = adjustmentAmount;
        this.adjustedRepo = adjustedRepo;
        this.transferId = transferId;
        this.crossAdjustmentId = crossAdjustmentId;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public LocalDateTime getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDateTime adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getAdjustmentRemark() {
        return adjustmentRemark;
    }

    public void setAdjustmentRemark(String adjustmentRemark) {
        this.adjustmentRemark = adjustmentRemark;
    }

    public BigDecimal getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public Repos getAdjustedRepo() {
        return adjustedRepo;
    }

    public void setAdjustedRepo(Repos adjustedRepo) {
        this.adjustedRepo = adjustedRepo;
    }

    public transfers getTransferId() {
        return transferId;
    }

    public void setTransferId(transfers transferId) {
        this.transferId = transferId;
    }

    public crossAdjustments getCrossAdjustmentId() {
        return crossAdjustmentId;
    }

    public void setCrossAdjustmentId(crossAdjustments crossAdjustmentId) {
        this.crossAdjustmentId = crossAdjustmentId;
    }
}
