package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class transferStatusDTO {
    private String checkedStatus;
    private String approveStatus;
    private String userId;

    public transferStatusDTO() {
    }

    public transferStatusDTO(String checkedStatus, String approveStatus, String userId) {
        this.checkedStatus = checkedStatus;
        this.approveStatus = approveStatus;
        this.userId = userId;
    }

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
