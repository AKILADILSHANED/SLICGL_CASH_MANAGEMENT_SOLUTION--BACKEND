package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_option")
public class transferOption {
    @Id
    @Column(name = "option_id", nullable = false, length = 9 /*Ex: OPTN-0001*/)
    private String optionId;
    @Column(name = "is_active", length = 1, nullable = false)
    private int isActive;
    @Column(name = "is_deleted", length = 1, nullable = false)
    private int isDeleted;
    @ManyToOne
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deletedBy;
    @ManyToOne
    @JoinColumn(name = "from_account", nullable = false)
    private bankAccount fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account", nullable = false)
    private bankAccount toAccount;
    @ManyToOne
    @JoinColumn(name = "transfer_channel", nullable = false)
    private transferChannel transferChannel;
    @Column(name = "defined_date", nullable = false)
    private LocalDateTime definedDate;
    @ManyToOne
    @JoinColumn(name = "defined_by", nullable = false)
    private User definedBy;

    public transferOption() {
    }

    public transferOption(String optionId, int isActive, int isDeleted, User deletedBy, bankAccount fromAccount, bankAccount toAccount, transferChannel transferChannel, LocalDateTime definedDate, User definedBy) {
        this.optionId = optionId;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transferChannel = transferChannel;
        this.definedDate = definedDate;
        this.definedBy = definedBy;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }

    public bankAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(bankAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public bankAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(bankAccount toAccount) {
        this.toAccount = toAccount;
    }

    public transferChannel getTransferChannel() {
        return transferChannel;
    }

    public void setTransferChannel(transferChannel transferChannel) {
        this.transferChannel = transferChannel;
    }

    public LocalDateTime getDefinedDate() {
        return definedDate;
    }

    public void setDefinedDate(LocalDateTime definedDate) {
        this.definedDate = definedDate;
    }

    public User getDefinedBy() {
        return definedBy;
    }

    public void setDefinedBy(User definedBy) {
        this.definedBy = definedBy;
    }
}
