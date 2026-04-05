package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.repoAdjustmentIMPL;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.repoAdjustmentService;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.repoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/repo")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class repoController {

    @Autowired
    repoService repoIMPL;
    @Autowired
    repoAdjustmentService repoAdjustmentService;
    @Autowired
    repoAdjustmentIMPL repoAdjustmentIMPL;

    @PutMapping(value = "/create-new-repo")
    public ResponseEntity<customAPIResponse<String>> createNewRepo(@RequestBody createNewRepoDTO newRepoObject) {
        return repoIMPL.createNewRepo(newRepoObject);
    }

    @GetMapping(value = "/get-adjustments")
    public ResponseEntity<customAPIResponse<List<repoAdjustmentsDTO>>> getAdjustments(@RequestParam String repoId) {
        return repoAdjustmentService.getAdjustments(repoId);
    }

    @GetMapping(value = "/display-repo")
    public ResponseEntity<customAPIResponse<List<displayRepoDTO>>> displayRepo(@RequestParam String repoId) {
        return repoIMPL.displayRepo(repoId);
    }

    @GetMapping(value = "/get-from-Repo-List")
    public ResponseEntity<customAPIResponse<List<getFromRepoListDTO>>> getFromRepoList() {
        return repoIMPL.getFromRepoList();
    }

    @GetMapping(value = "/get-To-Repo-List")
    public ResponseEntity<customAPIResponse<List<getFromRepoListDTO>>> getToRepoList(@RequestParam String selectedRepo) {
        return repoIMPL.getToRepoList(selectedRepo);
    }

    @PostMapping(value = "/create-new-repo")
    public ResponseEntity<customAPIResponse<String>> adjustmentNewRepo(@RequestBody adjustmentNewRepoDTO adjustmentNewRepo) {
        return repoIMPL.adjustmentNewRepo(adjustmentNewRepo);
    }

    @GetMapping(value = "/get-adjustment-details")
    public ResponseEntity<customAPIResponse<List<getCrossAdjustmentDetailsDTO>>> adjustmentDetails(@RequestParam String adjustmentId) {
        return repoAdjustmentIMPL.adjustmentDetails(adjustmentId);
    }

    @PutMapping(value = "/delete-adjustment")
    public ResponseEntity<customAPIResponse<String>> adjustmentDelete(@RequestParam String adjustmentId) {
        return repoAdjustmentIMPL.adjustmentDelete(adjustmentId);
    }

    @PostMapping(value = "/transfer-repo")
    public ResponseEntity<customAPIResponse<String>> existingRepoTransfer(@RequestBody adjustmentExistingRepoDTO adjustmentExistingRepo) {
        return repoIMPL.existingRepoTransfer(adjustmentExistingRepo);
    }

    @PutMapping(value = "/repo-delete")
    public ResponseEntity<customAPIResponse<String>> repoDelete(@RequestParam String repoId) {
        return repoIMPL.repoDelete(repoId);
    }

    @GetMapping(value = "/show-repo-adjustments")
    public ResponseEntity<customAPIResponse<List<String>>> showAdjustments(@RequestParam String repoId) {
        return repoAdjustmentIMPL.showAdjustments(repoId);
    }

    @GetMapping(value = "/display-repo-details")
    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForInvestments() {
        return repoIMPL.repoDetailsForInvestments();
    }

    @PutMapping(value = "/invest-repo")
    public ResponseEntity<customAPIResponse<String>> initiateInvestment(@RequestParam String repoId, @RequestParam LocalDate toDate, @RequestParam String rate, @RequestParam int method, @RequestParam BigDecimal maturityValue) {
        return repoIMPL.initiateInvestment(repoId, toDate, rate, method, maturityValue);
    }

    @GetMapping(value = "/display-invested-repo-details")
    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForInvestmentReverse() {
        return repoIMPL.repoDetailsForInvestmentReverse();
    }

    @PutMapping(value = "/investment-reverse")
    public ResponseEntity<customAPIResponse<String>> investmentReverse(@RequestParam String repoId) {
        return repoIMPL.investmentReverse(repoId);
    }

    @GetMapping(value = "/display-repo-details-for-print")
    public ResponseEntity<customAPIResponse<List<repoDetailsForInvestmentsDTO>>> repoDetailsForPrint(@RequestParam LocalDate repoDate) {
        return repoIMPL.repoDetailsForPrint(repoDate);
    }

    @GetMapping(value = "/get-repo-opening-balances")
    public ResponseEntity<customAPIResponse<List<getRepoOpeningBalancesDTO>>> getRepoOpeningBalances(@RequestParam LocalDate repoDate) {
        return repoIMPL.getRepoOpeningBalances(repoDate);
    }

    @GetMapping(value = "/get-repo-closing-balances")
    public ResponseEntity<customAPIResponse<List<getRepoClosingBalancesDTO>>> getRepoClosingBalances(@RequestParam LocalDate repoDate) {
        return repoIMPL.getRepoClosingBalances(repoDate);
    }

    @GetMapping(value = "/get-repo")
    public ResponseEntity<customAPIResponse<List<getRepoAccountsListDTO>>> getRepoAccountsList() {
        return repoIMPL.getRepoAccountsList();
    }

    @GetMapping(value = "/reposForManualTransfers")
    public ResponseEntity<customAPIResponse<List<repoListForManualTransfersDTO>>> getReposForManualTransfers() {
        return repoIMPL.getReposForManualTransfers();
    }

    @GetMapping(value = "/get-available-balance")
    public ResponseEntity<customAPIResponse<BigDecimal>> getAvailableBalances(@RequestParam String repoId) {
        return repoIMPL.getAvailableBalances(repoId);
    }
}
