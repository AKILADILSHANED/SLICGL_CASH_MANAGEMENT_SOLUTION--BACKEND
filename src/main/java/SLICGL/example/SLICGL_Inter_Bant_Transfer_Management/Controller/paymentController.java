package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getPaymentsForFundRequestDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentRegisterDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentSearchDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.paymentServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
@RequestMapping(value = "/api/v1/payment")
public class paymentController {
    @Autowired
    paymentServiceIMPL paymentService;

    @PostMapping(value = "/registerPayment")
    public ResponseEntity<customAPIResponse<paymentRegisterDTO>> paymentRegister(@RequestParam String paymentType){
        return paymentService.paymentRegister(paymentType);
    }

    @GetMapping(value = "/payment-search")
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPayment(@RequestParam String paymentId){
        return paymentService.searchPayment(paymentId);
    }

    @GetMapping(value = "/payment-search-for-update")
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPaymentForUpdate(@RequestParam String paymentId){
        return paymentService.searchPaymentForUpdate(paymentId);
    }

    @PutMapping(value = "/payment-update")
    public ResponseEntity<customAPIResponse<String>> updatePayment(@RequestParam String paymentType, @RequestParam String paymentId){
        return paymentService.updatePayment(paymentType, paymentId);
    }

    @GetMapping(value = "/payment-search-for-delete")
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPaymentForDelete(@RequestParam String paymentId){
        return paymentService.searchPaymentForDelete(paymentId);
    }

    @PutMapping(value = "/payment-delete")
    public ResponseEntity<customAPIResponse<String>> deletePayment(@RequestParam String paymentId){
        return paymentService.deletePayment(paymentId);
    }

    @GetMapping(value = "/getPaymentList")
    public ResponseEntity<customAPIResponse<List<getPaymentsForFundRequestDTO>>> getPaymentsForFundRequest(){
        return paymentService.getPaymentsForFundRequest();
    }
}
