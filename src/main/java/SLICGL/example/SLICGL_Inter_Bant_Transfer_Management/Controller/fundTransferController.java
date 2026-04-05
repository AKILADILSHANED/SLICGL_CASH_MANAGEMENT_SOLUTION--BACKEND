package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.fundTransferIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transfers")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class fundTransferController {

    @Autowired
    fundTransferIMPL fundTransfers;

    @PostMapping(value = "/initiate-transfers")
    public ResponseEntity<customAPIResponse<String>> initiateTransfers() {

        return fundTransfers.initiateTransfers();
    }

    @GetMapping(value = "/display-transfer")
    public ResponseEntity<customAPIResponse<getTransferForTransferIdDTO>> getTransferForTransferId(@RequestParam String transferId) {
        return fundTransfers.getTransferForTransferId(transferId);
    }

    @GetMapping(value = "/get-all-transfers")
    public ResponseEntity<customAPIResponse<List<getAllTransfersDTO>>> getAllTransfers(@RequestParam LocalDate transferDate) {
        return fundTransfers.getAllTransfers(transferDate);
    }

    @GetMapping(value = "/get-approve-list")
    public ResponseEntity<customAPIResponse<List<getAllTransferForApproveDTO>>> getAllTransferForApprove() {
        return fundTransfers.getAllTransferForApprove();
    }

    @PutMapping(value = "/approve-transfer")
    public ResponseEntity<customAPIResponse<String>> approveTransfer(@RequestParam String transferId) {
        return fundTransfers.approveTransfer(transferId);
    }

    @GetMapping(value = "/reject-transfers")
    public ResponseEntity<customAPIResponse<List<rejectTransfersDTO>>> rejectTransfers(@RequestParam LocalDate transferDate) {
        return fundTransfers.rejectTransfers(transferDate);
    }

    @PutMapping(value = "/update-checking-rejection")
    public ResponseEntity<customAPIResponse<String>> saveCheckRejection(@RequestParam String transferId) {
        return fundTransfers.saveCheckRejection(transferId);
    }

    @PutMapping(value = "/update-approval-rejection")
    public ResponseEntity<customAPIResponse<String>> saveApprovalRejection(@RequestParam String transferId) {
        return fundTransfers.saveApprovalRejection(transferId);
    }

    @GetMapping(value = "/reverse-transfers")
    public ResponseEntity<customAPIResponse<List<reverseTransfersDTO>>> reverseTransfers(@RequestParam LocalDate transferDate) {
        return fundTransfers.reverseTransfers(transferDate);
    }

    @PutMapping(value = "/update-reversal")
    public ResponseEntity<customAPIResponse<String>> saveReversal(@RequestParam String transferId) {
        return fundTransfers.saveReversal(transferId);
    }

    @PutMapping(value = "/reverse-all")
    public ResponseEntity<customAPIResponse<String>> reverseAll(@RequestParam LocalDate transferDate) {
        return fundTransfers.reverseAll(transferDate);
    }

    @GetMapping(value = "/get-check-list")
    public ResponseEntity<customAPIResponse<List<getAllTransfersForCheckDTO>>> getAllTransfersForCheck() {
        return fundTransfers.getAllTransfersForCheck();
    }

    @PutMapping(value = "/check-transfer")
    public ResponseEntity<customAPIResponse<String>> checkTransfer(@RequestParam String transferId) {
        return fundTransfers.checkTransfer(transferId);
    }

    @PostMapping(value = "/initiate-manual-transfers")
    public ResponseEntity<customAPIResponse<String>> initiateManualTransfers(@RequestBody manualFundTransferDTO manualFundTransfer) {
        return fundTransfers.initiateManualTransfers(manualFundTransfer);
    }
}
