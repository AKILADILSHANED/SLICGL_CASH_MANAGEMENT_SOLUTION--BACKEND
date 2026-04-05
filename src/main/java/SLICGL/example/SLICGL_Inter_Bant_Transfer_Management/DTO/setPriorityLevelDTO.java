package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class setPriorityLevelDTO {
    private String channelId;
    private String channelType;
    private String shortKey;
    private int priorityLevel;

    public setPriorityLevelDTO() {
    }

    public setPriorityLevelDTO(String channelId, String channelType, String shortKey, int priorityLevel) {
        this.channelId = channelId;
        this.channelType = channelType;
        this.shortKey = shortKey;
        this.priorityLevel = priorityLevel;
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

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}
