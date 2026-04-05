package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface crossAdjustmentService {
    public String generateNewCrossAdjustmentId();
    public String saveNewCrossAdjustment();
    public ResponseEntity<customAPIResponse<List<String>>> getCrossAdjustmentList(String balanceId);
}
