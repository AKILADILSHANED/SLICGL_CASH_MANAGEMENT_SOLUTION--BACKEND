package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.bankAccountIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bank-account")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class BankAccountController {
    @Autowired
    bankAccountIMPL bankAccountService;

    @PostMapping(value = "/account-register")
    public ResponseEntity<customAPIResponse<String>> accountRegister(@RequestBody bankAccountRegisterDTO registerAccount) {
        return bankAccountService.accountRegister(registerAccount);
    }

    @GetMapping("/account-search")
    public ResponseEntity<customAPIResponse<searchAccountDTO>> searchAccountDetails(@RequestParam String accountId) {
        return bankAccountService.searchAccountDetails(accountId);
    }

    @GetMapping("/account-searchForUpdate")
    public ResponseEntity<customAPIResponse<searchAccountUpdateDTO>> searchAccountDetailsForUpdate(@RequestParam String accountId) {
        return bankAccountService.searchAccountDetailsForUpdate(accountId);
    }

    @GetMapping("/account-searchForDelete")
    public ResponseEntity<customAPIResponse<searchAccountUpdateDTO>> searchAccountDetailsForDelete(@RequestParam String accountId) {
        return bankAccountService.searchAccountDetailsForDelete(accountId);
    }

    @PutMapping(value = "/account-update")
    public ResponseEntity<customAPIResponse<String>> updateAccount(@RequestBody accountUpdateDTO updateAccount) {
        return bankAccountService.updateAccount(updateAccount);
    }

    @PutMapping(value = "/account-delete")
    public ResponseEntity<customAPIResponse<String>> deleteAccount(@RequestParam String accountId) {
        return bankAccountService.deleteAccount(accountId);
    }

    @GetMapping(value = "/getBankAccounts")
    public ResponseEntity<customAPIResponse<List<getBankAccountsForFundRequestDTO>>> getBankAccountsForFundRequest() {
        return bankAccountService.getBankAccountsForFundRequest();
    }

    @GetMapping(value = "/checkBankAccountForNewRepo")
    public ResponseEntity<customAPIResponse<Boolean>> checkBankAccountForNewRepo(@RequestParam String newRepoAccountId, @RequestParam String fromRepoId) {
        return bankAccountService.checkBankAccountForNewRepo(newRepoAccountId, fromRepoId);
    }

    @GetMapping(value = "/checkBankAccountForExistingRepo")
    public ResponseEntity<customAPIResponse<Boolean>> checkBankAccountForExistingRepo(@RequestParam String toRepoId, @RequestParam String fromRepoId) {
        return bankAccountService.checkBankAccountForExistingRepo(toRepoId, fromRepoId);
    }

    @GetMapping(value = "/bankAccountsForManualTransfers")
    public ResponseEntity<customAPIResponse<List<bankAccountListForManualTransfersDTO>>> getBankAccountsForManualTransfers() {
        return bankAccountService.getBankAccountsForManualTransfers();
    }
}
