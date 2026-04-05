package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.User;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.repoAdjustments;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface repoAdjustmentRepo extends JpaRepository<repoAdjustments, String> {
    @Query(value = "SELECT adjustment_id FROM repo_adjustment WHERE adjustment_id LIKE CONCAT('RPOADJ-', DATE_FORMAT(CURDATE(), '%Y%m'), '-%') ORDER BY adjustment_id DESC LIMIT 1", nativeQuery = true)
    public String getLastAdjustmentId();
}
