package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class repoDetailsForInvestmentsDTO {
    private LocalDate repoDate;
    private String repoId;
    private String accountNumber;
    private String repoType;
    private BigDecimal investmentValue;

    public repoDetailsForInvestmentsDTO() {
    }

    public repoDetailsForInvestmentsDTO(LocalDate repoDate, String repoId, String accountNumber, String repoType, BigDecimal investmentValue) {
        this.repoDate = repoDate;
        this.repoId = repoId;
        this.accountNumber = accountNumber;
        this.repoType = repoType;
        this.investmentValue = investmentValue;
    }

    public LocalDate getRepoDate() {
        return repoDate;
    }

    public void setRepoDate(LocalDate repoDate) {
        this.repoDate = repoDate;
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

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public BigDecimal getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(BigDecimal investmentValue) {
        this.investmentValue = investmentValue;
    }
}
