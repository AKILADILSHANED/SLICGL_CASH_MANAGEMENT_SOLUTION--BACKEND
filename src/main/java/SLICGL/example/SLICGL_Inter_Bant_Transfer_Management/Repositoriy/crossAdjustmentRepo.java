package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.crossAdjustments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface crossAdjustmentRepo extends JpaRepository<crossAdjustments, String> {
    @Query(value = "SELECT cross_adjustment_id FROM cross_adjustment WHERE cross_adjustment_id LIKE CONCAT('CRSADJ-', DATE_FORMAT(CURDATE(), '%Y%m'), '-%') ORDER BY cross_adjustment_id DESC LIMIT 1", nativeQuery = true)
    public String getLastAdjustmentId();

    @Modifying
    @Query(value = "UPDATE cross_adjustment crsadj SET crsadj.is_reversed = 1, crsadj.reversed_by = ?1 WHERE crsadj.cross_adjustment_id = ?2", nativeQuery = true)
    public int crossAdjustmentDeletion(String userId, String adjustmentId);

    @Query(value = "SELECT crsadj.cross_adjustment_id FROM account_balance bal LEFT JOIN account_balance_adjustments baladj ON baladj.adjustment_balance = bal.balance_id LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = baladj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND bal.delete_status = 0 AND bal.balance_id = ?1", nativeQuery = true)
    public List<String> getCrossAdjustmentList(String balanceId);
}
