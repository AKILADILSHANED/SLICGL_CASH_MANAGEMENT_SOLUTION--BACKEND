package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This will auto increment the id
    @Column(name = "session_id", length = 10, nullable = false)
    private int sessionId;
    @Column(name = "session_token", unique = true, length = 36, nullable = false)
    private String sessionToken;
    @Column(name = "ip_address", length = 45, nullable = false)
    private String ipAddress;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "last_activity_at", nullable = false)
    private LocalDateTime lastActivityAt;
    @Column(name = "expires_at", nullable = true)
    private LocalDateTime expiresAt;
    @Column(name = "session_status", nullable = false)
    private String sessionStatus;
    @ManyToOne
    @JoinColumn(name = "session_user", nullable = false)
    private User registeredBy;

    public Session() {
    }

    public Session(int sessionId, String sessionToken, String ipAddress, LocalDateTime createdAt, LocalDateTime lastActivityAt, LocalDateTime expiresAt, String sessionStatus, User registeredBy) {
        this.sessionId = sessionId;
        this.sessionToken = sessionToken;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
        this.lastActivityAt = lastActivityAt;
        this.expiresAt = expiresAt;
        this.sessionStatus = sessionStatus;
        this.registeredBy = registeredBy;
    }

    public Session(String sessionToken, String ipAddress, LocalDateTime createdAt, LocalDateTime lastActivityAt, LocalDateTime expiresAt, String sessionStatus, User registeredBy) {
        this.sessionToken = sessionToken;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
        this.lastActivityAt = lastActivityAt;
        this.expiresAt = expiresAt;
        this.sessionStatus = sessionStatus;
        this.registeredBy = registeredBy;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(LocalDateTime lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }
}
