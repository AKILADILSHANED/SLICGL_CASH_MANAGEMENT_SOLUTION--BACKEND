package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class rejectTransfersDTO {
    private String transferId;
    private String fromAccount;
    private String toAccount;
    private float transferAmount;
    private String channel;
    private String fromRepo;
    private String toRepo;
    private String initiatedBy;
    private String checkedStatus;
    private String approveStatus;
    private int isReversed;

    public rejectTransfersDTO() {
    }

    public rejectTransfersDTO(String transferId, String fromAccount, String toAccount, float transferAmount, String channel, String fromRepo, String toRepo, String initiatedBy, String checkedStatus, String approveStatus, int isReversed) {
        this.transferId = transferId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transferAmount = transferAmount;
        this.channel = channel;
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
        this.initiatedBy = initiatedBy;
        this.checkedStatus = checkedStatus;
        this.approveStatus = approveStatus;
        this.isReversed = isReversed;
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

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public int getIsReversed() {
        return isReversed;
    }

    public void setIsReversed(int isReversed) {
        this.isReversed = isReversed;
    }
}
