package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PrintingExceptions.PrintingInputDataViolationException;
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
public class printingIMPL implements printingService {
    @Autowired
    JdbcTemplate template;

    @Override
    @Session()
    @RequiresPermission("FUNC-046")
    @LogActivity(methodDescription = "This method will display IBT sheet for printing")
    public ResponseEntity<customAPIResponse<List<printIBTSheetDTO>>> printIBTSheet(LocalDate sheetDate) {
        // Check whether user provided sheet date
        if (sheetDate == null) {
            throw new PrintingInputDataViolationException("Please provide IBT sheet date");
        } else {
            String Sql = "SELECT tfr.transfer_id, from_bnk.bank_name AS from_bank, from_acc.account_number as from_account, to_bnk.bank_name as to_bank, to_acc.account_number as to_account, tfr.transfer_amount, chnl.short_key FROM transfers tfr LEFT JOIN bank_account from_acc ON tfr.from_account = from_acc.account_id LEFT JOIN bank from_bnk ON from_acc.bank = from_bnk.bank_id LEFT JOIN bank_account to_acc ON tfr.to_account = to_acc.account_id LEFT JOIN bank to_bnk ON to_acc.bank = to_bnk.bank_id LEFT JOIN transfer_channel chnl ON tfr.chanel = chnl.channel_id WHERE tfr.is_approved = 1 AND tfr.is_reversed = 0 AND tfr.is_checked = 1 AND DATE(tfr.transfer_date) = ?";
            List<printIBTSheetDTO> printSheet = template.query(Sql, new printIBTSheetMapper(), sheetDate);
            if (!printSheet.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        printSheet
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "No data found for provided date",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-047")
    @LogActivity(methodDescription = "This method will display transfer details for print vouchers and letters for banks")
    public ResponseEntity<customAPIResponse<List<getTransferListDTO>>> getTransferList(LocalDate transferDate) {
        // Check whether user provided transfer date
        if (transferDate == null) {
            throw new PrintingInputDataViolationException("Please provide transfer date");
        } else {
            String Sql = "SELECT tfr.transfer_id, from_bnk.bank_name AS from_bank, from_acc.account_number as from_account, to_bnk.bank_name as to_bank, to_acc.account_number as to_account, tfr.transfer_amount, chnl.short_key FROM transfers tfr LEFT JOIN bank_account from_acc ON tfr.from_account = from_acc.account_id LEFT JOIN bank from_bnk ON from_acc.bank = from_bnk.bank_id LEFT JOIN bank_account to_acc ON tfr.to_account = to_acc.account_id LEFT JOIN bank to_bnk ON to_acc.bank = to_bnk.bank_id LEFT JOIN transfer_channel chnl ON tfr.chanel = chnl.channel_id WHERE tfr.is_approved = 1 AND tfr.is_reversed = 0 AND tfr.is_checked = 1 AND DATE(tfr.transfer_date) = ?";
            List<getTransferListDTO> transferList = template.query(Sql, new getTransferListMapper(), transferDate);
            if (!transferList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        transferList
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "No transfer data found for provided date",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-047")
    @LogActivity(methodDescription = "This method will display voucher for print")
    public ResponseEntity<customAPIResponse<getVoucherDetailsDTO>> getVoucherDetails(String voucherNo) {
        // Check whether user provided voucher number
        if (voucherNo == null) {
            throw new PrintingInputDataViolationException("Please provide voucher number");
        } else {
            String Sql = "SELECT tfr.transfer_id AS 'Voucher Number', DATE_FORMAT(tfr.transfer_date, '%d/%m/%Y') AS 'Date', COALESCE(prepared_user.user_first_name, \"\") AS 'prepared_by', COALESCE(checked_by.user_first_name, \"\") AS 'checked_by', COALESCE(approved_by.user_first_name,\"\") AS 'approved_by', COALESCE(prepared_user.user_position, \"\") AS 'prepared_user_position', COALESCE(checked_by.user_position, \"\") AS 'checked_user_position', COALESCE(approved_by.user_position, \"\") AS 'approved_user_position', COALESCE(prepared_user.user_signature, \"\") AS 'prepared_signature', COALESCE(checked_by.user_signature, \"\") AS 'checked_signature', COALESCE(approved_by.user_signature, \"\") AS 'approved_signature', toacc.account_number AS 'to account', toacc.bank_branch AS 'to branch', tobank.bank_name AS 'to bank', CONCAT(SUBSTRING(toacc.gl_code, 1, LENGTH(toacc.gl_code) - 1), '1') AS 'to_gl', tfr.transfer_amount, from_account.account_number AS 'from account', from_bank.bank_name AS 'from bank', from_account.bank_branch AS 'from_branch', CONCAT(SUBSTRING(toacc.gl_code, 1, LENGTH(toacc.gl_code) - 1), '2') AS 'from_gl' FROM transfers tfr LEFT JOIN bank_account toacc ON tfr.to_account = toacc.account_id LEFT JOIN bank tobank ON toacc.bank = tobank.bank_id LEFT JOIN bank_account from_account ON tfr.from_account = from_account.account_id LEFT JOIN bank from_bank ON from_bank.bank_id = from_account.bank LEFT JOIN user prepared_user ON prepared_user.user_id = tfr.initiated_by LEFT JOIN user checked_by ON checked_by.user_id = tfr.checked_by LEFT JOIN user approved_by ON approved_by.user_id = tfr.approved_by WHERE tfr.transfer_id = ?";
            List<getVoucherDetailsDTO> voucherDetail = template.query(Sql, new getVoucherDetailsMapper(), voucherNo);
            if (!voucherDetail.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        voucherDetail.get(0)
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "Voucher details not found",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-047")
    @LogActivity(methodDescription = "This method will display IBT letters for print")
    public ResponseEntity<customAPIResponse<getIbtLetterDetailsDTO>> getIbtLetterDetails(String voucherNo) {
        // Check whether user provided voucher number
        if (voucherNo == null) {
            throw new PrintingInputDataViolationException("Please provide voucher number");
        } else {
            String Sql = "SELECT frombank.bank_name AS 'From Bank', fromacc.account_number AS 'From Account', fromacc.bank_branch AS 'From Bank Brabch',  tobank.bank_name AS 'To Bank', toacc.account_number AS 'To Account', toacc.bank_branch AS 'To Branch', tfr.transfer_amount AS 'Transfer Amount', DATE_FORMAT(tfr.transfer_date, '%d.%m.%Y') AS 'Value Date' FROM transfers tfr LEFT JOIN bank_account fromacc ON fromacc.account_id = tfr.from_account LEFT JOIN bank frombank on frombank.bank_id = fromacc.bank LEFT JOIN bank_account toacc ON tfr.to_account = toacc.account_id LEFT JOIN bank tobank ON toacc.bank = tobank.bank_id WHERE tfr.transfer_id = ?";
            List<getIbtLetterDetailsDTO> transferDetails = template.query(Sql, new getIbtLetterDetailsMapper(), voucherNo);
            if (!transferDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        transferDetails.get(0)
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "Letter details not found",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-047")
    @LogActivity(methodDescription = "This method will display RTGS letters for print")
    public ResponseEntity<customAPIResponse<getIbtLetterDetailsDTO>> getRtgsLetterDetails(String voucherNo) {
        // Check whether user provided voucher number
        if (voucherNo == null) {
            throw new PrintingInputDataViolationException("Please provide voucher number");
        } else {
            String Sql = "SELECT frombank.bank_name AS 'From Bank', fromacc.account_number AS 'From Account', fromacc.bank_branch AS 'From Bank Brabch',  tobank.bank_name AS 'To Bank', toacc.account_number AS 'To Account', toacc.bank_branch AS 'To Branch', tfr.transfer_amount AS 'Transfer Amount', DATE_FORMAT(tfr.transfer_date, '%d.%m.%Y') AS 'Value Date' FROM transfers tfr LEFT JOIN bank_account fromacc ON fromacc.account_id = tfr.from_account LEFT JOIN bank frombank on frombank.bank_id = fromacc.bank LEFT JOIN bank_account toacc ON tfr.to_account = toacc.account_id LEFT JOIN bank tobank ON toacc.bank = tobank.bank_id WHERE tfr.transfer_id = ?";
            List<getIbtLetterDetailsDTO> transferDetails = template.query(Sql, new getIbtLetterDetailsMapper(), voucherNo);
            if (!transferDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        transferDetails.get(0)
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new customAPIResponse<>(
                        false,
                        "RTGS Letter details not found",
                        null
                ));
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-048")
    @LogActivity(methodDescription = "This method will display REPO letters for print")
    public ResponseEntity<customAPIResponse<List<repoDataForPrintDTO>>> getRepoDataForPrint(String repoId) {
        //Check whether user provided repo id
        if (repoId == null) {
            throw new PrintingInputDataViolationException("Please provide repo id");
        } else {
            String sql = "SELECT rpo.interest_rate AS 'Interest Rate', rpo.invest_date AS 'Investment Date', rpo.maturity_date AS 'Maturity Date', COALESCE((rpo.repo_value + (SELECT COALESCE(SUM(rpoadj.adjustment_amount),0) AS 'Net Adjustment' FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON rpoadj.cross_adjustment_id = crsadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND rpoadj.adjusted_repo = ?)), 0) AS 'Repo Value', DATEDIFF(rpo.maturity_date, rpo.invest_date) AS 'Number of Days', acc.account_number AS 'Account', bnk.bank_name AS 'Bank', acc.bank_branch FROM repos rpo LEFT JOIN bank_account ACC ON acc.account_id = rpo.bank_account LEFT JOIN bank bnk ON bnk.bank_id = acc.bank WHERE rpo.repo_id = ?";
            List<repoDataForPrintDTO> repoDataList = template.query(sql, new repoDataForPrintMapper(), repoId, repoId);
            if (!repoDataList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                repoDataList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No Repo details found",
                                null
                        )
                );
            }
        }
    }
}
