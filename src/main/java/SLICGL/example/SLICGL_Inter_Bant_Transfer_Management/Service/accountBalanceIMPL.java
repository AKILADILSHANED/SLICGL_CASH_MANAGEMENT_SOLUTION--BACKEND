package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.accountBalance;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AccountBalanceExceptions.BalanceInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AccountBalanceExceptions.BalanceNotUpdateException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.LogoutFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.TokenNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.TransferExceptions.TransferInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.accountBalanceAdjustmentsRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.accountBalanceRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.bankAccountRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class accountBalanceIMPL implements accountBalanceService {
    @Autowired
    accountBalanceRepo accountBalanceRepository;

    @Autowired
    bankAccountRepo bankAccountRepository;

    @Autowired
    UserRepo userRepository;

    @Autowired
    HttpSession session;

    @Autowired
    JdbcTemplate template;

    @Autowired
    accountBalanceAdjustmentsRepo accountBalanceAdjustmentsRepository;

    @Autowired
    HttpRequestUtil httpRequest;

    private static final Logger logger = LoggerFactory.getLogger(accountBalanceIMPL.class);

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will fetch active bank accounts")
    public ResponseEntity<customAPIResponse<List<balanceEnterDTO>>> getAvailableAccounts() {
        List<balanceEnterDTO> newBalanceList = new ArrayList<>();
        //Get all available bank accounts;
        List<balanceEnterDTO> listOfAccounts = bankAccountRepository.getAllBankAccounts();
        //Loop each account_ids and check whether any balance is entered already for current date;
        for (balanceEnterDTO currentAccountObj : listOfAccounts) {
            String availableBalanceId = accountBalanceRepository.getBalanceIdForBalanceEnter(currentAccountObj.getAccountId(), LocalDate.now());
            if (availableBalanceId == null) {
                newBalanceList.add(currentAccountObj);
            } else {
                continue;
            }
        }
        if (newBalanceList.isEmpty()) {
            customAPIResponse<List<balanceEnterDTO>> response = new customAPIResponse<>(
                    false,
                    "No Bank Accounts found for enter balances.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            customAPIResponse<List<balanceEnterDTO>> response = new customAPIResponse<>(
                    true,
                    null,
                    newBalanceList
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-009")
    @LogActivity(methodDescription = "This method will save account balance")
    public ResponseEntity<customAPIResponse<String>> saveBalance(String accountId, Float balanceAmount) {
        //Check whether user provided the both account id and balance amount
        if (accountId.isEmpty() || balanceAmount == null) {
            throw new BalanceInputDataViolationException("Please provide the values for both Account ID and Balance Amount.");
        } else {
            //Check whether the user has been provided valid account id
            String ID = bankAccountRepository.findById(accountId).get().getAccountId();
            if (bankAccountRepository.accountAvailability(accountId) == 0) {
                throw new BalanceInputDataViolationException("Incorrect Account ID. Please provide valid Account ID.");
            } else {
                String newBalanceId;
                //Create variable for store current year
                String currentYear = String.valueOf(LocalDate.now().getYear());
                //Create variable for store current month;
                String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
                //Check whether any account balance record is available in account balance table;
                String lastBalanceId = accountBalanceRepository.getLastBalanceId();
                if (lastBalanceId == null) {
                    newBalanceId = "BAL-" + currentYear + currentMonth + "-0001";
                } else {
                    //Get year of the last balance id;
                    String lastYear = lastBalanceId.substring(4, 8);
                    //Get month of the last balance id;
                    String lastMonth = lastBalanceId.substring(8, 10);
                    //Check whether the year and month of the last balance id is equal to the  current year and month;
                    if (lastYear.equals(currentYear) && lastMonth.equals(currentMonth)) {
                        //Get last four characters in last balance_id;
                        int numericPart = Integer.parseInt(lastBalanceId.substring(lastBalanceId.length() - 4)) + 1;
                        String newNumericPart = String.format("%04d", numericPart);
                        newBalanceId = "BAL-" + currentYear + currentMonth + "-" + newNumericPart;
                    } else {
                        newBalanceId = "BAL-" + currentYear + currentMonth + "-0001";
                    }
                }
                //Create new account balance object;
                accountBalanceRepository.save(new accountBalance(
                        newBalanceId,
                        LocalDate.now(),
                        balanceAmount,
                        0,
                        userRepository.findById(httpRequest.getSessionUser()).get(),
                        bankAccountRepository.findById(accountId).get(),
                        null
                ));
                customAPIResponse<String> response = new customAPIResponse<>(
                        true,
                        "Account Balance saved successfully with Balance ID: " + newBalanceId + ".",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-010")
    @LogActivity(methodDescription = "This method will display account balance details")
    public ResponseEntity<customAPIResponse<List<getAllBalancesDTO>>> getAllBalances(LocalDate balanceDate) {
        //Check whether user provided a value for Balance Date
        if (balanceDate == null) {
            throw new BalanceInputDataViolationException("Please provide a Balance Date to display Account Balances.");
        } else {
            String Sql = "SELECT BAL.balance_id, BNK.bank_name, ACC.account_number, BAL.balance_date, BAL.balance_amount, CASE WHEN BAL.delete_status = 0 THEN 'Active' WHEN BAL.delete_status = 1 THEN 'Deleted' END AS 'delete_status', CASE WHEN BAL.deleted_by IS NULL THEN \"N/A\" ELSE USRDEL.user_first_name END as deletedBy, USR.user_first_name FROM account_balance BAL LEFT JOIN bank_account ACC ON BAL.account_id = ACC.account_id LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id LEFT JOIN user USR ON BAL.entered_by = USR.user_id LEFT JOIN user USRDEL ON USR.user_id = USRDEL.user_id WHERE BAL.balance_date = ?";
            List<getAllBalancesDTO> balanceList = template.query(Sql, new Object[]{balanceDate}, new accountBalanceMapper());
            //Check whether any account balances are available for user provided balance date;
            if (balanceList.isEmpty()) {
                customAPIResponse<List<getAllBalancesDTO>> response = new customAPIResponse<>(
                        false,
                        "Account Balances are not available for provided Balance Date.",
                        null
                );
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            } else {
                customAPIResponse<List<getAllBalancesDTO>> response = new customAPIResponse<>(
                        true,
                        null,
                        balanceList
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-011")
    @LogActivity(methodDescription = "This method will display account balance details for update")
    public ResponseEntity<customAPIResponse<getBalanceForUpdateDTO>> getBalanceForUpdate(String balanceId) {
        //Check whether the user provided a value for Balance ID
        if (balanceId == null || balanceId.isEmpty()) {
            throw new BalanceInputDataViolationException("Please provide a Balance ID.");
        } else {
            String Sql = "SELECT BAL.balance_id, BNK.bank_name, ACC.account_number, BAL.balance_date, BAL.balance_amount, BAL.delete_status FROM account_balance BAL LEFT JOIN bank_account ACC ON BAL.account_id = ACC.account_id LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id WHERE BAL.balance_id = ?";
            List<getBalanceForUpdateDTO> balanceObject = template.query(Sql, new Object[]{balanceId}, new updateBalanceMapper());
            //Check whether any balance is available for provided Balance ID;
            if (balanceObject.isEmpty()) {
                throw new BalanceInputDataViolationException("No Balance Details found for provided Balance ID.");
            } else {
                //Check whether the provided balance id is already deleted or not;
                if (balanceObject.get(0).getDeleteStatus() == 1) {
                    customAPIResponse<getBalanceForUpdateDTO> response = new customAPIResponse<>(
                            false,
                            "Provided Balance ID is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                } else {
                    //Check whether the updating balance is related to a previous day balance or not;
                    if (balanceObject.get(0).getBalanceDate().equals(LocalDate.now())) {
                        customAPIResponse<getBalanceForUpdateDTO> response = new customAPIResponse<>(
                                true,
                                null,
                                balanceObject.get(0)
                        );
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    } else {
                        customAPIResponse<getBalanceForUpdateDTO> response = new customAPIResponse<>(
                                false,
                                "You are not authorized to update a previous day Account Balance.",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    }
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-011")
    @LogActivity(methodDescription = "This method will update account balance")
    public ResponseEntity<customAPIResponse<balanceUpdateDTO>> updateBalance(balanceUpdateDTO updatedBalance) {
        //Check whether user provided values for all required fields of balance updating
        if (updatedBalance.getBalanceId() == null || updatedBalance.getBalanceAmount() == null || updatedBalance.getOutstandingBalance() == null || updatedBalance.getAction() == null || updatedBalance.getAdjustmentAmount() == null || updatedBalance.getBalanceId().isEmpty() || updatedBalance.getAction().isEmpty()) {
            throw new BalanceInputDataViolationException("Please provide the values for all required fields for successful updating.");
        } else {
            float newBalance;
            float newOutstandingBalance;
            //Check whether updating balance is a + action or - action;
            if (updatedBalance.getAction().equals("+")) {
                newBalance = updatedBalance.getBalanceAmount() + updatedBalance.getAdjustmentAmount();
                int affectedRow = accountBalanceRepository.updateAccountBalance(newBalance, updatedBalance.getBalanceId());
                if (affectedRow > 0) {
                    customAPIResponse<balanceUpdateDTO> response = new customAPIResponse<>(
                            true,
                            "Balance ID: " + updatedBalance.getBalanceId() + " updated successfully.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                } else {
                    throw new BalanceNotUpdateException("Account Balance update failed. Please re-check provided data.");
                }
            } else {
                newBalance = updatedBalance.getBalanceAmount() - updatedBalance.getAdjustmentAmount();
                int affectedRow = accountBalanceRepository.updateAccountBalance(newBalance, updatedBalance.getBalanceId());
                if (affectedRow > 0) {
                    customAPIResponse<balanceUpdateDTO> response = new customAPIResponse<>(
                            true,
                            "Balance ID: " + updatedBalance.getBalanceId() + " updated successfully.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                } else {
                    throw new BalanceNotUpdateException("Account Balance update failed. Please re-check provided data.");
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-012")
    @LogActivity(methodDescription = "This method will get account balance details for delete")
    public ResponseEntity<customAPIResponse<getBalanceForDeleteDTO>> getBalanceForDelete(String balanceId) {
        //Check whether the provided balance id is valid or not;
        if (balanceId == null || balanceId.isEmpty()) {
            throw new BalanceInputDataViolationException("Balance Id has not been provided. Please provide valid Balance Id.");
        } else {
            String Sql = "SELECT BAL.balance_id, BNK.bank_name, ACC.account_number, BAL.balance_date, BAL.balance_amount, BAL.delete_status FROM account_balance BAL LEFT JOIN bank_account ACC ON BAL.account_id = ACC.account_id LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id WHERE BAL.balance_id = ?";
            List<getBalanceForDeleteDTO> deleteBalanceObj = template.query(Sql, new Object[]{balanceId}, new deleteBalanceMapper());
            //Check whether any balance record is available for provided Balance ID;
            if (deleteBalanceObj.isEmpty()) {
                throw new BalanceInputDataViolationException("No balance found for provided Balance ID.");
            } else {
                //Check whether the balance record is a previous day balance record;
                if (!deleteBalanceObj.get(0).getBalanceDate().equals(LocalDate.now())) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new customAPIResponse<>(
                            false,
                            "You are not authorized to delete a previous day balance record.",
                            null
                    ));
                } else {
                    //Check whether the balance record is already deleted or not;
                    if (deleteBalanceObj.get(0).getDeleteStatus() == 1) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                                false,
                                "Provided Balance ID has been already deleted.",
                                null
                        ));
                    } else {
                        return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                                true,
                                null,
                                deleteBalanceObj.get(0)
                        ));
                    }
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-012")
    @LogActivity(methodDescription = "This method will delete account balance")
    public ResponseEntity<customAPIResponse<String>> deleteBalance(String balanceId) {
        //Check whether the user has been provided balance id;
        if (balanceId == null || balanceId.isEmpty()) {
            throw new BalanceInputDataViolationException("Please provide valid Balance ID.");
        } else {
            //Check whether any adjustments have been initiated under this balance.
            List<String> adjustmentList = accountBalanceAdjustmentsRepository.getAvailableAdjustments(balanceId);
            if (!adjustmentList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                        false,
                        "Adjustments have been initiated for this balance. Please reverse the adjustments first, before delete the balance.",
                        null
                ));
            } else {
                int affectedRow = accountBalanceRepository.deleteBalance(httpRequest.getSessionUser(), balanceId);
                if (affectedRow > 0) {
                    return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                            true,
                            "Account Balance " + balanceId + " deleted successfully.",
                            null
                    ));
                } else {
                    throw new BalanceInputDataViolationException("Unable to proceed deletion. Please re-check provided Account Balance data.");
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-013")
    @LogActivity(methodDescription = "This method will display all adjustment details for a account balance")
    public ResponseEntity<customAPIResponse<List<balanceAdjustmentsDTO>>> getAdjustments(String balanceId) {
        //Check whether the balance id is null or not;
        if (balanceId == null || balanceId.isEmpty()) {
            throw new BalanceInputDataViolationException("Please provide valid Balance Id.");
        } else {
            //Check whether user has been provided a valid Balance ID
            if (accountBalanceRepository.findById(balanceId).get().getBalanceId() == null) {
                throw new BalanceInputDataViolationException("Incorrect Balance ID. Please provide valid Balance ID.");
            } else {
                String Sql = "SELECT adj.adjustment_id AS 'adjustment_id', crsadj.cross_adjustment_id, adj.adjustment_amount AS 'adjustment_amount', adj.adjustment_remark AS 'remark', bal.balance_amount AS 'opening_balance', (COALESCE((SELECT COALESCE(SUM(balAdj.adjustment_amount), 0) FROM account_balance_adjustments balAdj LEFT JOIN cross_adjustment crsadj1 ON balAdj.cross_adjustment_id = crsadj1.cross_adjustment_id WHERE crsadj1.is_reversed = 0 AND balAdj.adjustment_balance = ?)+bal.balance_amount,0)) AS 'closing_balance', bal.balance_id, acc.account_number FROM account_balance_adjustments adj LEFT JOIN account_balance bal ON bal.balance_id = adj.adjustment_balance LEFT JOIN bank_account acc ON acc.account_id = bal.account_id LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = adj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND adj.adjustment_balance = ?";
                List<balanceAdjustmentsDTO> adjustmentList = template.query(Sql, new balanceAdjustmentsMapper(), balanceId, balanceId);
                if (!adjustmentList.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                            true,
                            null,
                            adjustmentList
                    ));
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                            false,
                            "No Adjustment Details available for provided Balance ID.",
                            null
                    ));
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-049")
    @LogActivity(methodDescription = "This method will fetch account balances for cash flow printing")
    public ResponseEntity<customAPIResponse<List<getAccountBalancesDTO>>> getAccountBalances(LocalDate balanceDate) {
        //Check whether the balance date is null or not;
        if (balanceDate == null) {
            throw new BalanceInputDataViolationException("Please provide balance date");
        } else {
            String Sql = "SELECT acc.account_number, accbal.balance_amount FROM account_balance accbal LEFT JOIN bank_account acc ON acc.account_id = accbal.account_id WHERE accbal.balance_date = ? AND accbal.delete_status = 0 AND accbal.balance_amount > 0";
            List<getAccountBalancesDTO> balanceList = template.query(Sql, new getAccountBalancesMapper(), balanceDate);
            if (balanceList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        true,
                        null,
                        balanceList
                ));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        balanceList
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-049")
    @LogActivity(methodDescription = "This method will fetch overdraft account balances for cash flow printing")
    public ResponseEntity<customAPIResponse<List<getOverdraftBalancesDTO>>> getOverdraftBalances(LocalDate balanceDate) {
        //Check whether the balance date is null or not;
        if (balanceDate == null) {
            throw new BalanceInputDataViolationException("Please provide balance date");
        } else {
            String Sql = "SELECT accbal.balance_id AS 'Balance ID', acc.account_number AS 'Account Number', accbal.balance_amount AS 'Balance Amount' FROM account_balance accbal LEFT JOIN bank_account acc ON acc.account_id = accbal.account_id WHERE accbal.balance_amount < 0 AND accbal.delete_status = 0 AND accbal.balance_date = ?";
            List<getOverdraftBalancesDTO> overdraftBalances = template.query(Sql, new getOverdraftBalancesMapper(), balanceDate);
            if (overdraftBalances.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        true,
                        null,
                        overdraftBalances
                ));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        overdraftBalances
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-024")
    @LogActivity(methodDescription = "This method will fetch available balance for given bank account")
    public ResponseEntity<customAPIResponse<BigDecimal>> getAvailableBalances(String accountId) {
        // Check whether user has been provided account id
        if (accountId == null || accountId.isEmpty()) {
            throw new TransferInputDataViolationException("Please provide account id");
        } else {
            BigDecimal availableBalance = accountBalanceRepository.getAvailableBalance(accountId, accountId);
            if (availableBalance == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "Account Balance not found for selected Bank Account",
                        null
                ));
            } else if (availableBalance.compareTo(BigDecimal.ZERO) == 0) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        availableBalance
                ));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        availableBalance
                ));
            }
        }
    }
}
