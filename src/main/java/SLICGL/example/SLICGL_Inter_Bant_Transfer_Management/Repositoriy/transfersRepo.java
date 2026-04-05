package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.checkUserForApproveDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transferChannel;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface transfersRepo extends JpaRepository<transfers, String> {
    @Query(value = "SELECT transfer_id FROM transfers WHERE transfer_id LIKE CONCAT('TFR-', DATE_FORMAT(CURDATE(), '%Y%m'), '-%') ORDER BY transfer_id DESC LIMIT 1", nativeQuery = true)
    public String getLastTransferId();

    @Modifying
    @Query(value = "UPDATE transfers SET is_approved = 1, approved_by = ?1, approved_date = ?2 WHERE transfer_id = ?3", nativeQuery = true)
    public int approveTransfer(String user, LocalDateTime date, String transferId);

    @Query(value = "SELECT DATE(TFR.transferDate) FROM transfers TFR WHERE TFR.transferId = :transferId", nativeQuery = true)
    public LocalDate getTransferDate(String transferId);

    @Modifying
    @Query(value = "UPDATE transfers TFR SET TFR.isApproved = 0, TFR.approvedBy = NULL WHERE TFR.transferId = :transferId")
    public int updateRejection(@Param("transferId") String transferId);

    @Modifying
    @Query(value = "UPDATE transfers TFR SET TFR.isReversed = 1, TFR.reversedBy = :reversedBy WHERE TFR.transferId = :transferId")
    public int updateReversal(@Param("reversedBy") User user, @Param("transferId") String transferId);

    @Query("SELECT TFR.transferId FROM transfers TFR WHERE DATE(TFR.transferDate) = :transferDate AND TFR.isReversed = 0")
    public List<String> getTransferIdList(@Param("transferDate") LocalDate transferDate);

    @Query("SELECT COUNT(*) AS totalTransfers FROM transfers WHERE DATE(transferDate) = :transferDate")
    public int getTotalTransferCount(@Param("transferDate") LocalDate transferDate);

    @Query("SELECT COUNT(*) AS totalTransfers FROM transfers WHERE DATE(transferDate) = :transferDate AND isApproved = 1")
    public int getTotalApproveTransferCount(@Param("transferDate") LocalDate transferDate);

    @Query("SELECT COUNT(*) AS totalTransfers FROM transfers WHERE DATE(transferDate) = :transferDate AND isApproved = 0 AND isReversed = 0")
    public int getTotalPendingTransferCount(@Param("transferDate") LocalDate transferDate);

    @Query("SELECT COUNT(*) AS totalTransfers FROM transfers WHERE DATE(transferDate) = :transferDate AND isReversed = 1")
    public int getTotalRejectedTransferCount(@Param("transferDate") LocalDate transferDate);

    @Query(value = "SELECT COUNT(TFR.transfer_id) FROM transfers TFR WHERE DATE(TFR.transfer_date) = ?1 AND chanel = ?2", nativeQuery = true)
    public int getTotalCEFTTransferChanelCount(LocalDate date, String chanel);

    @Query(value = "SELECT COUNT(TFR.transfer_id) FROM transfers TFR WHERE DATE(TFR.transfer_date) = ?1 AND chanel = ?2", nativeQuery = true)
    public int getTotalIBTTransferCount(LocalDate date, String chanel);

    @Query(value = "SELECT COUNT(TFR.transfer_id) FROM transfers TFR WHERE DATE(TFR.transfer_date) = ?1 AND chanel = ?2", nativeQuery = true)
    public int getTotalRTGSTransferCount(LocalDate date, String chanel);

    @Query(value = "SELECT COUNT(TFR.transfer_id) FROM transfers TFR WHERE DATE(TFR.transfer_date) = ?1 AND chanel = ?2", nativeQuery = true)
    public int getTotalChequeTransferCount(LocalDate date, String chanel);

    @Modifying
    @Query(value = "UPDATE transfers SET is_checked = 1, checked_by = ?1, checked_date = ?2 WHERE transfer_id = ?3", nativeQuery = true)
    public int checkTransfer(String user, LocalDateTime date, String transferId);

    @Query(value = "SELECT initiated_by, checked_by FROM transfers WHERE transfer_id = ?", nativeQuery = true)
    public checkUserForApproveDTO checkUserForApprove(String transferId);

    @Query(value = "SELECT initiated_by FROM transfers WHERE transfer_id = ?", nativeQuery = true)
    public String checkUserForCheck(String transferId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM repos WHERE transfer_eligibility = 1 AND is_deleted = 0 AND is_invested = 0 AND DATE(created_date) = ?1)", nativeQuery = true)
    public int checkFundTransferableRepo(LocalDate date);

    @Modifying
    @Query(value = "UPDATE transfers TFR SET tfr.is_checked = 0, TFR.checked_date = NULL, TFR.checked_by = NULL WHERE TFR.transfer_id = ?1", nativeQuery = true)
    public int saveCheckRejection(String transferId);

    @Modifying
    @Query(value = "UPDATE transfers TFR SET tfr.is_approved = 0, TFR.approved_date = NULL, TFR.approved_by = NULL WHERE TFR.transfer_id = ?1", nativeQuery = true)
    public int saveApprovalRejection(String transferId);

    @Query(value = "SELECT COALESCE(accBal.balance_amount + (SELECT COALESCE(SUM(baladj.adjustment_amount),0) FROM account_balance_adjustments baladj LEFT JOIN cross_adjustment crsadj ON baladj.cross_adjustment_id = crsadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND baladj.adjustment_balance = accBal.balance_id),0) AS 'Available Balance' FROM account_balance accBal WHERE DATE(accBal.balance_date) = ?1 AND accBal.account_id = ?2", nativeQuery = true)
    public BigDecimal availableBalance(LocalDate currentDate, String accountId);

}
