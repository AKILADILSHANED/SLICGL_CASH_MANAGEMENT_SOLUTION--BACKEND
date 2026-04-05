package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface bankAccountService {
    public ResponseEntity<customAPIResponse<String>> accountRegister(bankAccountRegisterDTO registerAccount);

    public ResponseEntity<customAPIResponse<searchAccountDTO>> searchAccountDetails(String accountId);

    public ResponseEntity<customAPIResponse<searchAccountUpdateDTO>> searchAccountDetailsForUpdate(String accountId);

    public ResponseEntity<customAPIResponse<String>> updateAccount(accountUpdateDTO updateAccount);

    public ResponseEntity<customAPIResponse<searchAccountUpdateDTO>> searchAccountDetailsForDelete(String accountId);

    public ResponseEntity<customAPIResponse<String>> deleteAccount(String accountId);

    public ResponseEntity<customAPIResponse<List<getBankAccountsForFundRequestDTO>>> getBankAccountsForFundRequest();

    public ResponseEntity<customAPIResponse<Boolean>> checkBankAccountForNewRepo(String toRepo, String fromRepoId);

    public ResponseEntity<customAPIResponse<Boolean>> checkBankAccountForExistingRepo(String toRepoId, String fromRepoId);

    public ResponseEntity<customAPIResponse<List<bankAccountListForManualTransfersDTO>>> getBankAccountsForManualTransfers();
}
