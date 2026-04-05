package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class getAllTransferForApproveDTO {
    private String transferId;
    private LocalDate transferDate;
    private String fromAccount;
    private String toAccount;
    private String fromRepo;
    private String toRepo;
    private float transferAmount;

    public getAllTransferForApproveDTO() {
    }

    public getAllTransferForApproveDTO(String transferId, LocalDate transferDate, String fromAccount, String toAccount, String fromRepo, String toRepo, float transferAmount) {
        this.transferId = transferId;
        this.transferDate = transferDate;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
        this.transferAmount = transferAmount;
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
}
