package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class adjustmentExistingRepoDTO {
    private String fromRepo;
    private String toRepo;
    private BigDecimal repoValue;
    private String transferChanel;

    public adjustmentExistingRepoDTO() {
    }

    public adjustmentExistingRepoDTO(String fromRepo, String toRepo, BigDecimal repoValue, String transferChanel) {
        this.fromRepo = fromRepo;
        this.toRepo = toRepo;
        this.repoValue = repoValue;
        this.transferChanel = transferChanel;
    }

    public String getFromRepo() {
        return fromRepo;
    }

    public void setFromRepo(String fromRepo) {
        this.fromRepo = fromRepo;
    }

    public String getToRepo() {
        return toRepo;
    }

    public void setToRepo(String toRepo) {
        this.toRepo = toRepo;
    }

    public BigDecimal getRepoValue() {
        return repoValue;
    }

    public void setRepoValue(BigDecimal repoValue) {
        this.repoValue = repoValue;
    }

    public String getTransferChanel() {
        return transferChanel;
    }

    public void setTransferChanel(String transferChanel) {
        this.transferChanel = transferChanel;
    }
}
