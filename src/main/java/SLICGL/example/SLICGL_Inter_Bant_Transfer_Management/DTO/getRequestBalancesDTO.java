package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getRequestBalancesDTO {
    private String requestId;
    private String payment;
    private String amount;

    public getRequestBalancesDTO() {
    }

    public getRequestBalancesDTO(String requestId, String payment, String amount) {
        this.requestId = requestId;
        this.payment = payment;
        this.amount = amount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
