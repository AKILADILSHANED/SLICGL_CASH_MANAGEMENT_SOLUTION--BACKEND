package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class getUserModuleDTO {
    private String moduleId;
    private String moduleName;

    public getUserModuleDTO() {
    }

    public getUserModuleDTO(String moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
