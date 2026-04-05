package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getTransactionDetailsDTO {
    private int totalTransfers;
    private int approvedTransfers;
    private int pendingTransfers;
    private int rejectedTransfers;
    private int ibtTransfers;
    private int ceftTransfers;
    private int rtgsTransfers;
    private int chequeTransfers;

    public getTransactionDetailsDTO() {
    }

    public getTransactionDetailsDTO(int totalTransfers, int approvedTransfers, int pendingTransfers, int rejectedTransfers, int ibtTransfers, int ceftTransfers, int rtgsTransfers, int chequeTransfers) {
        this.totalTransfers = totalTransfers;
        this.approvedTransfers = approvedTransfers;
        this.pendingTransfers = pendingTransfers;
        this.rejectedTransfers = rejectedTransfers;
        this.ibtTransfers = ibtTransfers;
        this.ceftTransfers = ceftTransfers;
        this.rtgsTransfers = rtgsTransfers;
        this.chequeTransfers = chequeTransfers;
    }

    public int getTotalTransfers() {
        return totalTransfers;
    }

    public void setTotalTransfers(int totalTransfers) {
        this.totalTransfers = totalTransfers;
    }

    public int getApprovedTransfers() {
        return approvedTransfers;
    }

    public void setApprovedTransfers(int approvedTransfers) {
        this.approvedTransfers = approvedTransfers;
    }

    public int getPendingTransfers() {
        return pendingTransfers;
    }

    public void setPendingTransfers(int pendingTransfers) {
        this.pendingTransfers = pendingTransfers;
    }

    public int getRejectedTransfers() {
        return rejectedTransfers;
    }

    public void setRejectedTransfers(int rejectedTransfers) {
        this.rejectedTransfers = rejectedTransfers;
    }

    public int getIbtTransfers() {
        return ibtTransfers;
    }

    public void setIbtTransfers(int ibtTransfers) {
        this.ibtTransfers = ibtTransfers;
    }

    public int getCeftTransfers() {
        return ceftTransfers;
    }

    public void setCeftTransfers(int ceftTransfers) {
        this.ceftTransfers = ceftTransfers;
    }

    public int getRtgsTransfers() {
        return rtgsTransfers;
    }

    public void setRtgsTransfers(int rtgsTransfers) {
        this.rtgsTransfers = rtgsTransfers;
    }

    public int getChequeTransfers() {
        return chequeTransfers;
    }

    public void setChequeTransfers(int chequeTransfers) {
        this.chequeTransfers = chequeTransfers;
    }
}
