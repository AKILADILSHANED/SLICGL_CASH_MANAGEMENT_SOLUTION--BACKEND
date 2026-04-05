package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.accountBalanceAdjustments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface accountBalanceAdjustmentsRepo extends JpaRepository<accountBalanceAdjustments, String> {
    @Query(value = "SELECT adjustment_id FROM account_balance_adjustments WHERE adjustment_id LIKE CONCAT('ADJ-', DATE_FORMAT(CURDATE(), '%Y%m'), '-%') ORDER BY adjustment_id DESC LIMIT 1", nativeQuery = true)
    public String getLastAdjustmentId();

    @Query(value = "SELECT crsadj.cross_adjustment_id FROM cross_adjustment crsadj LEFT JOIN account_balance_adjustments baladj ON baladj.cross_adjustment_id = crsadj.cross_adjustment_id LEFT JOIN account_balance bal ON bal.balance_id = baladj.adjustment_balance WHERE crsadj.is_reversed = 0 AND bal.balance_id = ?", nativeQuery = true)
    public List<String> getAvailableAdjustments(String balanceId);
}
