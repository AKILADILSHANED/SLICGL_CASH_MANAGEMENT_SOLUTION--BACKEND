package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "repos")
public class Repos {
        @Id
        @Column(name = "Repo_ID", length = 15, nullable = false) // REPO-202507-001
        private String repoId;
        @Column(name = "Created_Date", nullable = false)
        private LocalDateTime repoCreatedDate;
        @Column(name = "Invest_Date", nullable = true)
        private LocalDate investmentDate;
        @Column(name = "Maturity_Date", nullable = true)
        private LocalDate maturityDate;
        @Column(name = "Repo_Value", precision = 13, scale = 2, nullable = false)
        private BigDecimal repoValue;
        @Column(name = "Maturity_Value", precision = 13, scale = 2, nullable = false)
        private BigDecimal maturityValue;
        @Column(name = "Calculation_Method", length = 1, nullable = true) // 0 = Actual *364, 1 = Actual *365
        private String calculationMethod;
        @Column(name = "Interest_Rate", length = 5, nullable = true)
        private String interestRate;
        @Column(name = "transfer_eligibility", length = 1, nullable = false)
        private int eligibilityTransfers;
        @Column(name = "Repo_Type", length = 10, nullable = false) // 1 = Par, 2 = Non_Par, 3 = TR, 4 = Excess
        private int repoType;
        @Column(name = "Is_Invested", length = 1, nullable = false) // 1 = Invested, 0 = Not invested;
        private int isInvested;
        @Column(name = "Is_Deleted", length = 1, nullable = false) // 0 = Active, 1 = Deleted
        private int isDeleted;
        @ManyToOne
        @JoinColumn(name = "Deleted_By", nullable = true)
        private User deletedBy;
        @ManyToOne
        @JoinColumn(name = "Created_By", nullable = false)
        private User createdBy;
        @ManyToOne
        @JoinColumn(name = "Bank_Account", nullable = false)
        private bankAccount Account;

        public Repos() {
        }

    public Repos(String repoId, LocalDateTime repoCreatedDate, LocalDate investmentDate, LocalDate maturityDate, BigDecimal repoValue, BigDecimal maturityValue, String calculationMethod, String interestRate, int eligibilityTransfers, int repoType, int isInvested, int isDeleted, User deletedBy, User createdBy, bankAccount account) {
        this.repoId = repoId;
        this.repoCreatedDate = repoCreatedDate;
        this.investmentDate = investmentDate;
        this.maturityDate = maturityDate;
        this.repoValue = repoValue;
        this.maturityValue = maturityValue;
        this.calculationMethod = calculationMethod;
        this.interestRate = interestRate;
        this.eligibilityTransfers = eligibilityTransfers;
        this.repoType = repoType;
        this.isInvested = isInvested;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.createdBy = createdBy;
        Account = account;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public LocalDateTime getRepoCreatedDate() {
        return repoCreatedDate;
    }

    public void setRepoCreatedDate(LocalDateTime repoCreatedDate) {
        this.repoCreatedDate = repoCreatedDate;
    }

    public LocalDate getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(LocalDate investmentDate) {
        this.investmentDate = investmentDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public BigDecimal getRepoValue() {
        return repoValue;
    }

    public void setRepoValue(BigDecimal repoValue) {
        this.repoValue = repoValue;
    }

    public BigDecimal getMaturityValue() {
        return maturityValue;
    }

    public void setMaturityValue(BigDecimal maturityValue) {
        this.maturityValue = maturityValue;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public int getEligibilityTransfers() {
        return eligibilityTransfers;
    }

    public void setEligibilityTransfers(int eligibilityTransfers) {
        this.eligibilityTransfers = eligibilityTransfers;
    }

    public int getRepoType() {
        return repoType;
    }

    public void setRepoType(int repoType) {
        this.repoType = repoType;
    }

    public int getIsInvested() {
        return isInvested;
    }

    public void setIsInvested(int isInvested) {
        this.isInvested = isInvested;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public bankAccount getAccount() {
        return Account;
    }

    public void setAccount(bankAccount account) {
        Account = account;
    }
}
