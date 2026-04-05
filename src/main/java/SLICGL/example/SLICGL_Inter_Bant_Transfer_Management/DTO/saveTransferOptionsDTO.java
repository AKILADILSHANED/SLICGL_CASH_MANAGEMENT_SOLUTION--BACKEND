package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;
public class saveTransferOptionsDTO {

    private String fromAccountId;
    private String toAccountId;
    private String channelId;

    public saveTransferOptionsDTO() {
    }

    public saveTransferOptionsDTO(String fromAccountId, String toAccountId, String channelId) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.channelId = channelId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
