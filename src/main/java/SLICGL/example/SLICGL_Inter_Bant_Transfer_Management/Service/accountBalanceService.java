package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface accountBalanceService {
    public ResponseEntity<customAPIResponse<List<balanceEnterDTO>>> getAvailableAccounts();

    public ResponseEntity<customAPIResponse<String>> saveBalance(String accountId, Float balanceAmount);

    public ResponseEntity<customAPIResponse<List<getAllBalancesDTO>>> getAllBalances(LocalDate balanceDate);

    public ResponseEntity<customAPIResponse<getBalanceForUpdateDTO>> getBalanceForUpdate(String balanceId);

    public ResponseEntity<customAPIResponse<balanceUpdateDTO>> updateBalance(balanceUpdateDTO updatedBalance);

    public ResponseEntity<customAPIResponse<getBalanceForDeleteDTO>> getBalanceForDelete(String balanceId);

    public ResponseEntity<customAPIResponse<String>> deleteBalance(String balanceId);

    public ResponseEntity<customAPIResponse<List<balanceAdjustmentsDTO>>> getAdjustments(String balanceId);

    public ResponseEntity<customAPIResponse<List<getAccountBalancesDTO>>> getAccountBalances(LocalDate balanceDate);

    public ResponseEntity<customAPIResponse<List<getOverdraftBalancesDTO>>> getOverdraftBalances(LocalDate balanceDate);

    public ResponseEntity<customAPIResponse<BigDecimal>> getAvailableBalances(String accountId);
}
