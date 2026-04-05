package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getPaymentListDTO {
    private String paymentId;
    private String paymentType;

    public getPaymentListDTO() {
    }

    public getPaymentListDTO(String paymentId, String paymentType) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
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
}
