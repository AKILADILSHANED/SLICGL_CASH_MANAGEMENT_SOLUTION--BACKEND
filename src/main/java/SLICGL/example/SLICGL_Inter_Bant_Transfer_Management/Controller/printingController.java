package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.printingIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
@RequestMapping(value = "/api/v1/printing")
public class printingController {
    @Autowired
    printingIMPL printing;

    @GetMapping(value = "/printIBTSheet")
    public ResponseEntity<customAPIResponse<List<printIBTSheetDTO>>> printIBTSheet(@RequestParam LocalDate sheetDate){
        return printing.printIBTSheet(sheetDate);
    }
    @GetMapping(value = "/getTransferList")
    public ResponseEntity<customAPIResponse<List<getTransferListDTO>>> getTransferList(@RequestParam LocalDate transferDate){
        return printing.getTransferList(transferDate);
    }
    @GetMapping(value = "/getVoucherDetails")
    public ResponseEntity<customAPIResponse<getVoucherDetailsDTO>> getVoucherDetails(@RequestParam String voucherNo){
        return printing.getVoucherDetails(voucherNo);
    }

    @GetMapping(value = "/ibt-letter-details")
    public ResponseEntity<customAPIResponse<getIbtLetterDetailsDTO>> getIbtLetterDetails(@RequestParam String voucherNo){
        return printing.getIbtLetterDetails(voucherNo);
    }

    @GetMapping(value = "/rtgs-letter-details")
    public ResponseEntity<customAPIResponse<getIbtLetterDetailsDTO>> getRtgsLetterDetails(@RequestParam String voucherNo){
        return printing.getIbtLetterDetails(voucherNo);
    }

    @GetMapping(value = "/repo-letter-details")
    public ResponseEntity<customAPIResponse<List<repoDataForPrintDTO>>> getRepoDataForPrint(@RequestParam String repoId){
        return printing.getRepoDataForPrint(repoId);
    }
}
