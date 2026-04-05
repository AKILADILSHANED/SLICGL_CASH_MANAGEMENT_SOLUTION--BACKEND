package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class getRepoReportDTO {
    private String repoId;
    private String createdDate;
    private String investedDate;
    private String maturityDate;
    private String account;
    private BigDecimal openingBalance;
    private BigDecimal maturityValue;
    private String repoType;
    private String interestRate;
    private String investmentStatus;
    private String calculationMethod;
    private String deleteStatus;
    private String deletedBy;
    private String enterBy;

    public getRepoReportDTO() {
    }

    public getRepoReportDTO(String repoId, String createdDate, String investedDate, String maturityDate, String account, BigDecimal openingBalance, BigDecimal maturityValue, String repoType, String interestRate, String investmentStatus, String calculationMethod, String deleteStatus, String deletedBy, String enterBy) {
        this.repoId = repoId;
        this.createdDate = createdDate;
        this.investedDate = investedDate;
        this.maturityDate = maturityDate;
        this.account = account;
        this.openingBalance = openingBalance;
        this.maturityValue = maturityValue;
        this.repoType = repoType;
        this.interestRate = interestRate;
        this.investmentStatus = investmentStatus;
        this.calculationMethod = calculationMethod;
        this.deleteStatus = deleteStatus;
        this.deletedBy = deletedBy;
        this.enterBy = enterBy;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getInvestedDate() {
        return investedDate;
    }

    public void setInvestedDate(String investedDate) {
        this.investedDate = investedDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getMaturityValue() {
        return maturityValue;
    }

    public void setMaturityValue(BigDecimal maturityValue) {
        this.maturityValue = maturityValue;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getInvestmentStatus() {
        return investmentStatus;
    }

    public void setInvestmentStatus(String investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getEnterBy() {
        return enterBy;
    }

    public void setEnterBy(String enterBy) {
        this.enterBy = enterBy;
    }
}
