package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class getFundRequestDetailsDTO {
    private String requestId;
    private String accountNumber;
    private String paymentType;
    private float requestAmount;
    private LocalDateTime requestDate;
    private LocalDate requiredDate;
    private String approveStatus;
    private String approvedBy;
    private String deleteStatus;
    private String deleted_by;
    private String requestedBy;
    private int requestType;

    public getFundRequestDetailsDTO() {
    }

    public getFundRequestDetailsDTO(String requestId, String accountNumber, String paymentType, float requestAmount, LocalDateTime requestDate, LocalDate requiredDate, String approveStatus, String approvedBy, String deleteStatus, String deleted_by, String requestedBy, int requestType) {
        this.requestId = requestId;
        this.accountNumber = accountNumber;
        this.paymentType = paymentType;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.approveStatus = approveStatus;
        this.approvedBy = approvedBy;
        this.deleteStatus = deleteStatus;
        this.deleted_by = deleted_by;
        this.requestedBy = requestedBy;
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

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
}
