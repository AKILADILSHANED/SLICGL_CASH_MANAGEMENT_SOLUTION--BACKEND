package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getForecastingDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRequestDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransactionDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface dashBoardService {
    public ResponseEntity<customAPIResponse<getTransactionDetailsDTO>> getTransactionDetails();
    public ResponseEntity<customAPIResponse<List<getForecastingDetailsDTO>>> getForecastingDetails();
    public ResponseEntity<customAPIResponse<List<getBalanceDetailsDTO>>> getBalanceDetails();public
    ResponseEntity<customAPIResponse<List<getRequestDetailsDTO>>> getFundRequestDetails();
}
