package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class paymentReportDTO {
    private String paymentId;
    private String paymentType;
    private String registeredDate;
    private String registeredBy;
    private String status;
    private String deletedBy;

    public paymentReportDTO() {
    }

    public paymentReportDTO(String paymentId, String paymentType, String registeredDate, String registeredBy, String status, String deletedBy) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.registeredDate = registeredDate;
        this.registeredBy = registeredBy;
        this.status = status;
        this.deletedBy = deletedBy;
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
