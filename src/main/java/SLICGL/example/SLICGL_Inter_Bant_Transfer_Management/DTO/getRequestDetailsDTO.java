package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class getRequestDetailsDTO {
    private String paymentType;
    private BigDecimal amount;
    private String bankAccount;
    private LocalDate requestDate;
    private LocalDate requiredDate;
    private String requestedBy;

    public getRequestDetailsDTO() {
    }

    public getRequestDetailsDTO(String paymentType, BigDecimal amount, String bankAccount, LocalDate requestDate, LocalDate requiredDate, String requestedBy) {
        this.paymentType = paymentType;
        this.amount = amount;
        this.bankAccount = bankAccount;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.requestedBy = requestedBy;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
