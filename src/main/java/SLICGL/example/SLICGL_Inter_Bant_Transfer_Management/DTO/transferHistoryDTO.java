package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class transferHistoryDTO {
    private String transferId;
    private LocalDate transferDate;
    private float transferAmount;
    private String chanel;
    private String fromAccount;
    private String toAccount;
    private String fromRepo;
    private String toRepo;
    private String initiatedBy;
    private String checkedStatus;
    private String approveStatus;

    public transferHistoryDTO() {
    }

    public transferHistoryDTO(String transferId, LocalDate transferDate, float transferAmount, String chanel, String fromAccount, String toAccount, String fromRepo, String toRepo, String initiatedBy, String checkedStatus, String approveStatus) {
        this.transferId = transferId;
        this.transferDate = transferDate;
        this.transferAmount = transferAmount;
        this.chanel = chanel;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
        this.initiatedBy = initiatedBy;
        this.checkedStatus = checkedStatus;
        this.approveStatus = approveStatus;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getChanel() {
        return chanel;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
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
}
