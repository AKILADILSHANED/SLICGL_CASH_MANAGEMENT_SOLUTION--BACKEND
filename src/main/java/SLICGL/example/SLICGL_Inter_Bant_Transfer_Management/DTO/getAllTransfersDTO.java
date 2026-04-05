package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class getAllTransfersDTO {
    private String transferId;
    private String fromAccount;
    private String toAccount;
    private float transferAmount;
    private String channel;
    private String fromRepo;
    private String toRepo;
    private String approveStatus;
    private String approvedBy;
    private String approveDate;
    private String initiatedBy;
    private String initiatedDate;
    private String checkedStatus;
    private String checkedBy;
    private String checkedDate;
    private String isReversed;
    private String reversedBy;
    private String crossAdjustment;

    public getAllTransfersDTO() {
    }

    public getAllTransfersDTO(String transferId, String fromAccount, String toAccount, float transferAmount, String channel, String fromRepo, String toRepo, String approveStatus, String approvedBy, String approveDate, String initiatedBy, String initiatedDate, String checkedStatus, String checkedBy, String checkedDate, String isReversed, String reversedBy, String crossAdjustment) {
        this.transferId = transferId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transferAmount = transferAmount;
        this.channel = channel;
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
        this.approveStatus = approveStatus;
        this.approvedBy = approvedBy;
        this.approveDate = approveDate;
        this.initiatedBy = initiatedBy;
        this.initiatedDate = initiatedDate;
        this.checkedStatus = checkedStatus;
        this.checkedBy = checkedBy;
        this.checkedDate = checkedDate;
        this.isReversed = isReversed;
        this.reversedBy = reversedBy;
        this.crossAdjustment = crossAdjustment;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFromRepo() {
        return fromRepo;
    }

    public void setFromRepo(String fromRepo) {
        this.fromRepo = fromRepo;
    }

    public String getToRepo() {
        return toRepo;
    }

    public void setToRepo(String toRepo) {
        this.toRepo = toRepo;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public String getInitiatedDate() {
        return initiatedDate;
    }

    public void setInitiatedDate(String initiatedDate) {
        this.initiatedDate = initiatedDate;
    }

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(String checkedDate) {
        this.checkedDate = checkedDate;
    }

    public String getIsReversed() {
        return isReversed;
    }

    public void setIsReversed(String isReversed) {
        this.isReversed = isReversed;
    }

    public String getReversedBy() {
        return reversedBy;
    }

    public void setReversedBy(String reversedBy) {
        this.reversedBy = reversedBy;
    }

    public String getCrossAdjustment() {
        return crossAdjustment;
    }

    public void setCrossAdjustment(String crossAdjustment) {
        this.crossAdjustment = crossAdjustment;
    }
}
