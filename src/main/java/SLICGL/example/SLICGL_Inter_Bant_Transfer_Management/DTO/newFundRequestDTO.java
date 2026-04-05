package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class newFundRequestDTO {
    private String accountId;
    private String paymentId;
    private BigDecimal requestAmount;
    private Integer requestType; // 0 = Actual Fund Request && 1 = Forecasted Fund Request
    private LocalDate requiredDate;

    public newFundRequestDTO() {
    }

    public newFundRequestDTO(String accountId, String paymentId, BigDecimal requestAmount, Integer requestType, LocalDate requiredDate) {
        this.accountId = accountId;
        this.paymentId = paymentId;
        this.requestAmount = requestAmount;
        this.requestType = requestType;
        this.requiredDate = requiredDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }
}
