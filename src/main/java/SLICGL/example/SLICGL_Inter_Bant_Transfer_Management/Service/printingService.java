package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface printingService {
    public ResponseEntity<customAPIResponse<List<printIBTSheetDTO>>> printIBTSheet(LocalDate sheetDate);
    public ResponseEntity<customAPIResponse<List<getTransferListDTO>>> getTransferList(LocalDate transferDate);
    public ResponseEntity<customAPIResponse<getVoucherDetailsDTO>> getVoucherDetails(String voucherNo);
    public ResponseEntity<customAPIResponse<getIbtLetterDetailsDTO>> getIbtLetterDetails(String voucherNo);
    public ResponseEntity<customAPIResponse<getIbtLetterDetailsDTO>> getRtgsLetterDetails(String voucherNo);
    public ResponseEntity<customAPIResponse<List<repoDataForPrintDTO>>> getRepoDataForPrint(String repoId);
}
