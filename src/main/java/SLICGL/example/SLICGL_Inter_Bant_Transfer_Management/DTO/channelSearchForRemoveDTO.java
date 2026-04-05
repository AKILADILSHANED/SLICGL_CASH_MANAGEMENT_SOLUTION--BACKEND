package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class channelSearchForRemoveDTO {
    private String channelId;
    private String channelType;
    private String shortKey;
    private int priorityLevel;
    private int deleteStatus;
    private LocalDateTime createdDate;
    private String definedBy;

    public channelSearchForRemoveDTO() {
    }

    public channelSearchForRemoveDTO(String channelId, String channelType, String shortKey, int priorityLevel, int deleteStatus, LocalDateTime createdDate, String definedBy) {
        this.channelId = channelId;
        this.channelType = channelType;
        this.shortKey = shortKey;
        this.priorityLevel = priorityLevel;
        this.deleteStatus = deleteStatus;
        this.createdDate = createdDate;
        this.definedBy = definedBy;
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

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDefinedBy() {
        return definedBy;
    }

    public void setDefinedBy(String definedBy) {
        this.definedBy = definedBy;
    }
}
