package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFunctionsForGrantDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getUserModuleDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.userModuleRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.GetAllSubFunctionsMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.GetAllUserFunctionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class userModuleIMPL implements userModuleService {
    @Autowired
    userModuleRepo userModuleRepository;
    @Autowired
    JdbcTemplate template;

    @Override
    @Session()
    @RequiresPermission("FUNC-059")
    @LogActivity(methodDescription = "This method fill fetch user module list for grant authorities")
    public ResponseEntity<customAPIResponse<List<getUserModuleDTO>>> getUserModuleForAuthorityGrant() {
        List<getUserModuleDTO> moduleList = userModuleRepository.getModuleList();
        if (moduleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No User Modules found",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            moduleList
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-060")
    @LogActivity(methodDescription = "This method fill fetch user module list for revoke authorities")
    public ResponseEntity<customAPIResponse<List<getUserModuleDTO>>> getUserModuleForAuthorityRevoke() {
        List<getUserModuleDTO> moduleList = userModuleRepository.getModuleList();
        if (moduleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No User Modules found",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            moduleList
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-059")
    @LogActivity(methodDescription = "This method fill fetch all subfunctions for grant authorities")
    public ResponseEntity<customAPIResponse<List<getFunctionsForGrantDTO>>> getFunctionsForGrant(String userId, String moduleId) {
        //Check whether user provided all required data
        if (userId == null || userId.isEmpty() || moduleId == null || moduleId.isEmpty()) {
            throw new AuthorityInputDataViolationException("Please provide all required data");
        } else {
            //Get all sub functions related to provided module;
            String SqlGetAllSubFunctions = "SELECT SUB.function_id, MODULE.module_name, SUB.function_name FROM sub_function SUB LEFT JOIN user_module MODULE ON SUB.module = MODULE.module_id WHERE SUB.module = ? ORDER BY SUB.function_id ASC";
            List<getFunctionsForGrantDTO> allSubFunctionList = template.query(SqlGetAllSubFunctions, new GetAllSubFunctionsMapper(), moduleId);

            //Get all user available functions related;
            String SqlGetAllUserFunctions = "SELECT function_id FROM user_function WHERE user_id = ?";
            List<String> allUserFunctionList = template.query(SqlGetAllUserFunctions, new GetAllUserFunctionsMapper(), userId);

            Iterator<getFunctionsForGrantDTO> iterator = allSubFunctionList.iterator();
            while (iterator.hasNext()) {
                getFunctionsForGrantDTO subFunc = iterator.next();
                if (allUserFunctionList.contains(subFunc.getFunctionId().trim())) {
                    iterator.remove();
                }
            }

            if (allSubFunctionList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No functions available for grant",
                                null
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                allSubFunctionList
                        )
                );
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-060")
    @LogActivity(methodDescription = "This method fill fetch all subfunctions for revoke authorities")
    public ResponseEntity<customAPIResponse<List<getFunctionsForGrantDTO>>> getFunctionsForRevoke(String userId, String moduleId) {
        //Check whether user provided all required data
        if (userId == null || userId.isEmpty() || moduleId == null || moduleId.isEmpty()) {
            throw new AuthorityInputDataViolationException("Please provide all required data");
        } else {
            //Get all sub functions related to provided module;
            String SqlGetAllSubFunctions = "SELECT SUB.function_id, MODULE.module_name, SUB.function_name FROM sub_function SUB LEFT JOIN user_module MODULE ON SUB.module = MODULE.module_id WHERE SUB.module = ? ORDER BY SUB.function_id ASC";
            List<getFunctionsForGrantDTO> allSubFunctionList = template.query(SqlGetAllSubFunctions, new GetAllSubFunctionsMapper(), moduleId);

            //Get all user available functions related;
            String SqlGetAllUserFunctions = "SELECT function_id FROM user_function WHERE user_id = ?";
            List<String> allUserFunctionList = template.query(SqlGetAllUserFunctions, new GetAllUserFunctionsMapper(), userId);

            Iterator<getFunctionsForGrantDTO> iterator = allSubFunctionList.iterator();
            while (iterator.hasNext()) {
                getFunctionsForGrantDTO subFunc = iterator.next();
                if (!allUserFunctionList.contains(subFunc.getFunctionId().trim())) {
                    iterator.remove();
                }
            }

            if (allSubFunctionList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No functions available for revoke",
                                null
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                allSubFunctionList
                        )
                );
            }
        }
    }
}
