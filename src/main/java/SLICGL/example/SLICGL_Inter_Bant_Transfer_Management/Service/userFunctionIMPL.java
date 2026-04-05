package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.grantFunctionDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.revokeFunctionDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityGrantingFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityRevokingFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class userFunctionIMPL implements userFunctionService {
    @Autowired
    JdbcTemplate template;

    @Override
    @Session()
    @Transactional
    @RequiresPermission("FUNC-059")
    @LogActivity(methodDescription = "This method fill grant authorities for user functions")
    public ResponseEntity<customAPIResponse<String>> grantFunction(grantFunctionDTO grantFunctionDTO) {
        //Check whether user provided all required data
        if (grantFunctionDTO.getUserId() == null || grantFunctionDTO.getUserId().isEmpty() || grantFunctionDTO.getFunctionId() == null || grantFunctionDTO.getFunctionId().isEmpty()) {
            throw new AuthorityInputDataViolationException("Please provide required data");
        } else {
            String sql = "INSERT INTO user_function (user_id, function_id) VALUES (?, ?)";
            int rows = template.update(sql, grantFunctionDTO.getUserId(), grantFunctionDTO.getFunctionId());
            if (rows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Function granted successfully for user: " + grantFunctionDTO.getUserId(),
                                null
                        )
                );
            } else {
                throw new AuthorityGrantingFailureException("Unable to grant authority. Please contact administrator");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-060")
    @LogActivity(methodDescription = "This method fill revoke authorities for user functions")
    public ResponseEntity<customAPIResponse<String>> revokeFunction(revokeFunctionDTO revokeFunctionDTO) {
        //Check whether user provided all required data
        if (revokeFunctionDTO.getUserId() == null || revokeFunctionDTO.getUserId().isEmpty() || revokeFunctionDTO.getFunctionId() == null || revokeFunctionDTO.getFunctionId().isEmpty()) {
            throw new AuthorityInputDataViolationException("Please provide required data");
        } else {
            String sql = "DELETE FROM user_function WHERE user_id = ? AND function_id = ?";
            int rows = template.update(sql, revokeFunctionDTO.getUserId(), revokeFunctionDTO.getFunctionId());
            if (rows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Function revoked successfully for user: " + revokeFunctionDTO.getUserId(),
                                null
                        )
                );
            } else {
                throw new AuthorityRevokingFailureException("Unable to revoke authority. Please contact administrator");
            }
        }
    }
}
