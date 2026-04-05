package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.transferOptionIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transferOption")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class transferOptionController {
    @Autowired
    transferOptionIMPL transferOption;
    @GetMapping(value = "/display-options")
    public ResponseEntity<customAPIResponse<List<transferOptionDTO>>> displayAvailableOptions(@RequestParam String accountId, @RequestParam String channelId){
        return transferOption.displayAvailableOptions(accountId, channelId);
    }
    @PostMapping(value = "/save-transferOptions")
    public ResponseEntity<customAPIResponse<String>> saveTransferOptions(@RequestBody List<saveTransferOptionsDTO> savingOptions){
        return transferOption.saveTransferOptions(savingOptions);
    }
    @GetMapping(value = "/option-deactivate")
    public ResponseEntity<customAPIResponse<List<deactivateOptionsDTO>>> deactivateOptions(){
        return transferOption.deactivateOptions();
    }
    @GetMapping(value = "/option-reactivate")
    public ResponseEntity<customAPIResponse<List<reactivateOptionsDTO>>> reactivateOptions(){
        return transferOption.reactivateOptions();
    }
    @PutMapping(value = "/save-deactivation")
    public ResponseEntity<customAPIResponse<String>> saveDeactivation(@RequestParam String optionId){
        return transferOption.saveDeactivation(optionId);
    }
    @PutMapping(value = "/save-activation")
    public ResponseEntity<customAPIResponse<String>> saveActivation(@RequestParam String optionId){
        return transferOption.saveActivation(optionId);
    }
    @GetMapping(value = "/displayAvailableOptions")
    public ResponseEntity<customAPIResponse<List<availableTransferOptionsDTO>>> availableOptions(){
        return transferOption.availableOptions();
    }
    @GetMapping(value = "/displayAvailableOptionsForDelete")
    public ResponseEntity<customAPIResponse<getOptionForDeleteDTO>> getOptionForDelete(@RequestParam String fromAccount, String toAccount, String transferChannel){
        return transferOption.getOptionForDelete(fromAccount, toAccount, transferChannel);
    }
    @PutMapping(value = "/delete-option")
    public ResponseEntity<customAPIResponse<String>> saveOptionDelete(@RequestParam String optionId){
        return transferOption.saveOptionDelete(optionId);
    }
}
