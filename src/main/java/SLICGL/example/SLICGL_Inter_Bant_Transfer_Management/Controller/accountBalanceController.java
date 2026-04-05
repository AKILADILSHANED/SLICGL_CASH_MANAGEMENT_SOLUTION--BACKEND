package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.accountBalanceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account-balance")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class accountBalanceController {
    @Autowired
    accountBalanceIMPL accountBalanceService;

    @GetMapping("/get-accounts")
    public ResponseEntity<customAPIResponse<List<balanceEnterDTO>>> getAvailableAccounts() {
        return accountBalanceService.getAvailableAccounts();
    }

    @PostMapping(value = "/save-balance")
    public ResponseEntity<customAPIResponse<String>> saveBalance(@RequestParam String accountId, @RequestParam Float balanceAmount) {
        return accountBalanceService.saveBalance(accountId, balanceAmount);
    }

    @GetMapping(value = "/get-balances")
    public ResponseEntity<customAPIResponse<List<getAllBalancesDTO>>> getAllBalances(@RequestParam LocalDate balanceDate) {
        return accountBalanceService.getAllBalances(balanceDate);
    }

    @GetMapping(value = "/balance-update")
    public ResponseEntity<customAPIResponse<getBalanceForUpdateDTO>> getBalanceForUpdate(String balanceId) {
        return accountBalanceService.getBalanceForUpdate(balanceId);
    }

    @PostMapping("/save-balance-update")
    public ResponseEntity<customAPIResponse<balanceUpdateDTO>> updateBalance(@RequestBody balanceUpdateDTO updatedBalance) {
        return accountBalanceService.updateBalance(updatedBalance);
    }

    @GetMapping(value = "/balance-delete")
    public ResponseEntity<customAPIResponse<getBalanceForDeleteDTO>> getBalanceForDelete(String balanceId) {
        return accountBalanceService.getBalanceForDelete(balanceId);
    }

    @PostMapping("/save-balance-delete")
    public ResponseEntity<customAPIResponse<String>> deleteBalance(String balanceId) {
        return accountBalanceService.deleteBalance(balanceId);
    }

    @GetMapping("/get-adjustments")
    public ResponseEntity<customAPIResponse<List<balanceAdjustmentsDTO>>> getAdjustments(@RequestParam String balanceId) {
        return accountBalanceService.getAdjustments(balanceId);
    }

    @GetMapping(value = "/get-account-balances")
    public ResponseEntity<customAPIResponse<List<getAccountBalancesDTO>>> getRepoOpeningBalances(@RequestParam LocalDate balanceDate) {
        return accountBalanceService.getAccountBalances(balanceDate);
    }

    @GetMapping(value = "/get-overdraft-balances")
    public ResponseEntity<customAPIResponse<List<getOverdraftBalancesDTO>>> getOverdraftBalances(@RequestParam LocalDate balanceDate) {
        return accountBalanceService.getOverdraftBalances(balanceDate);
    }

    @GetMapping(value = "/get-available-balance")
    public ResponseEntity<customAPIResponse<BigDecimal>> getAvailableBalances(@RequestParam String accountId) {
        return accountBalanceService.getAvailableBalances(accountId);
    }
}
