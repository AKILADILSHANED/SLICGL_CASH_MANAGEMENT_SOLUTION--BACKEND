package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
public interface reportService {
    public ResponseEntity<customAPIResponse<List<fundRequestHistoryDTO>>> fundRequestHistory(LocalDate fromDate, LocalDate toDate, String paymentId);

    public ResponseEntity<customAPIResponse<List<transferHistoryDTO>>> transferHistory(LocalDate fromDate, LocalDate toDate, String fromAccount, String toAccount, String chanel);

    public ResponseEntity<customAPIResponse<List<userReportDTO>>> getUserReport();

    public ResponseEntity<customAPIResponse<List<accountReportDTO>>> getAccountReport();

    public ResponseEntity<customAPIResponse<List<paymentReportDTO>>> getPaymentReport();

    public ResponseEntity<customAPIResponse<List<getBalanceReportDTO>>> getBalanceReport(LocalDate fromDate, LocalDate toDate, String bankName, String account, String status);

    public ResponseEntity<customAPIResponse<List<getRepoReportDTO>>> getRepoReport(LocalDate fromDate, LocalDate toDate, String repoType, String accountNumber, String investStatus, String deleteStatus);
}
