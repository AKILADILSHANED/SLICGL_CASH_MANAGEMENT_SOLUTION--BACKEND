package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getCrossAdjustmentDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoAdjustmentsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface repoAdjustmentService {
    public String generateAdjustmentId();
    public String newRemark(String remarkAccount, BigDecimal adjustmentAmount);
    public void saveNewAdjustment(String remarkAccount, BigDecimal adjustmentAmount, String adjustmentRepo, String transferId, String crossAdjustment);
    public ResponseEntity<customAPIResponse<List<repoAdjustmentsDTO>>> getAdjustments(String repoId);
    public ResponseEntity<customAPIResponse<List<getCrossAdjustmentDetailsDTO>>> adjustmentDetails(String adjustmentId);
    public ResponseEntity<customAPIResponse<String>> adjustmentDelete(String adjustmentId);
    public ResponseEntity<customAPIResponse<List<String>>> showAdjustments(String repoId);
}
