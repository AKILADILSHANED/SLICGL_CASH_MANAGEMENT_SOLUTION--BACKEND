package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface repoService {
    public ResponseEntity<customAPIResponse<String>> createNewRepo(createNewRepoDTO newRepoObject);

    public ResponseEntity<customAPIResponse<List<displayRepoDTO>>> displayRepo(String repoId);

    public ResponseEntity<customAPIResponse<List<getFromRepoListDTO>>> getToRepoList(String selectedRepo);

    public ResponseEntity<customAPIResponse<List<getFromRepoListDTO>>> getFromRepoList();

    public ResponseEntity<customAPIResponse<String>> adjustmentNewRepo(adjustmentNewRepoDTO adjustmentNewRepo);

    public ResponseEntity<customAPIResponse<String>> existingRepoTransfer(adjustmentExistingRepoDTO adjustmentExistingRepo);

    public ResponseEntity<customAPIResponse<String>> repoDelete(String repoId);

    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForInvestments();

    public ResponseEntity<customAPIResponse<String>> initiateInvestment(String repoId, LocalDate toDate, String rate, Integer method, BigDecimal maturityValue);

    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForInvestmentReverse();

    public ResponseEntity<customAPIResponse<String>> investmentReverse(String repoId);

    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForPrint(LocalDate repoDate);

    public ResponseEntity<customAPIResponse<List<getRepoOpeningBalancesDTO>>> getRepoOpeningBalances(LocalDate repoDate);

    public ResponseEntity<customAPIResponse<List<getRepoClosingBalancesDTO>>> getRepoClosingBalances(LocalDate repoDate);

    public ResponseEntity<customAPIResponse<List<getRepoAccountsListDTO>>> getRepoAccountsList();

    public ResponseEntity<customAPIResponse<List<repoListForManualTransfersDTO>>> getReposForManualTransfers();

    public ResponseEntity<customAPIResponse<BigDecimal>> getAvailableBalances(String repoId);

}
