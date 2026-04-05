package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.Repos;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transfers;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PrintingExceptions.PrintingInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.RepoExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.TransferExceptions.TransferInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.bankAccountRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.repoRepository;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transfersRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.TransferIdGenerator;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class repoIMPL implements repoService {

    @Autowired
    repoRepository repoRepository;

    @Autowired
    JdbcTemplate template;

    @Autowired
    UserRepo userRepository;

    @Autowired
    bankAccountRepo accountRepository;

    @Autowired
    fundTransferIMPL fundTransferIMPL;

    @Autowired
    transfersRepo transfersRepo;

    @Autowired
    repoAdjustmentIMPL repoAdjustmentIMPL;

    @Autowired
    crossAdjustmentIMPL crossAdjustmentIMPL;

    @Autowired
    HttpRequestUtil httpRequest;

    @Override
    @Session()
    @RequiresPermission("FUNC-031")
    @LogActivity(methodDescription = "This method will create a new repo")
    public ResponseEntity<customAPIResponse<String>> createNewRepo(createNewRepoDTO newRepoObject) {
        //Check whether user has been provided values for all required fields
        if (newRepoObject.getAccountID() == null || newRepoObject.getRepoType() == null || newRepoObject.getRepoValue() == null || newRepoObject.getEligibility() == null || newRepoObject.getAccountID().isEmpty() || newRepoObject.getRepoValue().isEmpty()) {
            throw new RepoInputDataViolationException("Please provide all required data for successful repo creation");
        } else {
            BigDecimal repoValue = new BigDecimal(newRepoObject.getRepoValue());
            //Check whether the Repo value is minus value. Minus values should not be accepted;
            if (repoValue.compareTo(BigDecimal.ZERO) < 0) {
                throw new NegativeRepoBalanceException("Negative Repo values are not accepted");
            } else {
                //Create new REPO ID;
                String newRepoId;
                String currentYear = String.valueOf(LocalDate.now().getYear());
                String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

                //Get last Repo ID from actual Repo table;
                String lastRepoId = repoRepository.getLastRepoId();
                if (lastRepoId == null) {
                    newRepoId = "REPO-" + currentYear + currentMonth + "-001";
                } else {
                    if ((currentYear + currentMonth).equals(lastRepoId.substring(5, 11))) {
                        int newNumericRepotId = Integer.parseInt(lastRepoId.substring(12, 15)) + 1;
                        newRepoId = "REPO-" + currentYear + currentMonth + String.format("-%03d", newNumericRepotId);
                    } else {
                        newRepoId = "REPO-" + currentYear + currentMonth + "-001";
                    }
                }
                repoRepository.save(new Repos(
                        newRepoId,
                        LocalDateTime.now(),
                        null,
                        null,
                        repoValue,
                        BigDecimal.valueOf(0),
                        null,
                        null,
                        newRepoObject.getEligibility(),
                        newRepoObject.getRepoType(),
                        0,
                        0,
                        null,
                        userRepository.findById(httpRequest.getSessionUser()).get(),
                        accountRepository.findById(newRepoObject.getAccountID()).get()
                ));
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "New Repo created successfully with Repo ID: " + newRepoId,
                                null
                        )
                );
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-032")
    @LogActivity(methodDescription = "This method will display repo details")
    public ResponseEntity<customAPIResponse<List<displayRepoDTO>>> displayRepo(String repoId) {
        //Check whether user provided repo is
        if (repoId == null || repoId.isEmpty()) {
            throw new RepoInputDataViolationException("Please provide valid repo id");
        } else {
            String Sql = "SELECT rpo.repo_id, acc.account_number, rpo.repo_value AS 'opening_balance', COALESCE((SELECT COALESCE(SUM(rpoadj.adjustment_amount),0) AS 'Total_Adjustments' FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = rpoadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND rpoadj.adjusted_repo = ?)+rpo.repo_value,0) AS 'closing_balance', rpo.maturity_value AS 'maturity_value', CASE WHEN rpo.interest_rate IS NULL THEN 'N/A' ELSE rpo.interest_rate END AS 'interest_rate', CASE WHEN rpo.repo_type = 1 THEN 'Par' WHEN rpo.repo_type = 2 THEN 'Non-Par' WHEN rpo.repo_type = 3 THEN 'Technical Reserve' WHEN rpo.repo_type = 4 THEN 'Shareholder' END AS 'repo_type', CASE WHEN rpo.is_invested = 0 THEN 'Not-Invested' ELSE 'Invested' END AS 'investment_status', CASE WHEN rpo.invest_date IS NULL THEN 'N/A' ELSE DATE_FORMAT(rpo.invest_date, '%Y-%m-%d') END AS 'invest_date', CASE WHEN rpo.maturity_date IS NULL THEN 'N/A' ELSE DATE_FORMAT(rpo.maturity_date, '%Y-%m-%d') END AS 'maturity_date', DATE_FORMAT(rpo.created_date, '%Y-%m-%d') AS 'created_date', usr.user_first_name AS 'created_by', CASE WHEN rpo.is_deleted = 0 THEN 'Active' ELSE 'Deleted' END AS 'delete_status', CASE WHEN usrdlt.user_first_name IS NULL THEN 'N/A' ELSE usrdlt.user_first_name END AS 'deleted_user' FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account LEFT JOIN user usrdlt ON usrdlt.user_id = rpo.deleted_by LEFT JOIN user usr ON usr.user_id = rpo.created_by WHERE rpo.repo_id = ?";

            List<displayRepoDTO> displayRepo = template.query(Sql, new displayRepoMapper(), repoId, repoId);
            if (!displayRepo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                displayRepo
                        )
                );
            } else {
                throw new RepoInputDataViolationException("No REPO details can be found for provided REPO ID");
            }
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will fetch active repo list")
    public ResponseEntity<customAPIResponse<List<getFromRepoListDTO>>> getToRepoList(String selectedRepo) {
        String Sql = "SELECT DISTINCT rpo.repo_id, COALESCE((SELECT COALESCE(SUM(rpoadj.adjustment_amount), 0) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = rpoadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND rpoadj.adjusted_repo = rpo.repo_id) + rpo.repo_value, 0) AS 'closing_balance' FROM repos rpo WHERE rpo.is_deleted = 0 AND rpo.is_invested = 0 AND DATE(rpo.created_date) = CURRENT_DATE AND rpo.repo_id <> ?";
        List<getFromRepoListDTO> repoList = template.query(Sql, new getFromRepoListMapper(), selectedRepo);
        if (!repoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            repoList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Repos can be found for current date!",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will fetch active repo list")
    public ResponseEntity<customAPIResponse<List<getFromRepoListDTO>>> getFromRepoList() {
        String Sql = "SELECT DISTINCT rpo.repo_id, COALESCE((SELECT COALESCE(SUM(rpoadj.adjustment_amount), 0) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = rpoadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND rpoadj.adjusted_repo = rpo.repo_id) + rpo.repo_value, 0) AS 'closing_balance' FROM repos rpo WHERE rpo.is_deleted = 0 AND rpo.is_invested = 0 AND DATE(rpo.created_date) = CURRENT_DATE";
        List<getFromRepoListDTO> repoList = template.query(Sql, new getFromRepoListMapper());
        if (!repoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            repoList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Repos can be found for current date",
                            null
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-033")
    @LogActivity(methodDescription = "This method will create a new repo account")
    public ResponseEntity<customAPIResponse<String>> adjustmentNewRepo(adjustmentNewRepoDTO adjustmentNewRepo) {
        try {
            //Check whether newly create repo value is higher than the existing repo's value;
            BigDecimal closingBalance = repoRepository.getRepoClosingBalance(adjustmentNewRepo.getFromRepo(), adjustmentNewRepo.getFromRepo());
            BigDecimal repoValue = BigDecimal.valueOf(adjustmentNewRepo.getRepoValue());
            if (closingBalance.compareTo(repoValue) >= 0) {
                //Save new repo data to the database;
                //Create new REPO ID;
                String newRepoId;
                String currentYear = String.valueOf(LocalDate.now().getYear());
                String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

                //Get last Repo ID from actual Repo table;
                String lastRepoId = repoRepository.getLastRepoId();
                if (lastRepoId == null) {
                    newRepoId = "REPO-" + currentYear + currentMonth + "-001";
                } else {
                    if ((currentYear + currentMonth).equals(lastRepoId.substring(5, 11))) {
                        int newNumericRepotId = Integer.parseInt(lastRepoId.substring(12, 15)) + 1;
                        newRepoId = "REPO-" + currentYear + currentMonth + String.format("-%03d", newNumericRepotId);
                    } else {
                        newRepoId = "REPO-" + currentYear + currentMonth + "-001";
                    }
                }
                repoRepository.save(
                        new Repos(
                                newRepoId,
                                LocalDateTime.now(),
                                null,
                                null,
                                BigDecimal.valueOf(0),
                                BigDecimal.valueOf(0),
                                null,
                                null,
                                adjustmentNewRepo.getEligibility(),
                                adjustmentNewRepo.getRepoType(),
                                0,
                                0,
                                null,
                                userRepository.findById(httpRequest.getSessionUser()).get(),
                                accountRepository.findById(adjustmentNewRepo.getRepoAccount()).get()
                        )
                );
                //After creating the new repo, need to check whether the new repo is created in same bank account;
                //or separate bank account from fund sending repo;
                if (adjustmentNewRepo.getRepoAccount().equals(repoRepository.getRepoBankAccount(adjustmentNewRepo.getFromRepo()))) {
                    //Create new cross adjustment;
                    String crossAdjustment = crossAdjustmentIMPL.saveNewCrossAdjustment();
                    //Save from repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            newRepoId,
                            BigDecimal.valueOf(adjustmentNewRepo.getRepoValue() * -1),
                            adjustmentNewRepo.getFromRepo(),
                            null,
                            crossAdjustment
                    );
                    //Save to repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            adjustmentNewRepo.getFromRepo(),
                            BigDecimal.valueOf(adjustmentNewRepo.getRepoValue()),
                            newRepoId,
                            null,
                            crossAdjustment
                    );

                    //If the both repos are in same account, no any transfers and adjustments are saved in the database;
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    false,
                                    "Repo is created successfully with Repo Id: " + newRepoId,
                                    null
                            )
                    );
                } else {
                    //Create new cross adjustment;
                    String crossAdjustment = crossAdjustmentIMPL.saveNewCrossAdjustment();

                    //Get last transfer id by calling for generateTransferId method;
                    String generatedLastTransferId = transfersRepo.getLastTransferId();
                    TransferIdGenerator idGenerator = new TransferIdGenerator(generatedLastTransferId);
                    String transferId = idGenerator.getNextId();
                    //Save transfer record in to the database;
                    fundTransferIMPL.newTransfer(
                            transferId,
                            repoRepository.getRepoBankAccount(adjustmentNewRepo.getFromRepo()),
                            adjustmentNewRepo.getRepoAccount(),
                            adjustmentNewRepo.getTransferChanel(),
                            BigDecimal.valueOf(adjustmentNewRepo.getRepoValue()),
                            adjustmentNewRepo.getFromRepo(),
                            newRepoId,
                            crossAdjustment
                    );

                    //Save from repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            newRepoId,
                            BigDecimal.valueOf(adjustmentNewRepo.getRepoValue() * -1),
                            adjustmentNewRepo.getFromRepo(),
                            transferId,
                            crossAdjustment
                    );

                    //Save to repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            adjustmentNewRepo.getFromRepo(),
                            BigDecimal.valueOf(adjustmentNewRepo.getRepoValue()),
                            newRepoId,
                            transferId,
                            crossAdjustment
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    false,
                                    "Repo is created successfully with Repo Id: " + newRepoId,
                                    null
                            )
                    );
                }
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new customAPIResponse<>(
                                false,
                                "New Repo value cannot be higher than closing balance of fund sending Repo!",
                                null
                        )
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new customAPIResponse<>(
                            false,
                            "Un-expected error occurred. Please contact administrator!",
                            null
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-033")
    @LogActivity(methodDescription = "This method will funds transfer to an existing repo account")
    public ResponseEntity<customAPIResponse<String>> existingRepoTransfer(adjustmentExistingRepoDTO adjustmentExistingRepo) {
        //Check whether user provided all required values
        if (adjustmentExistingRepo.getFromRepo() == null || adjustmentExistingRepo.getToRepo() == null || adjustmentExistingRepo.getRepoValue() == null || adjustmentExistingRepo.getTransferChanel() == null || adjustmentExistingRepo.getFromRepo().isEmpty() || adjustmentExistingRepo.getToRepo().isEmpty() || adjustmentExistingRepo.getTransferChanel().isEmpty()) {
            throw new RepoInputDataViolationException("Please provide all required data");
        } else {
            //Check whether transfer value is higher than the existing repo's closing balance;
            BigDecimal closingBalance = repoRepository.getRepoClosingBalance(adjustmentExistingRepo.getFromRepo(), adjustmentExistingRepo.getFromRepo());
            BigDecimal repoValue = adjustmentExistingRepo.getRepoValue();
            if (closingBalance.compareTo(repoValue) >= 0) {
                //Check whether from repo and to repo is in same bank account or different bank account;
                if (repoRepository.getRepoBankAccount(adjustmentExistingRepo.getToRepo()).equals(repoRepository.getRepoBankAccount(adjustmentExistingRepo.getFromRepo()))) {
                    //Create new cross adjustment;
                    String crossAdjustment = crossAdjustmentIMPL.saveNewCrossAdjustment();
                    //Save from repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            adjustmentExistingRepo.getToRepo(),
                            adjustmentExistingRepo.getRepoValue().multiply(BigDecimal.valueOf(-1)),
                            adjustmentExistingRepo.getFromRepo(),
                            null,
                            crossAdjustment
                    );

                    //Save to repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            adjustmentExistingRepo.getFromRepo(),
                            adjustmentExistingRepo.getRepoValue(),
                            adjustmentExistingRepo.getToRepo(),
                            null,
                            crossAdjustment
                    );

                    //If the both repos are in same account, no any transfers and adjustments are saved in the database;
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    false,
                                    "Transfer has been initiated successfully",
                                    null
                            )
                    );
                } else {
                    //Create new cross adjustment;
                    String crossAdjustment = crossAdjustmentIMPL.saveNewCrossAdjustment();

                    //Get last transfer id by calling for generateTransferId method;
                    String generatedLastTransferId = transfersRepo.getLastTransferId();
                    TransferIdGenerator idGenerator = new TransferIdGenerator(generatedLastTransferId);
                    String transferId = idGenerator.getNextId();
                    //Save transfer record in to the database;
                    fundTransferIMPL.newTransfer(
                            transferId,
                            repoRepository.getRepoBankAccount(adjustmentExistingRepo.getFromRepo()),
                            repoRepository.getRepoBankAccount(adjustmentExistingRepo.getToRepo()),
                            adjustmentExistingRepo.getTransferChanel(),
                            adjustmentExistingRepo.getRepoValue(),
                            adjustmentExistingRepo.getFromRepo(),
                            adjustmentExistingRepo.getToRepo(),
                            crossAdjustment
                    );

                    //Save from repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            adjustmentExistingRepo.getToRepo(),
                            adjustmentExistingRepo.getRepoValue().multiply(BigDecimal.valueOf(-1)),
                            adjustmentExistingRepo.getFromRepo(),
                            transferId,
                            crossAdjustment
                    );

                    //Save to repo adjustment into the database;
                    repoAdjustmentIMPL.saveNewAdjustment(
                            adjustmentExistingRepo.getFromRepo(),
                            adjustmentExistingRepo.getRepoValue(),
                            adjustmentExistingRepo.getToRepo(),
                            transferId,
                            crossAdjustment
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    false,
                                    "Transfer has been initiated successfully",
                                    null
                            )
                    );
                }
            } else {
                throw new InsufficientBalanceException("Transferring value is higher than the closing balance of from Repo");
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-036")
    @LogActivity(methodDescription = "This method will delete a repo")
    public ResponseEntity<customAPIResponse<String>> repoDelete(String repoId) {
        //Check whether user provided repo id
        if (repoId == null || repoId.isEmpty()) {
            throw new RepoInputDataViolationException("Please provide all required data");
        } else {
            //Check whether the repo is already deleted or not;
            int deleteStatus = repoRepository.isRepoDeleted(repoId);
            if (deleteStatus == 0) {
                //Check whether the repo is already invested;
                int investedStatus = repoRepository.isInvestedRepo(repoId);
                if (investedStatus == 0) {
                    //Check whether the repo is previous day repo or same day repo;
                    int repoDateStatus = repoRepository.isRepoSameDate(repoId);
                    if (repoDateStatus == 1) {
                        //Check whether the repo has any active adjustments. If there is any adjustment, before delete the repo, the adjustment should be deleted;
                        int adjustmentCount = repoRepository.adjustmentCount(repoId);
                        if (adjustmentCount == 0) {
                            int deleteRepo = repoRepository.repoDelete(
                                    httpRequest.getSessionUser(),
                                    repoId
                            );
                            if (deleteRepo > 0) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        new customAPIResponse<>(
                                                false,
                                                "Repo Id " + repoId + " deleted successfully",
                                                null
                                        )
                                );
                            } else {
                                throw new RepoDeletionFailureException("Couldn't delete repo. Please contact administrator");
                            }
                        } else {
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                    new customAPIResponse<>(
                                            false,
                                            "This Repo ID has active adjustments. Please reverse all adjustments related for this Repo ID before delete",
                                            null
                                    )
                            );
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                new customAPIResponse<>(
                                        false,
                                        "You are not authorized to delete a previous day Repo",
                                        null
                                )
                        );
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                            new customAPIResponse<>(
                                    false,
                                    "This Repo is already invested. If you need to delete, please reverse the investment first",
                                    null
                            )
                    );
                }
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new customAPIResponse<>(
                                false,
                                "This Repo Id is already deleted. No further deletion is required",
                                null
                        )
                );
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-037")
    @LogActivity(methodDescription = "This method will display repo details for investment purpose")
    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForInvestments() {
        String Sql = "SELECT DATE(rpo.created_date) AS 'Repo_Date', rpo.repo_id, acc.account_number, CASE rpo.repo_type WHEN 1 THEN 'Par' WHEN 2 THEN 'Non-Par' WHEN 3 THEN 'Technical Reserve' WHEN 4 THEN 'Shareholder' ELSE 'UNKNOWN' END AS Repo_Type, rpo.repo_value + COALESCE((SELECT SUM(rpoadj.adjustment_amount) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON rpoadj.cross_adjustment_id = crsadj.cross_adjustment_id WHERE rpoadj.adjusted_repo = rpo.repo_id AND (crsadj.is_reversed = 0 OR crsadj.cross_adjustment_id IS NULL)), 0) AS Investment_Value FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account WHERE DATE(rpo.created_date) = CURRENT_DATE AND rpo.is_deleted = 0 AND rpo.is_invested = 0 GROUP BY rpo.repo_id, acc.account_number, rpo.repo_type, rpo.repo_value";
        List<repoDetailsForInvestmentsDTO> repoDetailsList = template.query(Sql, new repoDetailsForInvestmentsMapper());
        if (!repoDetailsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            repoDetailsList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Repo details available to display",
                            null
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-037")
    @LogActivity(methodDescription = "This method will invest repo balances")
    public ResponseEntity<customAPIResponse<String>> initiateInvestment(String repoId, LocalDate toDate, String rate, Integer method, BigDecimal maturityValue) {
        // Check whether user provided all required data
        if (repoId == null || repoId.isEmpty() || toDate == null || rate == null || rate.isEmpty() || method == null || maturityValue == null) {
            throw new RepoInputDataViolationException("Please provide all required data");
        } else {
            maturityValue = new BigDecimal(String.valueOf(maturityValue));
            int affectedRows = repoRepository.investRepo(rate, toDate, method, maturityValue, repoId);
            if (affectedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Repo invested successfully",
                                null
                        )
                );
            } else {
                throw new RepoInvestmentFailureException("Couldn't invest selected repo. Please contact administrator");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-038")
    @LogActivity(methodDescription = "This method will display repo details for investment reversal purpose")
    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForInvestmentReverse() {

        String Sql = "SELECT DATE(rpo.created_date) AS 'Repo_Date', rpo.repo_id, acc.account_number, CASE rpo.repo_type WHEN 1 THEN 'Par' WHEN 2 THEN 'Non-Par' WHEN 3 THEN 'Technical Reserve' WHEN 4 THEN 'Shareholder' ELSE 'UNKNOWN' END AS Repo_Type, rpo.repo_value + COALESCE((SELECT SUM(rpoadj.adjustment_amount) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON rpoadj.cross_adjustment_id = crsadj.cross_adjustment_id WHERE rpoadj.adjusted_repo = rpo.repo_id AND (crsadj.is_reversed = 0 OR crsadj.cross_adjustment_id IS NULL)), 0) AS Investment_Value FROM repos rpo\n" +
                "LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account WHERE DATE(rpo.created_date) = CURRENT_DATE AND rpo.is_deleted = 0 AND rpo.is_invested = 1 GROUP BY rpo.repo_id, acc.account_number, rpo.repo_type, rpo.repo_value";
        List<repoDetailsForInvestmentsDTO> repoDetailsList = template.query(Sql, new repoDetailsForInvestmentsMapper());
        if (!repoDetailsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            repoDetailsList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Invested Repo details available to display",
                            null
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-038")
    @LogActivity(methodDescription = "This method will reverse invested repo balances")
    public ResponseEntity<customAPIResponse<String>> investmentReverse(String repoId) {
        // Check whether user provided repo id
        if (repoId == null || repoId.isEmpty()) {
            throw new RepoInputDataViolationException("Please provide valid repo id");
        } else {
            int affectedRows = repoRepository.reverseInvestment(repoId);
            if (affectedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Investment reversed successfully",
                                null
                        )
                );
            } else {
                throw new RepoInvestmentReversalFailureException("Couldn't reverse the investment. Please contact administrator");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-048")
    @LogActivity(methodDescription = "This method will display repo details for printing")
    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForPrint(LocalDate repoDate) {
        //Check whether user provided repo date
        if (repoDate == null) {
            throw new RepoInputDataViolationException("Please provide repo date");
        } else {
            String Sql = "SELECT DATE(rpo.created_date) AS 'Repo_Date', rpo.repo_id, acc.account_number, CASE rpo.repo_type WHEN 1 THEN 'PAR' WHEN 2 THEN 'NON-PAR' WHEN 3 THEN 'TR' WHEN 4 THEN 'EXCESS' ELSE 'UNKNOWN' END AS Repo_Type, rpo.repo_value + COALESCE((SELECT SUM(rpoadj.adjustment_amount) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON rpoadj.cross_adjustment_id = crsadj.cross_adjustment_id WHERE rpoadj.adjusted_repo = rpo.repo_id AND (crsadj.is_reversed = 0 OR crsadj.cross_adjustment_id IS NULL)), 0) AS Investment_Value FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account WHERE DATE(rpo.created_date) = ? AND rpo.is_deleted = 0 AND rpo.is_invested = 1 GROUP BY rpo.repo_id, acc.account_number, rpo.repo_type, rpo.repo_value";
            List<repoDetailsForInvestmentsDTO> repoDetailsList = template.query(Sql, new repoDetailsForInvestmentsMapper(), repoDate);
            if (!repoDetailsList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                repoDetailsList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No Repo details available to print",
                                null
                        )
                );
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-049")
    @LogActivity(methodDescription = "This method will fetch opening repo balances for cash flow printing")
    public ResponseEntity<customAPIResponse<List<getRepoOpeningBalancesDTO>>> getRepoOpeningBalances(LocalDate repoDate) {
        // Check whether user provided repo date
        if (repoDate == null) {
            throw new RepoInputDataViolationException("Please provide repo date");
        } else {
            String sql = "SELECT rpo.repo_id, rpo.repo_value, acc.account_number FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account WHERE DATE(rpo.created_date) = ? AND rpo.repo_value > 0 AND rpo.is_deleted = 0 AND rpo.is_invested = 1";
            List<getRepoOpeningBalancesDTO> openingBalanceList = template.query(sql, new getRepoOpeningBalancesMapper(), repoDate);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            openingBalanceList
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-049")
    @LogActivity(methodDescription = "This method will fetch closing repo balances for cash flow printing")
    public ResponseEntity<customAPIResponse<List<getRepoClosingBalancesDTO>>> getRepoClosingBalances(LocalDate repoDate) {
        if (repoDate == null) {
            throw new RepoInputDataViolationException("Please provide repo date");
        } else {
            String Sql = "SELECT rpo.repo_id, acc.account_number, CASE rpo.repo_type WHEN 1 THEN 'PAR' WHEN 2 THEN 'NON-PAR' WHEN 3 THEN 'TR' WHEN 4 THEN 'EXCESS' ELSE 'UNKNOWN' END AS Repo_Type, rpo.repo_value + COALESCE((SELECT SUM(rpoadj.adjustment_amount) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON rpoadj.cross_adjustment_id = crsadj.cross_adjustment_id WHERE rpoadj.adjusted_repo = rpo.repo_id AND (crsadj.is_reversed = 0 OR crsadj.cross_adjustment_id IS NULL)), 0) AS Investment_Value FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account WHERE DATE(rpo.created_date) = ? AND rpo.is_deleted = 0 AND rpo.is_invested = 1 GROUP BY rpo.repo_id, acc.account_number, rpo.repo_type, rpo.repo_value";
            List<getRepoClosingBalancesDTO> repoList = template.query(Sql, new getRepoClosingBalancesMapper(), repoDate);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            repoList
                    )
            );
        }
    }

    @Override
    @Session()
    public ResponseEntity<customAPIResponse<List<getRepoAccountsListDTO>>> getRepoAccountsList() {
        try {
            String Sql = "SELECT rpo.repo_id AS 'Repo ID', CASE WHEN rpo.repo_type = 1 THEN 'PAR' WHEN rpo.repo_type = 2 THEN 'NON-PAR' WHEN rpo.repo_type = 3 THEN 'TR' WHEN rpo.repo_type = 4 THEN 'EXCESS' END AS 'Repo Type', acc.account_number AS 'Account Number' FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account WHERE rpo.is_deleted = 0 AND rpo.is_invested = 0 AND DATE(rpo.created_date) = ?";
            List<getRepoAccountsListDTO> repoList = template.query(Sql, new getRepoAccountsListMapper(), LocalDate.now());
            if (!repoList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                repoList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new customAPIResponse<>(
                                true,
                                "No Repo Account found!",
                                null
                        )
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new customAPIResponse<>(
                            false,
                            "Un-expected error occurred. Please contact administrator!",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-024")
    @LogActivity(methodDescription = "This method will fetch all repos for manual transfers")
    public ResponseEntity<customAPIResponse<List<repoListForManualTransfersDTO>>> getReposForManualTransfers() {
        String sql = "SELECT repo.repo_id, acc.account_number FROM repos repo LEFT JOIN bank_account acc ON acc.account_id = repo.bank_account WHERE repo.is_deleted = 0 AND repo.is_invested = 0 AND DATE(repo.created_date) = CURRENT_DATE";
        List<repoListForManualTransfersDTO> repoList = template.query(sql, new repoListForManualTransferMapper());
        if (!repoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            repoList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No repo details found",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-024")
    @LogActivity(methodDescription = "This method will fetch available balance for given repo id")
    public ResponseEntity<customAPIResponse<BigDecimal>> getAvailableBalances(String repoId) {
        // Check whether user has been provided repo id
        if (repoId == null || repoId.isEmpty()) {
            throw new RepoInputDataViolationException("Please provide repo id");
        } else {
            BigDecimal availableBalance = repoRepository.getAvailableBalance(repoId, repoId);
            if (availableBalance == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "Account Balance not found for selected Repo Account",
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
