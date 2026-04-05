package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class transferChanelForTransferHistory {
    private String channelId;
    private String channelType;

    public transferChanelForTransferHistory() {
    }

    public transferChanelForTransferHistory(String channelId, String channelType) {
        this.channelId = channelId;
        this.channelType = channelType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
