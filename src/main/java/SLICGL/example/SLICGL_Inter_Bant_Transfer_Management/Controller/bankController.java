package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.bankServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bank")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class bankController {
    @Autowired
    bankServiceIMPL bankService;

    @GetMapping(value = "/bank-list")
    public ResponseEntity<customAPIResponse<List<bank>>> getAllBankList(){
        return bankService.getAllBankList();
    }
}
