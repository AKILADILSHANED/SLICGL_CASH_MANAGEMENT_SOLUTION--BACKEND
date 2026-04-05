package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class getIbtLetterDetailsDTO {
    private String fromBank;
    private String fromAccount;
    private String fromBranch;
    private String toBank;
    private String toAccount;
    private String toBranch;
    private BigDecimal transferAmount;
    private String valueDate;

    public getIbtLetterDetailsDTO() {
    }

    public getIbtLetterDetailsDTO(String fromBank, String fromAccount, String fromBranch, String toBank, String toAccount, String toBranch, BigDecimal transferAmount, String valueDate) {
        this.fromBank = fromBank;
        this.fromAccount = fromAccount;
        this.fromBranch = fromBranch;
        this.toBank = toBank;
        this.toAccount = toAccount;
        this.toBranch = toBranch;
        this.transferAmount = transferAmount;
        this.valueDate = valueDate;
    }

    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromBranch() {
        return fromBranch;
    }

    public void setFromBranch(String fromBranch) {
        this.fromBranch = fromBranch;
    }

    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToBranch() {
        return toBranch;
    }

    public void setToBranch(String toBranch) {
        this.toBranch = toBranch;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }
}
