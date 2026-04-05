package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class getTransferDataForReversalDTO {
    private LocalDate transferDate;
    private String crossAdjustment;
    private int checkedStatus;
    private int approveStatus;

    public getTransferDataForReversalDTO() {
    }

    public getTransferDataForReversalDTO(LocalDate transferDate, String crossAdjustment, int checkedStatus, int approveStatus) {
        this.transferDate = transferDate;
        this.crossAdjustment = crossAdjustment;
        this.checkedStatus = checkedStatus;
        this.approveStatus = approveStatus;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public String getCrossAdjustment() {
        return crossAdjustment;
    }

    public void setCrossAdjustment(String crossAdjustment) {
        this.crossAdjustment = crossAdjustment;
    }

    public int getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(int checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public int getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(int approveStatus) {
        this.approveStatus = approveStatus;
    }
}
