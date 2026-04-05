package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class displayRepoDTO {
    private String repoId;
    private String accountNumber;
    private String openingBalance;
    private String closingBalance;
    private String maturityValue;
    private String interestRate;
    private String repoType;
    private String investmentStatus;
    private String investDate;
    private String maturityDate;
    private String createdDate;
    private String createdBy;
    private String deleteStatus;
    private String deleteUser;

    public displayRepoDTO() {
    }

    public displayRepoDTO(String repoId, String accountNumber, String openingBalance, String closingBalance, String maturityValue, String interestRate, String repoType, String investmentStatus, String investDate, String maturityDate, String createdDate, String createdBy, String deleteStatus, String deleteUser) {
        this.repoId = repoId;
        this.accountNumber = accountNumber;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.maturityValue = maturityValue;
        this.interestRate = interestRate;
        this.repoType = repoType;
        this.investmentStatus = investmentStatus;
        this.investDate = investDate;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.deleteStatus = deleteStatus;
        this.deleteUser = deleteUser;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getMaturityValue() {
        return maturityValue;
    }

    public void setMaturityValue(String maturityValue) {
        this.maturityValue = maturityValue;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getInvestmentStatus() {
        return investmentStatus;
    }

    public void setInvestmentStatus(String investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public String getInvestDate() {
        return investDate;
    }

    public void setInvestDate(String investDate) {
        this.investDate = investDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }
}
