package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_channel")
public class transferChannel {
    @Id
    @Column(name = "channel_id", nullable = false, length = 7 /*Ex: CHNL-01*/)
    private String channelId;
    @Column(name = "channel_type", length = 50, nullable = false)
    private String channelType;
    @Column(name = "short_key", length = 20, nullable = false)
    private String shortKey;
    @Column(name = "priorityLevel", length = 2, nullable = false)
    private int priorityLevel;
    @Column(name = "created_date")
    private LocalDateTime channelDate;
    @Column(name = "deleted_status", length = 1, nullable = false)
    private int deleteStatus;
    @ManyToOne
    @JoinColumn(name = "delete_by", nullable = true)
    private User deleteBy;
    @ManyToOne
    @JoinColumn(name = "defined_by", nullable = false)
    private User definedBy;

    public transferChannel() {
    }

    public transferChannel(String channelId, String channelType, String shortKey, int priorityLevel, LocalDateTime channelDate, int deleteStatus, User deleteBy, User definedBy) {
        this.channelId = channelId;
        this.channelType = channelType;
        this.shortKey = shortKey;
        this.priorityLevel = priorityLevel;
        this.channelDate = channelDate;
        this.deleteStatus = deleteStatus;
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

    public LocalDateTime getChannelDate() {
        return channelDate;
    }

    public void setChannelDate(LocalDateTime channelDate) {
        this.channelDate = channelDate;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(User deleteBy) {
        this.deleteBy = deleteBy;
    }

    public User getDefinedBy() {
        return definedBy;
    }

    public void setDefinedBy(User definedBy) {
        this.definedBy = definedBy;
    }
}
