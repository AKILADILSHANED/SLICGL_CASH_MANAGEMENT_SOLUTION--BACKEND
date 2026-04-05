package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class accountBalanceListDTO {
    private String accountId;
    private String accountNumber;
    private String balanceId;
    private BigDecimal finalBalance;

    public accountBalanceListDTO() {
    }

    public accountBalanceListDTO(String accountId, String accountNumber, String balanceId, BigDecimal finalBalance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balanceId = balanceId;
        this.finalBalance = finalBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public BigDecimal getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(BigDecimal finalBalance) {
        this.finalBalance = finalBalance;
    }
}
