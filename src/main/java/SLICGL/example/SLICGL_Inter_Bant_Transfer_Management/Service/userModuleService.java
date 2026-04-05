package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFunctionsForGrantDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getUserModuleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface userModuleService {
    public ResponseEntity<customAPIResponse<List<getUserModuleDTO>>> getUserModuleForAuthorityGrant();

    public ResponseEntity<customAPIResponse<List<getUserModuleDTO>>> getUserModuleForAuthorityRevoke();

    public ResponseEntity<customAPIResponse<List<getFunctionsForGrantDTO>>> getFunctionsForGrant(String userId, String moduleId);

    public ResponseEntity<customAPIResponse<List<getFunctionsForGrantDTO>>> getFunctionsForRevoke(String userId, String moduleId);
}
