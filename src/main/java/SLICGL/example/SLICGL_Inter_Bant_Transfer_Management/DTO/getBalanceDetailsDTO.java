package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getBalanceDetailsDTO {
    private String accountNumber;
    private float originalBalance;
    private float finalBalance;

    public getBalanceDetailsDTO() {
    }

    public getBalanceDetailsDTO(String accountNumber, float originalBalance, float finalBalance) {
        this.accountNumber = accountNumber;
        this.originalBalance = originalBalance;
        this.finalBalance = finalBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getOriginalBalance() {
        return originalBalance;
    }

    public void setOriginalBalance(float originalBalance) {
        this.originalBalance = originalBalance;
    }

    public float getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(float finalBalance) {
        this.finalBalance = finalBalance;
    }
}
