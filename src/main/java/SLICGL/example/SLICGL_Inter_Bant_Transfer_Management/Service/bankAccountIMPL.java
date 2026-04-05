package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bankAccount;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountNotUpdateException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.accountSearch;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.searchAccountUpdate;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class bankAccountIMPL implements bankAccountService {
    @Autowired
    bankAccountRepo bankAccountRepository;
    @Autowired
    HttpSession session;
    @Autowired
    UserRepo userRepository;
    @Autowired
    bankRepo bankRepository;
    @Autowired
    JdbcTemplate template;
    @Autowired
    repoRepository repoRepository;
    @Autowired
    transferOptionRepository transferOptionRepository;
    @Autowired
    HttpRequestUtil httpRequest;

    private static final Logger logger = LoggerFactory.getLogger(bankAccountIMPL.class);

    @Transactional
    @Override
    @Session()
    @RequiresPermission(value = "FUNC-005")
    @LogActivity(methodDescription = "This method will register a new bank account")
    public ResponseEntity<customAPIResponse<String>> accountRegister(bankAccountRegisterDTO registerAccount) {
        if (registerAccount.getBank().isEmpty() || registerAccount.getBankBranch().isEmpty() || registerAccount.getAccountType() == null || registerAccount.getCurrency().isEmpty() || registerAccount.getGlCode() == null || registerAccount.getAccountNumber().isEmpty()) {
            throw new AccountInputDataViolationException("Please provide all required data for successful Bank Account registration.");
        } else {
            //Create new account id;
            String newAccountId;
            String lastAccountId = bankAccountRepository.getLastAccountId();
            //Check whether an account id is already available in the bank account table;
            if (lastAccountId == null) {
                //If not account id available;
                newAccountId = "ACC-001";
            } else {
                //If account id is available;
                int newNumericID = Integer.parseInt(lastAccountId.replace("ACC-", "")) + 1;
                newAccountId = String.format("ACC-%03d", newNumericID);
            }
            //Create new bank account entity to save to database;
            bankAccount account = new bankAccount(
                    newAccountId,
                    registerAccount.getBankBranch(),
                    registerAccount.getAccountType(),
                    registerAccount.getCurrency(),
                    bankRepository.findById(registerAccount.getBank()).get(),
                    registerAccount.getGlCode(),
                    registerAccount.getAccountNumber(),
                    0,
                    null,
                    LocalDateTime.now(),
                    userRepository.findById(httpRequest.getSessionUser()).get()
            );
            bankAccountRepository.save(account);
            customAPIResponse<String> response = new customAPIResponse<>(
                    true,
                    "Bank Account registered successfully with Account ID: " + newAccountId + ".",
                    null
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    @Session()
    @RequiresPermission(value = "FUNC-006")
    @LogActivity(methodDescription = "This method will display bank account details")
    public ResponseEntity<customAPIResponse<searchAccountDTO>> searchAccountDetails(String accountId) {
        //Check whether the user has been provided an Account ID;
        if (accountId.isEmpty()) {
            throw new AccountInputDataViolationException("Please provide Account ID for successful searching.");
        } else {
            //Check whether the provided Account ID is valid or not;
            String Sql = "SELECT ACC.account_id, bnk.bank_name, ACC.bank_branch, ACC.account_number, ACC.delete_status, CASE WHEN ACC.account_type = '1' THEN 'Current Account' WHEN ACC.account_type = '2' THEN 'Saving Account' END AS 'accountType', ACC.currency, ACC.gl_code, ACC.registered_date, USR.user_first_name FROM bank_account ACC LEFT JOIN user USR ON ACC.registered_by = USR.user_id LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id WHERE ACC.account_id = ?";
            List<searchAccountDTO> searchedAccount = template.query(Sql, new Object[]{accountId}, new accountSearch());
            //Check whether any account available for user provided account id;
            if (searchedAccount.isEmpty()) {
                throw new AccountInputDataViolationException("Provided Account ID is invalid. Please provide valid Account ID.");
            } else {
                //Check whether the existing record is already deleted or not;
                if (searchedAccount.get(0).getDeleteStatus() == 0) {
                    customAPIResponse<searchAccountDTO> accountAvailability = new customAPIResponse<>(
                            true,
                            null,
                            searchedAccount.get(0)
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(accountAvailability);
                } else {
                    customAPIResponse<searchAccountDTO> accountAvailability = new customAPIResponse<>(
                            false,
                            "This Account ID: " + searchedAccount.get(0).getAccountId() + " is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(accountAvailability);
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission(value = "FUNC-007")
    @LogActivity(methodDescription = "This method will display bank account details for update")
    public ResponseEntity<customAPIResponse<searchAccountUpdateDTO>> searchAccountDetailsForUpdate(String accountId) {
        //Check whether the user has been provided an Account ID;
        if (accountId.isEmpty()) {
            throw new AccountInputDataViolationException("Please provide Account ID for successful searching.");
        } else {
            String Sql = "SELECT ACC.account_id, bnk.bank_id, bnk.bank_name, ACC.bank_branch, ACC.account_type, ACC.currency, ACC.gl_code, ACC.account_number, ACC.registered_date, USR.user_first_name, ACC.delete_status FROM bank_account ACC LEFT JOIN user USR ON ACC.registered_by = USR.user_id LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id WHERE ACC.account_id = ?";
            List<searchAccountUpdateDTO> searchedAccount = template.query(Sql, new Object[]{accountId}, new searchAccountUpdate());
            //Check whether any account available for user provided account id;
            if (searchedAccount.isEmpty()) {
                throw new AccountInputDataViolationException("Provided Account ID is invalid. Please provide valid Account ID.");
            } else {
                //Check whether the existing record is already deleted or not;
                if (searchedAccount.get(0).getDeleteStatus() == 0) {
                    customAPIResponse<searchAccountUpdateDTO> accountAvailability = new customAPIResponse<>(
                            true,
                            null,
                            searchedAccount.get(0)
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(accountAvailability);
                } else {
                    customAPIResponse<searchAccountUpdateDTO> accountAvailability = new customAPIResponse<>(
                            false,
                            "This Account ID: " + searchedAccount.get(0).getAccountId() + " is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(accountAvailability);
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission(value = "FUNC-007")
    @LogActivity(methodDescription = "This method will update bank account details")
    public ResponseEntity<customAPIResponse<String>> updateAccount(accountUpdateDTO updateAccount) {
        //Check whether the user has been provided updating details;
        if (updateAccount.getBank().isEmpty() || updateAccount.getAccountType() == null || updateAccount.getAccountId().isEmpty() || updateAccount.getAccountNumber().isEmpty() || updateAccount.getBankBranch().isEmpty() || updateAccount.getCurrency().isEmpty() || updateAccount.getGlCode() == null) {
            throw new AccountInputDataViolationException("Please provide Account update details for successful Bank Account updating.");
        } else {
            int affected_rows = bankAccountRepository.updateAccount(
                    updateAccount.getBank(),
                    updateAccount.getBankBranch(),
                    updateAccount.getAccountNumber(),
                    updateAccount.getAccountType(),
                    updateAccount.getCurrency(),
                    updateAccount.getGlCode(),
                    updateAccount.getAccountId()
            );
            if (affected_rows > 0) {
                customAPIResponse<String> response = new customAPIResponse<>(
                        true,
                        "Account ID: " + updateAccount.getAccountId() + " updated successfully.",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                throw new AccountNotUpdateException("Bank Account update process failed. Please contact Administrator.");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission(value = "FUNC-008")
    @LogActivity(methodDescription = "This method will display bank account details for delete")
    public ResponseEntity<customAPIResponse<searchAccountUpdateDTO>> searchAccountDetailsForDelete(String accountId) {
        //Check whether the user has been provided an Account ID;
        if (accountId.isEmpty()) {
            throw new AccountInputDataViolationException("Please provide Account ID for successful searching.");
        } else {
            String Sql = "SELECT ACC.account_id, bnk.bank_id, bnk.bank_name, ACC.bank_branch, ACC.account_type, ACC.currency, ACC.gl_code, ACC.account_number, ACC.registered_date, USR.user_first_name, ACC.delete_status FROM bank_account ACC LEFT JOIN user USR ON ACC.registered_by = USR.user_id LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id WHERE ACC.account_id = ?";
            List<searchAccountUpdateDTO> searchedAccount = template.query(Sql, new Object[]{accountId}, new searchAccountUpdate());
            //Check whether any account available for user provided account id;
            if (searchedAccount.isEmpty()) {
                throw new AccountInputDataViolationException("Provided Account ID is invalid. Please provide valid Account ID.");
            } else {
                //Check whether the existing record is already deleted or not;
                if (searchedAccount.get(0).getDeleteStatus() == 0) {
                    customAPIResponse<searchAccountUpdateDTO> accountAvailability = new customAPIResponse<>(
                            true,
                            null,
                            searchedAccount.get(0)
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(accountAvailability);
                } else {
                    customAPIResponse<searchAccountUpdateDTO> accountAvailability = new customAPIResponse<>(
                            false,
                            "This Account ID: " + searchedAccount.get(0).getAccountId() + " is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(accountAvailability);
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission(value = "FUNC-008")
    @LogActivity(methodDescription = "This method will delete bank account details")
    public ResponseEntity<customAPIResponse<String>> deleteAccount(String accountId) {
        //Check whether the user has been provided Account ID;
        if (accountId.isEmpty()) {
            throw new AccountInputDataViolationException("Please provide Account ID for successful Bank Account deletion.");
        } else {
            //Check whether any Transfer Options have been already defined for deleting Bank Account
            int optionCount = transferOptionRepository.getOptionCount(accountId, accountId);
            if (optionCount >= 1) {
                customAPIResponse<String> response = new customAPIResponse<>(
                        false,
                        "Transfer options have been already defined for this Bank Account. " + optionCount + " options were found.",
                        null
                );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                //Check whether any valid Account ID has been provided or not;
                int affectedRows = bankAccountRepository.deleteAccount(httpRequest.getSessionUser().toString(), accountId);
                if (affectedRows == 0) {
                    throw new AccountInputDataViolationException("No Account number found for provided Account ID: \" + accountId.");
                } else {
                    customAPIResponse<String> response = new customAPIResponse<>(
                            true,
                            "Account ID: " + accountId + " deleted successfully.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will fetch active bank account list")
    public ResponseEntity<customAPIResponse<List<getBankAccountsForFundRequestDTO>>> getBankAccountsForFundRequest() {
        List<getBankAccountsForFundRequestDTO> accountList = bankAccountRepository.getBankAccountsForFundRequest();
        if (!accountList.isEmpty()) {
            customAPIResponse<List<getBankAccountsForFundRequestDTO>> response = new customAPIResponse<>(
                    true,
                    null,
                    accountList
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            throw new AccountNotFoundException("No Bank Accounts found for Fund Requests.");
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will check whether from repo account and new repo account are same or not")
    public ResponseEntity<customAPIResponse<Boolean>> checkBankAccountForNewRepo(String newRepoAccountId, String fromRepoId) {
        //Check whether user provided all required data
        if (newRepoAccountId == null || newRepoAccountId.isEmpty() || fromRepoId == null || fromRepoId.isEmpty()) {
            throw new AccountInputDataViolationException("Please provide all required data");
        } else {
            //Check whether from repo account id is equal with new repo account id;
            if (repoRepository.getFromRepoBankAccountId(fromRepoId).equals(newRepoAccountId) || repoRepository.getFromRepoBankAccountId(fromRepoId) == null || Objects.equals(newRepoAccountId, "")) {
                customAPIResponse<Boolean> response = new customAPIResponse<>(
                        false,
                        null,
                        true
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                customAPIResponse<Boolean> response = new customAPIResponse<>(
                        false,
                        null,
                        false
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will check whether from repo account and to repo account are same or not")
    public ResponseEntity<customAPIResponse<Boolean>> checkBankAccountForExistingRepo(String toRepoId, String fromRepoId) {
        //Check whether user provided all required data
        if (toRepoId == null || toRepoId.isEmpty() || fromRepoId == null || fromRepoId.isEmpty()) {
            throw new AccountInputDataViolationException("Please provide all required data");
        } else {
            //Check whether from repo account id is equal with to repo account id;
            if (repoRepository.getFromRepoBankAccountId(fromRepoId).equals(repoRepository.getFromRepoBankAccountId(toRepoId)) || Objects.equals(toRepoId, "")) {
                customAPIResponse<Boolean> response = new customAPIResponse<>(
                        false,
                        null,
                        true
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                customAPIResponse<Boolean> response = new customAPIResponse<>(
                        false,
                        null,
                        false
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-024")
    @LogActivity(methodDescription = "This method will fetch all bank accounts for manual transfers")
    public ResponseEntity<customAPIResponse<List<bankAccountListForManualTransfersDTO>>> getBankAccountsForManualTransfers() {
        List<bankAccountListForManualTransfersDTO> accountList = bankAccountRepository.getAccountList();
        if (!accountList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            accountList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Bank Accounts found",
                            null
                    )
            );
        }
    }
}
