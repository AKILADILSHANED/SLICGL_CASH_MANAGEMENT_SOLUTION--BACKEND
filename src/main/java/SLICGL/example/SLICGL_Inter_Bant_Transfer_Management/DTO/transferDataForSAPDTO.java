package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class transferDataForSAPDTO {
    private String date;
    private String fromReference;
    private String toReference;
    private float transferAmount;
    private String fromGL;
    private String toGL;
    private String shortKey;

    public transferDataForSAPDTO() {
    }

    public transferDataForSAPDTO(String date, String fromReference, String toReference, float transferAmount, String fromGL, String toGL, String shortKey) {
        this.date = date;
        this.fromReference = fromReference;
        this.toReference = toReference;
        this.transferAmount = transferAmount;
        this.fromGL = fromGL;
        this.toGL = toGL;
        this.shortKey = shortKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromReference() {
        return fromReference;
    }

    public void setFromReference(String fromReference) {
        this.fromReference = fromReference;
    }

    public String getToReference() {
        return toReference;
    }

    public void setToReference(String toReference) {
        this.toReference = toReference;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getFromGL() {
        return fromGL;
    }

    public void setFromGL(String fromGL) {
        this.fromGL = fromGL;
    }

    public String getToGL() {
        return toGL;
    }

    public void setToGL(String toGL) {
        this.toGL = toGL;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }
}
