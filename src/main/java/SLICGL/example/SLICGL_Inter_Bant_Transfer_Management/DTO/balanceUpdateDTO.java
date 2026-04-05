package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class balanceUpdateDTO {
    private String balanceId;
    private Float balanceAmount;
    private Float outstandingBalance;
    private String action;
    private Float adjustmentAmount;

    public balanceUpdateDTO() {
    }

    public balanceUpdateDTO(String balanceId, Float balanceAmount, Float outstandingBalance, String action, Float adjustmentAmount) {
        this.balanceId = balanceId;
        this.balanceAmount = balanceAmount;
        this.outstandingBalance = outstandingBalance;
        this.action = action;
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public Float getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Float balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Float getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(Float outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Float getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(Float adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }
}
