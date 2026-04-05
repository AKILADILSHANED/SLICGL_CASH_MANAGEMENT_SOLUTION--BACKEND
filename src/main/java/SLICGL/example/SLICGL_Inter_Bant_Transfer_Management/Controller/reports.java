package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.reportIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reports")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class reports {
    @Autowired
    reportIMPL reportIMPL;

    @GetMapping(value = "/fundRequestHistory")
    public ResponseEntity<customAPIResponse<List<fundRequestHistoryDTO>>> fundRequestHistory(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate, @RequestParam String paymentId) {
        return reportIMPL.fundRequestHistory(fromDate, toDate, paymentId);
    }

    @GetMapping(value = "/transferHistory")
    public ResponseEntity<customAPIResponse<List<transferHistoryDTO>>> transferHistory(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate, @RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam String chanel) {
        return reportIMPL.transferHistory(fromDate, toDate, fromAccount, toAccount, chanel);
    }

    @GetMapping(value = "/get-user-report")
    public ResponseEntity<customAPIResponse<List<userReportDTO>>> getUserReport() {
        return reportIMPL.getUserReport();
    }

    @GetMapping(value = "/get-account-report")
    public ResponseEntity<customAPIResponse<List<accountReportDTO>>> getAccountReport() {
        return reportIMPL.getAccountReport();
    }

    @GetMapping(value = "/get-payment-report")
    public ResponseEntity<customAPIResponse<List<paymentReportDTO>>> getPaymentReport() {
        return reportIMPL.getPaymentReport();
    }

    @GetMapping(value = "/get-balance-report")
    public ResponseEntity<customAPIResponse<List<getBalanceReportDTO>>> getBalanceReport(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate, @RequestParam String bankName, @RequestParam String account, @RequestParam String status) {
        return reportIMPL.getBalanceReport(fromDate, toDate, bankName, account, status);
    }

    @GetMapping(value = "/get-repo-report")
    public ResponseEntity<customAPIResponse<List<getRepoReportDTO>>> getRepoReport(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate, @RequestParam String repoType, @RequestParam String accountNumber, @RequestParam String investStatus, @RequestParam String deleteStatus) {
        return reportIMPL.getRepoReport(fromDate, toDate, repoType, accountNumber, investStatus, deleteStatus);
    }
}
