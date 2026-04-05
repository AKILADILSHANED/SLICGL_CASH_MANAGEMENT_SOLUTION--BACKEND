package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.grantFunctionDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.revokeFunctionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface userFunctionService {
    public ResponseEntity<customAPIResponse<String>> grantFunction(grantFunctionDTO grantFunctionDTO);
    public ResponseEntity<customAPIResponse<String>> revokeFunction(revokeFunctionDTO revokeFunctionDTO);
}
