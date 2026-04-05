package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class repoAdjustmentsDTO {
    private String adjustmentId;
    private String adjustmentAmount;
    private String remark;
    private String crossAdjustment;
    private String openingBalance;
    private String closingBalance;
    private String repoId;
    private String bankAccount;

    public repoAdjustmentsDTO() {
    }

    public repoAdjustmentsDTO(String adjustmentId, String adjustmentAmount, String remark, String crossAdjustment, String openingBalance, String closingBalance, String repoId, String bankAccount) {
        this.adjustmentId = adjustmentId;
        this.adjustmentAmount = adjustmentAmount;
        this.remark = remark;
        this.crossAdjustment = crossAdjustment;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.repoId = repoId;
        this.bankAccount = bankAccount;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCrossAdjustment() {
        return crossAdjustment;
    }

    public void setCrossAdjustment(String crossAdjustment) {
        this.crossAdjustment = crossAdjustment;
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

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
