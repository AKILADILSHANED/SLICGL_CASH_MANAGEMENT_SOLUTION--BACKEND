package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class deleteCrossAdjustmentDTO {
    private LocalDate adjustmentDate;
    private String transferId;

    public deleteCrossAdjustmentDTO() {
    }

    public deleteCrossAdjustmentDTO(LocalDate adjustmentDate, String transferId) {
        this.adjustmentDate = adjustmentDate;
        this.transferId = transferId;
    }

    public LocalDate getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDate adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }
}
