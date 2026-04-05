package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getOverdraftBalancesDTO {
    private String balanceId;
    private String accountNumber;
    private String overdraftBalance;

    public getOverdraftBalancesDTO() {
    }

    public getOverdraftBalancesDTO(String balanceId, String accountNumber, String overdraftBalance) {
        this.balanceId = balanceId;
        this.accountNumber = accountNumber;
        this.overdraftBalance = overdraftBalance;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOverdraftBalance() {
        return overdraftBalance;
    }

    public void setOverdraftBalance(String overdraftBalance) {
        this.overdraftBalance = overdraftBalance;
    }
}
