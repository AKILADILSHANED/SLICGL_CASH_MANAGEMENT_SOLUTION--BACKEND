package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class getForecastingDetailsDTO {
    private String paymentType;
    private String bankAccount;
    private float amount;
    private LocalDate date;
    private String requestedBy;
    private float totalAmount;

    public getForecastingDetailsDTO() {
    }

    public getForecastingDetailsDTO(String paymentType, String bankAccount, float amount, LocalDate date, String requestedBy, float totalAmount) {
        this.paymentType = paymentType;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.date = date;
        this.requestedBy = requestedBy;
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
