package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class searchRequestForUpdateDTO {
    private String requestId;
    private String accountId;
    private String paymentId;
    private float requestAmount;
    private LocalDateTime requestDate;
    private LocalDate requiredDate;
    private String approveStatus;
    private String deleteStatus;
    private List<getBankAccountListDTO> accountList;
    private List<getPaymentListDTO> paymentList;

    public searchRequestForUpdateDTO() {
    }

    public searchRequestForUpdateDTO(String requestId, String accountId, String paymentId, float requestAmount, LocalDateTime requestDate, LocalDate requiredDate, String approveStatus, String deleteStatus, List<getBankAccountListDTO> accountList, List<getPaymentListDTO> paymentList) {
        this.requestId = requestId;
        this.accountId = accountId;
        this.paymentId = paymentId;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.approveStatus = approveStatus;
        this.deleteStatus = deleteStatus;
        this.accountList = accountList;
        this.paymentList = paymentList;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public List<getBankAccountListDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<getBankAccountListDTO> accountList) {
        this.accountList = accountList;
    }

    public List<getPaymentListDTO> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<getPaymentListDTO> paymentList) {
        this.paymentList = paymentList;
    }
}
