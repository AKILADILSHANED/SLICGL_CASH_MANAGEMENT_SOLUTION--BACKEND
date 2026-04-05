package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "repo_transfers")
public class repoTransfers {
    @Id
    @Column(name = "Transfer_Id", length = 15, nullable = false) // Eg - RPOTFR-202507-0001
    private String transferId;
    @Column(name = "Transfer_Date", length = 10, nullable = false)
    private LocalDateTime transferDate;
    @Column(name = "Transfer_Amount", length = 12, nullable = false)
    private float transferAmount;
    @Column(name = "Is_Reversed", length = 1, nullable = false)
    private int isReversed;
    @ManyToOne
    @JoinColumn(name = "Reversed_By", nullable = true)
    private User reversedBy;
    @Column(name = "Is_Approved", length = 1, nullable = false)
    private int isApproved;
    @ManyToOne
    @JoinColumn(name = "Approved_By", nullable = true)
    private User approvedBy;
    @ManyToOne
    @JoinColumn(name = "Initiated_By", nullable = false)
    private User initiatedBy;
    @ManyToOne
    @JoinColumn(name = "From_Repo", nullable = false)
    private Repos fromRepo;
    @ManyToOne
    @JoinColumn(name = "To_Repo", nullable = false)
    private Repos toRepo;

    public repoTransfers() {
    }

    public repoTransfers(String transferId, LocalDateTime transferDate, float transferAmount, int isReversed, User reversedBy, int isApproved, User approvedBy, User initiatedBy, Repos fromRepo, Repos toRepo) {
        this.transferId = transferId;
        this.transferDate = transferDate;
        this.transferAmount = transferAmount;
        this.isReversed = isReversed;
        this.reversedBy = reversedBy;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.initiatedBy = initiatedBy;
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }

    public int getIsReversed() {
        return isReversed;
    }

    public void setIsReversed(int isReversed) {
        this.isReversed = isReversed;
    }

    public User getReversedBy() {
        return reversedBy;
    }

    public void setReversedBy(User reversedBy) {
        this.reversedBy = reversedBy;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public User getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(User initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public Repos getFromRepo() {
        return fromRepo;
    }

    public void setFromRepo(Repos fromRepo) {
        this.fromRepo = fromRepo;
    }

    public Repos getToRepo() {
        return toRepo;
    }

    public void setToRepo(Repos toRepo) {
        this.toRepo = toRepo;
    }
}
