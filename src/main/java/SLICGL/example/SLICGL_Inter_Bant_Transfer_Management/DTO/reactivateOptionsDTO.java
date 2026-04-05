package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class reactivateOptionsDTO {
    private String optionId;
    private LocalDateTime definedDate;
    private String fromAccount;
    private String toAccount;
    private String  transferChannel;

    public reactivateOptionsDTO(String optionId, LocalDateTime definedDate, String fromAccount, String toAccount, String transferChannel) {
        this.optionId = optionId;
        this.definedDate = definedDate;
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

    public LocalDateTime getDefinedDate() {
        return definedDate;
    }

    public void setDefinedDate(LocalDateTime definedDate) {
        this.definedDate = definedDate;
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
