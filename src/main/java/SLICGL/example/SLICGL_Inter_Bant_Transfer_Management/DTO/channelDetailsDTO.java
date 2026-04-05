package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class channelDetailsDTO {
    private String channelId;
    private String channelType;
    private String shortKey;
    private int priorityLevel;
    private LocalDateTime createdDate;
    private String deletedStatus;
    private String deleteBy;
    private String definedBy;

    public channelDetailsDTO() {
    }

    public channelDetailsDTO(String channelId, String channelType, String shortKey, int priorityLevel, LocalDateTime createdDate, String deletedStatus, String deleteBy, String definedBy) {
        this.channelId = channelId;
        this.channelType = channelType;
        this.shortKey = shortKey;
        this.priorityLevel = priorityLevel;
        this.createdDate = createdDate;
        this.deletedStatus = deletedStatus;
        this.deleteBy = deleteBy;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(String deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    public String getDefinedBy() {
        return definedBy;
    }

    public void setDefinedBy(String definedBy) {
        this.definedBy = definedBy;
    }
}
