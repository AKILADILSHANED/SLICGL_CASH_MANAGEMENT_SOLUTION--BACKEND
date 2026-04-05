package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface fundTransferService {
    public ResponseEntity<customAPIResponse<String>> initiateTransfers();

    public ResponseEntity<customAPIResponse<getTransferForTransferIdDTO>> getTransferForTransferId(String transferId);

    public ResponseEntity<customAPIResponse<List<getAllTransfersDTO>>> getAllTransfers(LocalDate transferDate);

    public ResponseEntity<customAPIResponse<List<getAllTransferForApproveDTO>>> getAllTransferForApprove();

    public ResponseEntity<customAPIResponse<String>> approveTransfer(String transferId);

    public ResponseEntity<customAPIResponse<List<rejectTransfersDTO>>> rejectTransfers(LocalDate transferDate);

    public ResponseEntity<customAPIResponse<String>> saveCheckRejection(String transferId);

    public ResponseEntity<customAPIResponse<List<reverseTransfersDTO>>> reverseTransfers(LocalDate transferDate);

    public ResponseEntity<customAPIResponse<String>> saveReversal(String transferId);

    public ResponseEntity<customAPIResponse<String>> reverseAll(LocalDate transferDate);

    public ResponseEntity<customAPIResponse<List<getAllTransfersForCheckDTO>>> getAllTransfersForCheck();

    public ResponseEntity<customAPIResponse<String>> checkTransfer(String transferId);

    public ResponseEntity<customAPIResponse<String>> saveApprovalRejection(String transferId);

    public ResponseEntity<customAPIResponse<String>> initiateManualTransfers(@RequestBody manualFundTransferDTO manualFundTransfer);
}
