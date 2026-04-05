package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.crossAdjustmentDeleteDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.deleteCrossAdjustmentDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getCrossAdjustmentDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoAdjustmentsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.Repos;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.crossAdjustments;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.repoAdjustments;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transfers;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.RepoAdjustmentExceptions.RepoAdjustmentInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.RepoExceptions.RepoDeletionFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.crossAdjustmentDeleteMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.deleteCrossAdjustmentMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.getCrossAdjustmentDetailsMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.repoAdjustmentsMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class repoAdjustmentIMPL implements repoAdjustmentService {
    @Autowired
    repoAdjustmentRepo repoAdjustmentRepository;

    @Autowired
    repoRepository repoRepository;

    @Autowired
    transfersRepo transfersRepository;

    @Autowired
    JdbcTemplate template;

    @Autowired
    crossAdjustmentRepo crossAdjustmentRepository;

    @Autowired
    accountBalanceAdjustmentsRepo accountBalanceAdjustmentsRepository;

    @Autowired
    HttpRequestUtil httpRequest;

    //This method has been implemented to generate a new Repo Adjustment ID.
    @Override
    @Session()
    @Transactional
    public String generateAdjustmentId() {
        //Create new Adjustment ID for a new Repo Adjustment;
        String newAdjustmentId;
        String currentYear = String.valueOf(LocalDate.now().getYear());
        String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

        //Get last Repo Adjustment ID from Repo_Adjustment table;
        String lastAdjustmentId = repoAdjustmentRepository.getLastAdjustmentId();
        if (lastAdjustmentId == null) {
            newAdjustmentId = "RPOADJ-" + currentYear + currentMonth + "-0001";
        } else {
            if ((currentYear + currentMonth).equals(lastAdjustmentId.substring(7, 13))) {
                int newNumericAdjustmentId = Integer.parseInt(lastAdjustmentId.substring(14, 18)) + 1;
                newAdjustmentId = "RPOADJ-" + currentYear + currentMonth + String.format("-%04d", newNumericAdjustmentId);
            } else {
                newAdjustmentId = "RPOADJ-" + currentYear + currentMonth + "-0001";
            }
        }
        return newAdjustmentId;
    }

    //This method has been implemented to Generate proper repo_adjustment remark;
    @Override
    @Session()
    public String newRemark(String remarkAccount, BigDecimal adjustmentAmount) {
        if (adjustmentAmount.compareTo(BigDecimal.ZERO) > 0) {
            return "Received from " + remarkAccount;
        } else {
            return "Transferred to " + remarkAccount;
        }
    }

    //This method has been implemented to save a new Repo Adjustment to the Repo_adjustment table;
    @Override
    @Session()
    @Transactional
    public void saveNewAdjustment(String remarkAccount, BigDecimal adjustmentAmount, String adjustmentRepo, String transferId, String crossAdjustmentId) {
        Repos repoEntity = (adjustmentRepo != null) ? repoRepository.findById(adjustmentRepo).orElse(null) : null;
        transfers transferEntity = (transferId != null) ? transfersRepository.findById(transferId).orElse(null) : null;
        crossAdjustments crossAdjustment = (crossAdjustmentId != null) ? crossAdjustmentRepository.findById(crossAdjustmentId).orElse(null) : null;
        repoAdjustmentRepository.save(new repoAdjustments(
                this.generateAdjustmentId(),
                LocalDateTime.now(),
                this.newRemark(remarkAccount, adjustmentAmount),
                adjustmentAmount,
                repoEntity,
                transferEntity,
                crossAdjustment
        ));
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-034")
    @LogActivity(methodDescription = "This method will fetch all repo adjustments")
    public ResponseEntity<customAPIResponse<List<repoAdjustmentsDTO>>> getAdjustments(String repoId) {
        // Check whether user provided repo id
        if (repoId == null || repoId.isEmpty()) {
            throw new RepoAdjustmentInputDataViolationException("Please provide all required data");
        } else {
            if (repoRepository.isRepoDeleted(repoId) == 0) {
                String Sql = "SELECT adj.adjustment_id AS 'adjustment_id', adj.adjustment_amount AS 'adjustment_amount', adj.adjustment_remark AS 'remark', adj.cross_adjustment_id AS 'cross_adjustment_id', repo.repo_value AS 'opening_balance', (COALESCE((SELECT COALESCE(SUM(repoAdj.adjustment_amount), 0) FROM repo_adjustment repoAdj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = repoAdj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND repoAdj.adjusted_repo = ?)+repo.repo_value,0)) AS 'closing_balance', repo.repo_id, acc.account_number FROM repo_adjustment adj LEFT JOIN repos repo ON repo.repo_id = adj.adjusted_repo LEFT JOIN bank_account acc ON acc.account_id = repo.bank_account LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = adj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND adj.adjusted_repo = ?";
                List<repoAdjustmentsDTO> adjustmentList = template.query(Sql, new repoAdjustmentsMapper(), repoId, repoId);
                if (!adjustmentList.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                            true,
                            null,
                            adjustmentList
                    ));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                            false,
                            "No adjustment can be found for provided REPO Id",
                            null
                    ));
                }
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                        false,
                        "This Repo is already deleted",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-035")
    @LogActivity(methodDescription = "This method will display repo adjustment details for deletion")
    public ResponseEntity<customAPIResponse<List<getCrossAdjustmentDetailsDTO>>> adjustmentDetails(String adjustmentId) {
        // Check whether user provided adjustment id
        if (adjustmentId == null || adjustmentId.isEmpty()) {
            throw new RepoAdjustmentInputDataViolationException("Please provide valid adjustment id");
        } else {
            //Check whether any adjustment is available for provided id;
            String Sql = "SELECT crsadj.cross_adjustment_id, DATE(crsadj.adjusted_date), CASE WHEN crsadj.is_reversed = 0 THEN 'Active' ELSE 'Reversed' END AS 'reverse_status' FROM cross_adjustment crsadj WHERE crsadj.cross_adjustment_id = ?";
            List<getCrossAdjustmentDetailsDTO> adjustmentList = template.query(Sql, new getCrossAdjustmentDetailsMapper(), adjustmentId);
            if (!adjustmentList.isEmpty()) {
                //Check whether the adjustment is already deleted or not;
                if (adjustmentList.get(0).getStatus().equals("Active")) {
                    return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                            true,
                            null,
                            adjustmentList
                    ));
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                            false,
                            "Provided Adjustment ID is already deleted",
                            null
                    ));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "No Repo Adjustment details can be found for provided Adjustment ID",
                        null
                ));
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-035")
    @LogActivity(methodDescription = "This method will delete repo adjustments")
    public ResponseEntity<customAPIResponse<String>> adjustmentDelete(String adjustmentId) {
        // Check whether user provided adjustment id
        if (adjustmentId == null || adjustmentId.isEmpty()) {
            throw new RepoAdjustmentInputDataViolationException("Please provide valid adjustment id");
        } else {
            //Check whether the adjustment is a previous day adjustment. If the adjustment is related to previous day, no authority to delete;
            String Sql = "SELECT DATE(crsadj.adjusted_date) AS 'adjustment_date', tfr.transfer_id FROM cross_adjustment crsadj LEFT JOIN transfers tfr ON tfr.cross_adjustment = crsadj.cross_adjustment_id WHERE crsadj.cross_adjustment_id = ?";
            List<deleteCrossAdjustmentDTO> adjustment = template.query(Sql, new deleteCrossAdjustmentMapper(), adjustmentId);
            if (adjustment.get(0).getAdjustmentDate().equals(LocalDate.now())) {
                //Check whether the relevant repo is already invested;
                boolean status = repoRepository.investmentStatus(adjustmentId);
                if (!status) {
                    if (adjustment.get(0).getTransferId() == null) {
                        //Delete provided cross adjustment;
                        int affectedRow = crossAdjustmentRepository.crossAdjustmentDeletion(httpRequest.getSessionUser(), adjustmentId);
                        if (affectedRow > 0) {
                            return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                                    true,
                                    "Adjustment: " + adjustmentId + " deleted successfully",
                                    null
                            ));
                        } else {
                            throw new RepoDeletionFailureException("Couldn't delete adjustment. Please contact administrator");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                                false,
                                "This adjustment has been initiated through a transfer. Please reverse the relevant transfer and the adjustment will be reversed automatically",
                                null
                        ));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                            false,
                            "Repos have been already invested related with this Adjustment Id. Before delete this adjustment, please reverse the investment first",
                            null
                    ));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new customAPIResponse<>(
                        false,
                        "You are not authorized to reverse a previous day adjustments",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    public ResponseEntity<customAPIResponse<List<String>>> showAdjustments(String repoId) {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
