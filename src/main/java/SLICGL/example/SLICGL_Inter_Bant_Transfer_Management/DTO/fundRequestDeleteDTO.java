package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class fundRequestDeleteDTO {
    private String requestId;
    private String accountNumber;
    private String paymentType;
    private float requestAmount;
    private LocalDateTime requestDate;
    private LocalDate requiredDate;
    private String requestType;

    public fundRequestDeleteDTO() {
    }

    public fundRequestDeleteDTO(String requestId, String accountNumber, String paymentType, float requestAmount, LocalDateTime requestDate, LocalDate requiredDate, String requestType) {
        this.requestId = requestId;
        this.accountNumber = accountNumber;
        this.paymentType = paymentType;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.requestType = requestType;
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
