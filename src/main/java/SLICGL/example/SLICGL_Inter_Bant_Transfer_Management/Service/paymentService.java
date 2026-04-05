package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getPaymentsForFundRequestDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentRegisterDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentSearchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface paymentService {
    public ResponseEntity<customAPIResponse<paymentRegisterDTO>> paymentRegister(String paymentType);
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPayment(String paymentId);
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPaymentForUpdate(String paymentId);
    public ResponseEntity<customAPIResponse<String>> updatePayment(String paymentType, String paymentId);
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPaymentForDelete(String paymentId);
    public ResponseEntity<customAPIResponse<String>> deletePayment(String paymentId);
    public ResponseEntity<customAPIResponse<List<getPaymentsForFundRequestDTO>>> getPaymentsForFundRequest();
}
