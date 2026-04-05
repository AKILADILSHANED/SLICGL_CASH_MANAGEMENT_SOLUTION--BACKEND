package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class repoDetailsForTransferDTO {
    private String repoId;
    private String accountId;
    private BigDecimal finalRepoValue;

    public repoDetailsForTransferDTO(String repoId, String accountId, BigDecimal finalRepoValue) {
        this.repoId = repoId;
        this.accountId = accountId;
        this.finalRepoValue = finalRepoValue;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getFinalRepoValue() {
        return finalRepoValue;
    }

    public void setFinalRepoValue(BigDecimal finalRepoValue) {
        this.finalRepoValue = finalRepoValue;
    }
}
