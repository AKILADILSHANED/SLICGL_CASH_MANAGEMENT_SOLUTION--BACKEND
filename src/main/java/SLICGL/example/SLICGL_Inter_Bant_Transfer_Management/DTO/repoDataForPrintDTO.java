package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class repoDataForPrintDTO {
    private String interestRate;
    private String investmentDate;
    private String maturityDate;
    private String investmentValue;
    private String numberOfDays;
    private String bankAccount;
    private String bank;
    private String bankBranch;

    public repoDataForPrintDTO() {
    }

    public repoDataForPrintDTO(String interestRate, String investmentDate, String maturityDate, String investmentValue, String numberOfDays, String bankAccount, String bank, String bankBranch) {
        this.interestRate = interestRate;
        this.investmentDate = investmentDate;
        this.maturityDate = maturityDate;
        this.investmentValue = investmentValue;
        this.numberOfDays = numberOfDays;
        this.bankAccount = bankAccount;
        this.bank = bank;
        this.bankBranch = bankBranch;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(String investmentDate) {
        this.investmentDate = investmentDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(String investmentValue) {
        this.investmentValue = investmentValue;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
}
