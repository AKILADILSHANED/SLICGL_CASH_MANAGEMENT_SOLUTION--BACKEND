package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getAccountBalancesDTO {
    private String accountNumber;
    private String balance;

    public getAccountBalancesDTO() {
    }

    public getAccountBalancesDTO(String accountNumber, String balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
