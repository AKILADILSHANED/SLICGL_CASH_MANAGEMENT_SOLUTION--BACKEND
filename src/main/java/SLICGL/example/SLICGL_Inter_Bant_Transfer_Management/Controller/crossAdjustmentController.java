package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.crossAdjustmentIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cross-adjustment")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class crossAdjustmentController {
    @Autowired
    crossAdjustmentIMPL crossAdjustmentIMPL;

    @GetMapping("/get-transferId-list")
    public ResponseEntity<customAPIResponse<List<String>>> getCrossAdjustmentList(@RequestParam String balanceId){
        return crossAdjustmentIMPL.getCrossAdjustmentList(balanceId);
    }
}
