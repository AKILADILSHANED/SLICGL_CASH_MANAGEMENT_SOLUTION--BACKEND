package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDateTime;

public class paymentRegisterDTO {
    private String paymentId;
    private String paymentType;
    private LocalDateTime registeredDate;
    private String registeredBy;

    public paymentRegisterDTO() {
    }

    public paymentRegisterDTO(String paymentId, String paymentType, LocalDateTime registeredDate, String registeredBy) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.registeredDate = registeredDate;
        this.registeredBy = registeredBy;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }
}
