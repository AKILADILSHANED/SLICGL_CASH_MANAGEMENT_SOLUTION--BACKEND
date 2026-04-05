package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class fundRequestHistoryDTO {
    private String requestId;
    private LocalDateTime requestDate;
    private LocalDate requiredDate;
    private float requestAmount;
    private String accountNumber;
    private String paymentType;
    private String approveStatus;
    private String requestBy;

    public fundRequestHistoryDTO() {
    }

    public fundRequestHistoryDTO(String requestId, LocalDateTime requestDate, LocalDate requiredDate, float requestAmount, String accountNumber, String paymentType, String approveStatus, String requestBy) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.requestAmount = requestAmount;
        this.accountNumber = accountNumber;
        this.paymentType = paymentType;
        this.approveStatus = approveStatus;
        this.requestBy = requestBy;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public float getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(float requestAmount) {
        this.requestAmount = requestAmount;
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

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
}
