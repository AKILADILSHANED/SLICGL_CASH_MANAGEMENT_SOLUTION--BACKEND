package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getTransferListDTO {
    private String transferId;
    private String fromBank;
    private String fromAccount;
    private String toBank;
    private String toAccount;
    private float transferAmount;
    private String channel;

    public getTransferListDTO() {
    }

    public getTransferListDTO(String transferId, String fromBank, String fromAccount, String toBank, String toAccount, float transferAmount, String channel) {
        this.transferId = transferId;
        this.fromBank = fromBank;
        this.fromAccount = fromAccount;
        this.toBank = toBank;
        this.toAccount = toAccount;
        this.transferAmount = transferAmount;
        this.channel = channel;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
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
}
