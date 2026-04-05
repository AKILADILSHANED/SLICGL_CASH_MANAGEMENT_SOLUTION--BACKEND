package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class balanceAdjustmentsDTO {
    private String adjustmentId;
    private String crossAdjustment;
    private float adjustmentAmount;
    private String remark;
    private float openingBalance;
    private float closingBalance;
    private String balanceId;
    private String bankAccount;


    public balanceAdjustmentsDTO() {
    }

    public balanceAdjustmentsDTO(String adjustmentId, String crossAdjustment, float adjustmentAmount, String remark, float openingBalance, float closingBalance, String balanceId, String bankAccount) {
        this.adjustmentId = adjustmentId;
        this.crossAdjustment = crossAdjustment;
        this.adjustmentAmount = adjustmentAmount;
        this.remark = remark;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.balanceId = balanceId;
        this.bankAccount = bankAccount;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getCrossAdjustment() {
        return crossAdjustment;
    }

    public void setCrossAdjustment(String crossAdjustment) {
        this.crossAdjustment = crossAdjustment;
    }

    public float getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(float adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(float openingBalance) {
        this.openingBalance = openingBalance;
    }

    public float getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(float closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
