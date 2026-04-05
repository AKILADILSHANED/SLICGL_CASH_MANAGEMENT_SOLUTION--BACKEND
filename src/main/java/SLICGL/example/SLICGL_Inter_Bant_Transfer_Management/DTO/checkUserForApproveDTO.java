package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class checkUserForApproveDTO {
    private String initiatedBy;
    private String checkedBy;

    public checkUserForApproveDTO() {
    }

    public checkUserForApproveDTO(String initiatedBy, String checkedBy) {
        this.initiatedBy = initiatedBy;
        this.checkedBy = checkedBy;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }
}
