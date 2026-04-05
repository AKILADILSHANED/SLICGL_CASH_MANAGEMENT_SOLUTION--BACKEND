package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getChannelsForDefineOptionsDTO {
    private String channelId;
    private String channelType;
    private String shortKey;

    public getChannelsForDefineOptionsDTO() {
    }

    public getChannelsForDefineOptionsDTO(String channelId, String channelType, String shortKey) {
        this.channelId = channelId;
        this.channelType = channelType;
        this.shortKey = shortKey;
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

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }
}
