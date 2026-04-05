package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ReportExceptions.ReportInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class reportIMPL implements reportService {
    @Autowired
    JdbcTemplate template;

    @Override
    @Session()
    @RequiresPermission("FUNC-043")
    @LogActivity(methodDescription = "This method will fetch all fund request details for provided criteria")
    public ResponseEntity<customAPIResponse<List<fundRequestHistoryDTO>>> fundRequestHistory(LocalDate fromDate, LocalDate toDate, String paymentId) {
        // Check whether user provided all required data
        if (fromDate == null || toDate == null | paymentId == null || paymentId.isEmpty()) {
            throw new ReportInputDataViolationException("Please provide all required data for report generation");
        } else {
            //If user have selected a specific payment type;
            if (!paymentId.equals("1")) {
                String Sql = "SELECT REQ.request_id, REQ.request_date, REQ.required_date, REQ.request_amount, ACC.account_number, PMNT.payment_type, CASE WHEN REQ.approve_status = 1 THEN 'Approved' ELSE 'Not-Approved' END AS 'approve_status', USR.user_first_name FROM fund_request REQ LEFT JOIN bank_account ACC ON REQ.bank_account = ACC.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id LEFT JOIN user USR ON REQ.request_by = USR.user_id WHERE REQ.required_date BETWEEN ? AND ? AND REQ.payment = ? AND REQ.delete_status = 0";
                List<fundRequestHistoryDTO> requestHistory = template.query(Sql, new fundRequestHistoryMapper(), fromDate, toDate, paymentId);
                if (requestHistory.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new customAPIResponse<List<fundRequestHistoryDTO>>(
                                    false,
                                    "No Fund Request Data available for given date range",
                                    null
                            )
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<List<fundRequestHistoryDTO>>(
                                    true,
                                    null,
                                    requestHistory
                            )
                    );
                }
            } else {
                //If user has been selected the payment type as 'All';
                String Sql = "SELECT REQ.request_id, REQ.request_date, REQ.required_date, REQ.request_amount, ACC.account_number, PMNT.payment_type, CASE WHEN REQ.approve_status = 1 THEN 'Approved' ELSE 'Not-Approved' END AS 'approve_status', USR.user_first_name FROM fund_request REQ LEFT JOIN bank_account ACC ON REQ.bank_account = ACC.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id LEFT JOIN user USR ON REQ.request_by = USR.user_id WHERE REQ.required_date BETWEEN ? AND ? AND REQ.delete_status = 0";
                List<fundRequestHistoryDTO> requestHistory = template.query(Sql, new fundRequestHistoryMapper(), fromDate, toDate);
                if (requestHistory.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new customAPIResponse<List<fundRequestHistoryDTO>>(
                                    false,
                                    "No Fund Request Data available for given date range",
                                    null
                            )
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<List<fundRequestHistoryDTO>>(
                                    true,
                                    null,
                                    requestHistory
                            )
                    );
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-044")
    @LogActivity(methodDescription = "This method will fetch all fund transfer details for provided criteria")
    public ResponseEntity<customAPIResponse<List<transferHistoryDTO>>> transferHistory(LocalDate fromDate, LocalDate toDate, String fromAccount, String toAccount, String chanel) {
        // Check whether user provided all required data
        if (fromDate == null || toDate == null || fromAccount == null) {
            throw new ReportInputDataViolationException("Please provide all required data for report generation");
        } else {
            String Sql = "SELECT TFR.transfer_id, TFR.transfer_date, TFR.transfer_amount, CHNL.channel_type, FROMACC.account_number, TOACC.account_number, CASE WHEN TFR.from_repo IS NULL THEN 'N/A' ELSE TFR.from_repo END AS 'From Repo', CASE WHEN TFR.to_repo IS NULL THEN 'N/A' ELSE TFR.to_repo END AS 'To Repo', USR.user_first_name, CASE WHEN TFR.is_checked = 0 THEN 'Pending' ELSE 'Checked' END AS 'checked_status', CASE WHEN TFR.is_approved = 0 THEN 'Pending' ELSE 'Approved' END AS 'Approve Status' FROM transfers TFR LEFT JOIN transfer_channel CHNL ON TFR.chanel = CHNL.channel_id LEFT JOIN bank_account FROMACC ON TFR.from_account = FROMACC.account_id LEFT JOIN bank_account TOACC ON TFR.to_account = TOACC.account_id LEFT JOIN user USR ON TFR.initiated_by = USR.user_id WHERE TFR.is_reversed = 0 AND FROMACC.account_number LIKE ? AND TOACC.account_number LIKE ? AND CHNL.channel_type LIKE ? AND TFR.transfer_date BETWEEN ? AND DATE_ADD(?, INTERVAL 1 DAY) ORDER BY TFR.transfer_id ASC";
            List<transferHistoryDTO> historyData = template.query(Sql, new transferHistoryMapper(), fromAccount + "%", toAccount + "%", chanel + "%", fromDate, toDate);
            if (historyData.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<List<transferHistoryDTO>>(
                                false,
                                "No Transfer records found for given criteria",
                                null
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<List<transferHistoryDTO>>(
                                true,
                                null,
                                historyData
                        )
                );
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-039")
    @LogActivity(methodDescription = "This method will fetch all available users")
    public ResponseEntity<customAPIResponse<List<userReportDTO>>> getUserReport() {
        String sql = "SELECT usr.user_id, usr.user_first_name, usr.user_last_name, usr.department, usr.section, usr.user_position,  usr.user_epf, usr.user_email, CASE WHEN usr.user_active_status = 0 THEN 'In-active' ELSE 'Active' END AS 'Active Status', usrDel.user_epf AS 'Registered By' FROM user usr LEFT JOIN user usrDel ON usrDel.user_id = usr.user_created_by";
        List<userReportDTO> userList = template.query(sql, new userReportMapper());
        if (!userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<List<userReportDTO>>(
                            true,
                            null,
                            userList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<List<userReportDTO>>(
                            true,
                            "No users are available",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-040")
    @LogActivity(methodDescription = "This method will fetch all available bank accounts")
    public ResponseEntity<customAPIResponse<List<accountReportDTO>>> getAccountReport() {
        String sql = "SELECT acc.account_id, bnk.bank_name, acc.bank_branch, acc.account_number, acc.currency, acc.gl_code, CASE WHEN acc.account_type = 1 THEN 'Current Account' ELSE 'Saving Account' END AS 'Account Type', CASE WHEN acc.delete_status = 0 THEN 'Active' ELSE 'Deleted' END AS 'Status', CASE WHEN usrDel.user_first_name IS NULL THEN 'N/A' ELSE usrDel.user_first_name END AS 'Deleted By', acc.registered_date, usrReg.user_first_name AS 'Registered By' FROM bank_account acc LEFT JOIN bank bnk ON bnk.bank_id = acc.bank LEFT JOIN user usrReg ON usrReg.user_id = acc.registered_by LEFT JOIN user usrDel ON usrDel.user_id = acc.deleted_by";
        List<accountReportDTO> accountList = template.query(sql, new accountReportMapper());
        if (!accountList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<List<accountReportDTO>>(
                            true,
                            null,
                            accountList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<List<accountReportDTO>>(
                            true,
                            "No Bank Accounts are available",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-042")
    @LogActivity(methodDescription = "This method will fetch all payment details")
    public ResponseEntity<customAPIResponse<List<paymentReportDTO>>> getPaymentReport() {
        String sql = "SELECT pmnt.payment_id, pmnt.payment_type, pmnt.registered_date, regUser.user_first_name AS 'Registered By', CASE WHEN pmnt.is_deleted = 0 THEN 'Active' ELSE 'Deleted' END AS 'Status', CASE WHEN usrDel.user_first_name IS NULL THEN 'N/A' ELSE usrDel.user_first_name END AS 'Deleted By' FROM payment pmnt LEFT JOIN user regUser ON regUser.user_id = pmnt.registered_by LEFT JOIN user usrDel ON usrDel.user_id = pmnt.deleted_by";
        List<paymentReportDTO> paymentList = template.query(sql, new paymentReportMapper());
        if (!paymentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<List<paymentReportDTO>>(
                            true,
                            null,
                            paymentList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<List<paymentReportDTO>>(
                            true,
                            "No Payments are available",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-041")
    @LogActivity(methodDescription = "This method will fetch all account balances under provided criteria")
    public ResponseEntity<customAPIResponse<List<getBalanceReportDTO>>> getBalanceReport(LocalDate fromDate, LocalDate toDate, String bankName, String account, String status) {
        // Check whether user provided date range
        if (fromDate == null || toDate == null) {
            throw new ReportInputDataViolationException("Please provide date range to generate report");
        } else {
            String sql = "SELECT accBal.balance_id, accBal.balance_date, bnk.bank_name, acc.bank_branch, acc.account_number, accBal.balance_amount, CASE WHEN accBal.delete_status = 0 THEN 'Active' ELSE 'Deleted' END AS 'Status', CASE WHEN delUser.user_first_name IS NULL THEN 'N/A' ELSE delUser.user_first_name END AS 'Deleted By', enterUser.user_first_name AS 'Registered By' FROM account_balance accBal LEFT JOIN bank_account acc ON acc.account_id = accBal.account_id LEFT JOIN bank bnk ON bnk.bank_id = acc.bank LEFT JOIN user delUser ON delUser.user_id = accBal.deleted_by LEFT JOIN user enterUser ON enterUser.user_id = accBal.entered_by WHERE accBal.balance_date BETWEEN ? AND DATE_ADD(?, INTERVAL 1 DAY) AND bnk.bank_name LIKE ? AND acc.account_number LIKE ? AND accBal.delete_status LIKE ?";
            List<getBalanceReportDTO> balanceList = template.query(sql, new getBalanceReportMapper(), fromDate, toDate, bankName + "%", account + "%", status + "%");
            if (!balanceList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<List<getBalanceReportDTO>>(
                                true,
                                null,
                                balanceList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<List<getBalanceReportDTO>>(
                                false,
                                "No balance record found for provided criteria",
                                null
                        )
                );
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-045")
    @LogActivity(methodDescription = "This method will fetch all repo details under provided criteria")
    public ResponseEntity<customAPIResponse<List<getRepoReportDTO>>> getRepoReport(LocalDate fromDate, LocalDate toDate, String repoType, String accountNumber, String investStatus, String deleteStatus) {
        // Check whether user provided all required data
        if (fromDate == null || toDate == null) {
            throw new ReportInputDataViolationException("Please provide all required data for report generation");
        } else {
            String sql = "SELECT rpo.repo_id, DATE(rpo.created_date), CASE WHEN rpo.invest_date IS NULL THEN 'N/A' ELSE rpo.invest_date END AS 'Invested Date', CASE WHEN rpo.maturity_date IS NULL THEN 'N/A' ELSE rpo.maturity_date END AS 'Maturity Date', acc.account_number, rpo.repo_value, CASE WHEN rpo.maturity_value IS NULL THEN 'N/A' ELSE rpo.maturity_value END AS 'Maturity Value', CASE WHEN rpo.repo_type = 1 THEN 'Par' WHEN rpo.repo_type = 2 THEN 'Non-Par' WHEN rpo.repo_type = 3 THEN 'Technical Reserve' WHEN rpo.repo_type = 4 THEN 'Shareholder' END AS 'Repo Type', CASE WHEN rpo.interest_rate IS NULL THEN 'N/A' ELSE rpo.interest_rate END AS 'Interest Rate', CASE WHEN rpo.is_invested = 0 THEN 'Not-Invested' ELSE 'Invested' END AS 'Investment Status', CASE WHEN rpo.calculation_method = 0 THEN 'Actual *364' WHEN rpo.calculation_method = 1 THEN 'Actual *365' WHEN rpo.calculation_method IS NULL THEN 'N/A'  END AS 'Calculation Method', CASE WHEN rpo.is_deleted = 0 THEN 'Active' WHEN rpo.is_deleted = 1 THEN 'Deleted' END AS 'Delete Status', CASE WHEN usrDel.user_first_name IS NULL THEN 'N/A' ELSE usrDel.user_first_name END AS 'Deleted By', usrReg.user_first_name AS 'Enter By' FROM repos rpo LEFT JOIN bank_account acc ON acc.account_id = rpo.bank_account LEFT JOIN user usrDel ON usrDel.user_id = rpo.deleted_by LEFT JOIN user usrReg ON usrReg.user_id = rpo.created_by WHERE rpo.created_date BETWEEN ? AND DATE_ADD(?, INTERVAL 1 DAY) AND rpo.repo_type LIKE ? AND acc.account_number LIKE ? AND rpo.is_invested LIKE ? AND  rpo.is_deleted LIKE ? ORDER BY rpo.created_date ASC";
            List<getRepoReportDTO> repoList = template.query(sql, new getRepoReportMapper(), fromDate, toDate, repoType + "%", accountNumber + "%", investStatus + "%", deleteStatus + "%");
            if (!repoList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<List<getRepoReportDTO>>(
                                true,
                                null,
                                repoList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<List<getRepoReportDTO>>(
                                false,
                                "No Repo record found for provided criteria",
                                null
                        )
                );
            }
        }
    }
}
