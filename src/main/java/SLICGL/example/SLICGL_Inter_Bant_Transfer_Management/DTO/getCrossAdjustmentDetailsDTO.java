package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class getCrossAdjustmentDetailsDTO {
    private String adjustmentId;
    private LocalDate adjustmentDate;
    private String status;

    public getCrossAdjustmentDetailsDTO() {
    }

    public getCrossAdjustmentDetailsDTO(String adjustmentId, LocalDate adjustmentDate, String status) {
        this.adjustmentId = adjustmentId;
        this.adjustmentDate = adjustmentDate;
        this.status = status;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public LocalDate getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDate adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
