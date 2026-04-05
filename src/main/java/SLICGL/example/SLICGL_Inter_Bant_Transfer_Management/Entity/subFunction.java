package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "sub_function")
public class subFunction {
    @Id
    @Column(name = "Function_ID", length = 8, nullable = false)
    private String functionId;
    @Column(name = "Function_Name", length = 30, nullable = false)
    private String functionName;
    @ManyToOne
    @JoinColumn(name = "module", nullable = false)
    private userModule moduleId;
    @ManyToMany(mappedBy = "subFunctions")
    private List<User> users;

    public subFunction() {
    }

    public subFunction(String functionId, String functionName, userModule moduleId) {
        this.functionId = functionId;
        this.functionName = functionName;
        this.moduleId = moduleId;
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

    public userModule getModuleId() {
        return moduleId;
    }

    public void setModuleId(userModule moduleId) {
        this.moduleId = moduleId;
    }
}
