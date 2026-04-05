package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class manualFundTransferDTO {
    private String fromAccount;
    private String fromRepo;
    private String toAccount;
    private String toRepo;
    private String channel;
    private BigDecimal amount;

    public manualFundTransferDTO() {
    }

    public manualFundTransferDTO(String fromAccount, String fromRepo, String toAccount, String toRepo, String channel, BigDecimal amount) {
        this.fromAccount = fromAccount;
        this.fromRepo = fromRepo;
        this.toAccount = toAccount;
        this.toRepo = toRepo;
        this.channel = channel;
        this.amount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromRepo() {
        return fromRepo;
    }

    public void setFromRepo(String fromRepo) {
        this.fromRepo = fromRepo;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToRepo() {
        return toRepo;
    }

    public void setToRepo(String toRepo) {
        this.toRepo = toRepo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
