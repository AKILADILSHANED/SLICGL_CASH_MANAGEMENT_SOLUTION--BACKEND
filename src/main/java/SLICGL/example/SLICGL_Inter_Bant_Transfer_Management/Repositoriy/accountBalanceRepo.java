package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.accountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface accountBalanceRepo extends JpaRepository<accountBalance, String> {
    @Query(value = "SELECT balance_id FROM account_balance WHERE account_id = ?1 AND balance_date = ?2 AND delete_status = 0", nativeQuery = true)
    public String getBalanceIdForBalanceEnter(String accountId, LocalDate balanceDate);

    @Query(value = "SELECT balance_id FROM account_balance ORDER BY balance_id DESC LIMIT 1", nativeQuery = true)
    public String getLastBalanceId();

    @Modifying
    @Query(value = "UPDATE account_balance SET balance_amount = ?1 WHERE balance_id = ?2", nativeQuery = true)
    public int updateAccountBalance(float balanceAmount, String balanceId);

    @Modifying
    @Query(value = "UPDATE account_balance SET delete_status = 1, deleted_by = ?1 WHERE balance_id = ?2", nativeQuery = true)
    public int deleteBalance(String deletedBy, String balanceId);

    @Modifying
    @Query(value = "UPDATE account_balance SET outstanding_balance = ?1 WHERE balance_id = ?2", nativeQuery = true)
    public void updateOutstandingBalance(float outstandingBalance, String balanceId);

    @Query(value = "SELECT outstanding_balance FROM account_balance WHERE balance_id = ?1", nativeQuery = true)
    public float existingBalanceAmount(String balanceId);

    @Query(value = "SELECT tfr.transfer_id FROM account_balance_adjustments adj LEFT JOIN transfers tfr ON tfr.transfer_id = adj.transfer_id LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = adj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND tfr.is_reversed = 0 AND adj.adjustment_balance = ?", nativeQuery = true)
    public List<String> transferIdList(String balanceId);

    @Query(value = "SELECT accBal.balance_id FROM account_balance accBal WHERE accBal.delete_status = 0 AND accBal.account_id = ?1 AND DATE(accBal.balance_date) = ?2 ", nativeQuery = true)
    public String getBalanceId(String accountId, LocalDate currentDate);

    @Query(value = "SELECT((SELECT bal.balance_amount AS 'original_balance' FROM account_balance bal WHERE bal.account_id = ?1 AND DATE(bal.balance_Date) = CURRENT_DATE AND bal.delete_status = 0) + (SELECT CASE WHEN SUM(adj.adjustment_amount) IS NULL THEN 0 ELSE SUM(adj.adjustment_amount) END AS 'available_balance' FROM account_balance_adjustments adj LEFT JOIN  cross_adjustment crsadj ON adj.cross_adjustment_id = crsadj.cross_adjustment_id LEFT JOIN account_balance accBal ON adj.adjustment_balance = accBal.balance_id LEFT JOIN bank_account acc ON accBal.account_id = acc.account_id WHERE DATE(adj.adjustment_date) = CURRENT_DATE AND DATE(accBal.balance_date) = CURRENT_DATE AND accBal.delete_status = 0 AND crsadj.is_reversed = 0 AND acc.account_id = ?2)) AS 'Balance' \n", nativeQuery = true)
    public BigDecimal getAvailableBalance(String accountId01, String accountId02);
}
