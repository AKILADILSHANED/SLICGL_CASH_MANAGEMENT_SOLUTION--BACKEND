package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class adjustmentNewRepoDTO {
    private String fromRepo;
    private int adjustmentType;
    private String repoAccount;
    private int repoType;
    private float repoValue;
    private int eligibility;
    private String transferChanel;

    public adjustmentNewRepoDTO() {
    }

    public adjustmentNewRepoDTO(String fromRepo, int adjustmentType, String repoAccount, int repoType, float repoValue, int eligibility, String transferChanel) {
        this.fromRepo = fromRepo;
        this.adjustmentType = adjustmentType;
        this.repoAccount = repoAccount;
        this.repoType = repoType;
        this.repoValue = repoValue;
        this.eligibility = eligibility;
        this.transferChanel = transferChanel;
    }

    public String getFromRepo() {
        return fromRepo;
    }

    public void setFromRepo(String fromRepo) {
        this.fromRepo = fromRepo;
    }

    public int getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(int adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getRepoAccount() {
        return repoAccount;
    }

    public void setRepoAccount(String repoAccount) {
        this.repoAccount = repoAccount;
    }

    public int getRepoType() {
        return repoType;
    }

    public void setRepoType(int repoType) {
        this.repoType = repoType;
    }

    public float getRepoValue() {
        return repoValue;
    }

    public void setRepoValue(float repoValue) {
        this.repoValue = repoValue;
    }

    public int getEligibility() {
        return eligibility;
    }

    public void setEligibility(int eligibility) {
        this.eligibility = eligibility;
    }

    public String getTransferChanel() {
        return transferChanel;
    }

    public void setTransferChanel(String transferChanel) {
        this.transferChanel = transferChanel;
    }
}
