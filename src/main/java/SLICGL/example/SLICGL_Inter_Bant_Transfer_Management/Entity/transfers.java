package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
public class transfers {
    @Id
    @Column(name = "Transfer_Id", length = 15, nullable = false) // Eg - TFR-202507-0001
    private String transferId;
    @Column(name = "Transfer_Date", length = 10, nullable = false)
    private LocalDateTime transferDate;
    @Column(name = "Transfer_Amount", precision = 13, scale = 2, nullable = false)
    private BigDecimal transferAmount;
    @Column(name = "Is_Reversed", length = 1, nullable = false)
    private int isReversed;
    @ManyToOne
    @JoinColumn(name = "Reversed_By", nullable = true)
    private User reversedBy;
    @Column(name = "Is_Checked", length = 1, nullable = false)
    private int isChecked;
    @ManyToOne
    @JoinColumn(name = "Checked_By", nullable = true)
    private User checkedBy;
    @Column(name = "Checked_Date", length = 10, nullable = true)
    private LocalDateTime checkedDate;
    @Column(name = "Is_Approved", length = 1, nullable = false)
    private int isApproved;
    @ManyToOne
    @JoinColumn(name = "Approved_By", nullable = true)
    private User approvedBy;
    @Column(name = "Approved_Date", length = 10, nullable = true)
    private LocalDateTime approvdDate;
    @ManyToOne
    @JoinColumn(name = "Initiated_By", nullable = false)
    private User initiatedBy;
    @ManyToOne
    @JoinColumn(name = "From_Account", nullable = false)
    private bankAccount fromAccount;
    @ManyToOne
    @JoinColumn(name = "To_Account", nullable = false)
    private bankAccount toAccount;
    @ManyToOne
    @JoinColumn(name = "Chanel", nullable = false)
    private transferChannel chanel;
    @ManyToOne
    @JoinColumn(name = "From_Repo", nullable = true)
    private Repos fromRepo;
    @ManyToOne
    @JoinColumn(name = "To_Repo", nullable = true)
    private Repos toRepo;
    @OneToOne
    @JoinColumn(name = "cross_adjustment", nullable = false)
    private crossAdjustments crossAdjustment;

    public transfers() {
    }

    public transfers(String transferId, LocalDateTime transferDate, BigDecimal transferAmount, int isReversed, User reversedBy, int isChecked, User checkedBy, LocalDateTime checkedDate, int isApproved, User approvedBy, LocalDateTime approvdDate, User initiatedBy, bankAccount fromAccount, bankAccount toAccount, transferChannel chanel, Repos fromRepo, Repos toRepo, crossAdjustments crossAdjustment) {
        this.transferId = transferId;
        this.transferDate = transferDate;
        this.transferAmount = transferAmount;
        this.isReversed = isReversed;
        this.reversedBy = reversedBy;
        this.isChecked = isChecked;
        this.checkedBy = checkedBy;
        this.checkedDate = checkedDate;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.approvdDate = approvdDate;
        this.initiatedBy = initiatedBy;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.chanel = chanel;
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
        this.crossAdjustment = crossAdjustment;
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

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
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

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public User getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(User checkedBy) {
        this.checkedBy = checkedBy;
    }

    public LocalDateTime getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(LocalDateTime checkedDate) {
        this.checkedDate = checkedDate;
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

    public LocalDateTime getApprovdDate() {
        return approvdDate;
    }

    public void setApprovdDate(LocalDateTime approvdDate) {
        this.approvdDate = approvdDate;
    }

    public User getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(User initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public bankAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(bankAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public bankAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(bankAccount toAccount) {
        this.toAccount = toAccount;
    }

    public transferChannel getChanel() {
        return chanel;
    }

    public void setChanel(transferChannel chanel) {
        this.chanel = chanel;
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

    public crossAdjustments getCrossAdjustment() {
        return crossAdjustment;
    }

    public void setCrossAdjustment(crossAdjustments crossAdjustment) {
        this.crossAdjustment = crossAdjustment;
    }
}
