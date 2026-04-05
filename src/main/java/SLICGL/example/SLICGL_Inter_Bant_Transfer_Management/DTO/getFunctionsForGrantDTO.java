package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getFunctionsForGrantDTO {
    private String functionId;
    private String moduleName;
    private String functionName;


    public getFunctionsForGrantDTO() {
    }

    public getFunctionsForGrantDTO(String functionId, String moduleName, String functionName) {
        this.functionId = functionId;
        this.moduleName = moduleName;
        this.functionName = functionName;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
