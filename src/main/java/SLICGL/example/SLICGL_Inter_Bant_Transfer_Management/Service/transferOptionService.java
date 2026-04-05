package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface transferOptionService {
    public ResponseEntity<customAPIResponse<List<transferOptionDTO>>> displayAvailableOptions(String accountId, String channelId);
    public ResponseEntity<customAPIResponse<String>> saveTransferOptions(List<saveTransferOptionsDTO> savingOptions);
    public ResponseEntity<customAPIResponse<List<deactivateOptionsDTO>>> deactivateOptions();
    public ResponseEntity<customAPIResponse<List<reactivateOptionsDTO>>> reactivateOptions();
    public ResponseEntity<customAPIResponse<String>> saveDeactivation(String optionId);
    public ResponseEntity<customAPIResponse<String>> saveActivation(String optionId);
    public ResponseEntity<customAPIResponse<List<availableTransferOptionsDTO>>> availableOptions();
    public ResponseEntity<customAPIResponse<getOptionForDeleteDTO>> getOptionForDelete(String fromAccount, String toAccount, String transferChannel);
    public ResponseEntity<customAPIResponse<String>> saveOptionDelete(String optionId);
}
