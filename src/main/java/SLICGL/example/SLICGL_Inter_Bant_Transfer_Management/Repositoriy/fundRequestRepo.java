package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.fundRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface fundRequestRepo extends JpaRepository<fundRequest, String> {

    @Query(value = "SELECT request_id FROM fund_request ORDER BY request_date DESC LIMIT 1", nativeQuery = true)
    public String getLastRequestId();

    @Query(value = "SELECT request_id FROM fund_request WHERE payment = ?1 AND delete_status = 0 AND required_date = ?2", nativeQuery = true)
    public String availableRequest(String payment_Id, LocalDate requiredDate );

    @Modifying
    @Query(value = "UPDATE fund_request SET bank_account = ?1, request_amount = ?2, payment = ?3 WHERE request_id = ?4", nativeQuery = true)
    public int updateFundRequest(String accountId, BigDecimal requestAmount, String payment, String requestId);

    @Modifying
    @Query(value = "UPDATE fund_request SET delete_status = 1, deleted_by = ?1 WHERE request_id = ?2", nativeQuery = true)
    public int deleteFundRequest(String userId, String requestId);

    @Modifying
    @Query(value = "UPDATE fund_request SET approve_status = 1, approved_by = ?1 WHERE request_id = ?2", nativeQuery = true)
    public int approveRequest(String userId, String requestId);

    @Modifying
    @Query(value = "UPDATE fund_request SET approve_status = 0, approved_by = null WHERE request_id = ?1", nativeQuery = true)
    public int reverseApproval(String requestId);

    @Query(value = "SELECT outstanding_amount FROM fund_request WHERE request_id = ?1", nativeQuery = true)
    public float existingRequestAmount(String requestId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM account_balance WHERE account_id = ?1 AND delete_status = 0 AND balance_date = ?2)", nativeQuery = true)
    public int accountBalanceForFundRequest(String accountId, LocalDate date);

    @Query(value = "SELECT bal.balance_id FROM fund_request req LEFT JOIN bank_account acc ON acc.account_id = req.bank_account LEFT JOIN account_balance bal ON bal.account_id = acc.account_id AND bal.delete_status = 0 AND DATE(bal.balance_date) = ?1 WHERE req.delete_status = 0 AND req.approve_status = 1 AND DATE(req.required_date) = ?2 AND bal.balance_id IS NULL", nativeQuery = true)
    public List<String> nullBalanceRequest(LocalDate balanceDate, LocalDate requiredDate);

    @Query(value ="SELECT EXISTS(SELECT 1 FROM fund_request req WHERE req.payment = ?1 AND req.required_date = (SELECT required_date FROM fund_request WHERE request_id = ?2) AND req.request_id != ?3) AS result", nativeQuery = true)
    public Integer existsByPaymentAndRequestDate(String paymentId, String requestId1, String requestId2);
}
