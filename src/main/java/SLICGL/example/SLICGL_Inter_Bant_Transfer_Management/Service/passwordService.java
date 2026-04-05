package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import org.springframework.http.ResponseEntity;

public interface passwordService {
    public ResponseEntity<customAPIResponse<String>> unlockPassword(String userId);
    public ResponseEntity<customAPIResponse<String>> resetPassword(String userId);
}
