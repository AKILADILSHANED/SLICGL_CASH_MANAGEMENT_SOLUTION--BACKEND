package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class availableTransferOptionsDTO {
    private String optionId;
    private LocalDate definedDate;
    private String definedBy;
    private String isActive;
    private String isDeleted;
    private String deletedBy;
    private String fromAccount;
    private String toAccount;
    private String transferChannel;

    public availableTransferOptionsDTO() {
    }

    public availableTransferOptionsDTO(String optionId, LocalDate definedDate, String definedBy, String isActive, String isDeleted, String deletedBy, String fromAccount, String toAccount, String transferChannel) {
        this.optionId = optionId;
        this.definedDate = definedDate;
        this.definedBy = definedBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transferChannel = transferChannel;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public LocalDate getDefinedDate() {
        return definedDate;
    }

    public void setDefinedDate(LocalDate definedDate) {
        this.definedDate = definedDate;
    }

    public String getDefinedBy() {
        return definedBy;
    }

    public void setDefinedBy(String definedBy) {
        this.definedBy = definedBy;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
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

    public String getTransferChannel() {
        return transferChannel;
    }

    public void setTransferChannel(String transferChannel) {
        this.transferChannel = transferChannel;
    }
}
