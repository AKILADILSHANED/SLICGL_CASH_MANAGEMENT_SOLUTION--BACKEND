package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface fundRequestService {
    public ResponseEntity<customAPIResponse<String>> newFundRequest(newFundRequestDTO fundRequest);
    public ResponseEntity<customAPIResponse<List<getFundRequestDetailsDTO>>> requestDetails(LocalDate requiredDate, String requestType);
    public ResponseEntity<customAPIResponse<searchRequestForUpdateDTO>> searchRequestForUpdate(String requestId, String requestType);
    public ResponseEntity<customAPIResponse<String>> saveUpdateFundRequest(updateFundRequestDTO updatedRequest);
    public ResponseEntity<customAPIResponse<fundRequestDeleteDTO>> deleteFundRequest(String requestId, Integer requestType);
    public ResponseEntity<customAPIResponse<String>> saveDeleteFundRequest(String requestId, Integer requestType);
    public ResponseEntity<customAPIResponse<List<getFundRequestForApproveDTO>>> getFundRequestForApprove();
    public ResponseEntity<customAPIResponse<String>> approveRequest(String requestId);
    public ResponseEntity<customAPIResponse<List<getFundRequestForReverseDTO>>> getFundRequestForReverse();
    public ResponseEntity<customAPIResponse<String>> reverseApproval(String requestId);
    public ResponseEntity<customAPIResponse<List<getRequestBalancesDTO>>> getRequestBalances(LocalDate requestDate);
}
