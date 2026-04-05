package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class getFundRequestForReverseDTO {
    private String requestId;
    private String accountNumber;
    private String paymentType;
    private String requestBy;
    private float requestAmount;
    private LocalDateTime requestDate;
    private LocalDate requiredDate;

    public getFundRequestForReverseDTO() {
    }

    public getFundRequestForReverseDTO(String requestId, String accountNumber, String paymentType, String requestBy, float requestAmount, LocalDateTime requestDate, LocalDate requiredDate) {
        this.requestId = requestId;
        this.accountNumber = accountNumber;
        this.paymentType = paymentType;
        this.requestBy = requestBy;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public float getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(float requestAmount) {
        this.requestAmount = requestAmount;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }
}
