package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class revokeFunctionDTO {
    private String userId;
    private String functionId;

    public revokeFunctionDTO() {
    }

    public revokeFunctionDTO(String userId, String functionId) {
        this.userId = userId;
        this.functionId = functionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
