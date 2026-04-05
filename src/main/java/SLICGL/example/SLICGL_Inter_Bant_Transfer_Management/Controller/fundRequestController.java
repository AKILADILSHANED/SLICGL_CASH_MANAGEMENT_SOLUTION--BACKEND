package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.fundRequestIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
@RequestMapping(value = "/api/v1/fund-request")
public class fundRequestController {

    @Autowired
    fundRequestIMPL fundRequestService;

    @PostMapping(value = "/new-request")
    public ResponseEntity<customAPIResponse<String>> newFundRequest(@RequestBody newFundRequestDTO fundRequest){
        return fundRequestService.newFundRequest(fundRequest);
    }

    @GetMapping(value = "/get-fundRequest-details")
    public ResponseEntity<customAPIResponse<List<getFundRequestDetailsDTO>>> requestDetails(@RequestParam LocalDate requiredDate, @RequestParam String requestType){
        return fundRequestService.requestDetails(requiredDate, requestType);
    }

    @GetMapping(value = "/fundRequest-update")
    public ResponseEntity<customAPIResponse<searchRequestForUpdateDTO>> searchRequestForUpdate(@RequestParam String requestId, @RequestParam String requestType){
        return fundRequestService.searchRequestForUpdate(requestId, requestType);
    }

    @PutMapping(value = "/save-requestUpdate")
    public ResponseEntity<customAPIResponse<String>> saveUpdateFundRequest(@RequestBody updateFundRequestDTO updatedRequest){
        return fundRequestService.saveUpdateFundRequest(updatedRequest);
    }

    @GetMapping(value = "/delete-request")
    public ResponseEntity<customAPIResponse<fundRequestDeleteDTO>> deleteFundRequest(@RequestParam String requestId, @RequestParam Integer requestType){
        return fundRequestService.deleteFundRequest(requestId, requestType);
    }

    @PutMapping(value = "/save-requestDelete")
    public ResponseEntity<customAPIResponse<String>> saveDeleteFundRequest(@RequestParam String requestId, @RequestParam Integer requestType){
        return fundRequestService.saveDeleteFundRequest(requestId, requestType);
    }

    @GetMapping(value = "/getRequestDetails-approve")
    public ResponseEntity<customAPIResponse<List<getFundRequestForApproveDTO>>> getFundRequestForApprove(){
        return fundRequestService.getFundRequestForApprove();
    }

    @PutMapping(value = "/approve-request")
    public ResponseEntity<customAPIResponse<String>> approveRequest(@RequestParam String requestId){
        return fundRequestService.approveRequest(requestId);
    }

    @GetMapping(value = "/getRequestDetails-reverse")
    public ResponseEntity<customAPIResponse<List<getFundRequestForReverseDTO>>> getFundRequestForReverse(){
        return fundRequestService.getFundRequestForReverse();
    }

    @PutMapping(value = "/reverse-approval")
    public ResponseEntity<customAPIResponse<String>> reverseApproval(@RequestParam String requestId){
        return fundRequestService.reverseApproval(requestId);
    }

    @GetMapping(value = "/get-request-balances")
    public ResponseEntity<customAPIResponse<List<getRequestBalancesDTO>>> getRequestBalances(@RequestParam LocalDate requestDate){
        return fundRequestService.getRequestBalances(requestDate);
    }
}
