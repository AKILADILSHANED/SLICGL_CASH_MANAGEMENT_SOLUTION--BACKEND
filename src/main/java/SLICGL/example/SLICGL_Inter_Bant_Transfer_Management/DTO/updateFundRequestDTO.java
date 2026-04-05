package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;

public class updateFundRequestDTO {
    private String requestId;
    private String accountId;
    private BigDecimal requestAmount;
    private BigDecimal outstandingAmount;
    private String paymentType;
    private String adjustmentType;
    private BigDecimal adjustmentAmount;

    public updateFundRequestDTO() {
    }

    public updateFundRequestDTO(String requestId, String accountId, BigDecimal requestAmount, BigDecimal outstandingAmount, String paymentType, String adjustmentType, BigDecimal adjustmentAmount) {
        this.requestId = requestId;
        this.accountId = accountId;
        this.requestAmount = requestAmount;
        this.outstandingAmount = outstandingAmount;
        this.paymentType = paymentType;
        this.adjustmentType = adjustmentType;
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public BigDecimal getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }
}
