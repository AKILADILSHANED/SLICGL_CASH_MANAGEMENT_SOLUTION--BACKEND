package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoListForManualTransfersDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.Repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface repoRepository extends JpaRepository<Repos, String> {
    @Query(value = "SELECT repo_id FROM repos ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    public String getLastRepoId();

    @Query(value = "SELECT COALESCE((rpo.repo_value+(SELECT COALESCE(SUM(rpoadj.adjustment_amount),0) FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = rpoadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND rpoadj.adjusted_repo = ?1)),0) AS 'closing_balance' FROM repos rpo WHERE rpo.repo_id = ?2", nativeQuery = true)
    public BigDecimal getRepoClosingBalance(String repoId1, String repoId2);

    @Query(value = "SELECT rpo.bank_account FROM repos rpo WHERE rpo.repo_id = ?1", nativeQuery = true)
    public String getRepoBankAccount(String repoId);

    @Query(value = "SELECT bank_account FROM repos WHERE repo_id = ?1", nativeQuery = true)
    public String getFromRepoBankAccountId(String fromRepoId);

    @Query(value = "SELECT CASE WHEN rpo.is_deleted = 0 THEN FALSE ELSE TRUE END AS 'delete_status' FROM repos rpo WHERE rpo.repo_id = ?", nativeQuery = true)
    public int isRepoDeleted(String repoId);

    @Query(value = "SELECT CASE WHEN rpo.is_invested = 0 THEN FALSE ELSE TRUE END AS 'delete_status' FROM repos rpo WHERE rpo.repo_id = ?", nativeQuery = true)
    public int isInvestedRepo(String repoId);

    @Query(value = "SELECT IF(DATE_FORMAT(rpo.created_date, '%y-%m-%d') = CURRENT_DATE, TRUE, FALSE) AS 'is_repo_same_date' FROM repos rpo WHERE rpo.repo_id = ?1", nativeQuery = true)
    public int isRepoSameDate(String repoId);

    @Modifying
    @Query(value = "UPDATE repos rpo SET rpo.is_deleted = 1, rpo.deleted_by = ?1 WHERE rpo.repo_id = ?2", nativeQuery = true)
    public int repoDelete(String deletedUser, String repoId);

    @Query(value = "SELECT COUNT(rpoadj.adjustment_id) AS 'adjustments' FROM repo_adjustment rpoadj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = rpoadj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND rpoadj.adjusted_repo = ?1", nativeQuery = true)
    public int adjustmentCount(String repoId);

    @Modifying
    @Query(value = "UPDATE repos rpo SET rpo.interest_rate = ?1, rpo.invest_date = CURRENT_DATE, rpo.maturity_date = ?2, rpo.is_invested = 1, rpo.calculation_method = ?3, rpo.maturity_value = ?4 WHERE rpo.repo_id = ?5", nativeQuery = true)
    public int investRepo(String rate, LocalDate maturityDate, int method, BigDecimal maturityValue, String repoId);

    @Query(value = "SELECT IF(MAX(rpo.is_invested) = 1, 'true', 'false') as is_invested FROM repos rpo LEFT JOIN repo_adjustment rpoadj ON rpoadj.adjusted_repo = rpo.repo_id LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = rpoadj.cross_adjustment_id WHERE crsadj.cross_adjustment_id = ?1", nativeQuery = true)
    public boolean investmentStatus(String adjustmentId);

    @Modifying
    @Query(value = "UPDATE repos rpo SET rpo.interest_rate = null, rpo.invest_date = null, rpo.maturity_date = null, rpo.is_invested = 0, rpo.calculation_method = null, rpo.maturity_value = 0 WHERE rpo.repo_id = ?1", nativeQuery = true)
    public int reverseInvestment(String repoId);

    @Query(value = "SELECT((SELECT repo.repo_value AS 'original_balance' FROM repos repo WHERE repo.repo_id = ?1 AND repo.is_deleted = 0 AND repo.is_invested = 0 AND DATE(repo.created_date) = CURRENT_DATE) + (SELECT CASE WHEN SUM(rpoAdj.adjustment_amount) IS NULL THEN 0 ELSE SUM(rpoAdj.adjustment_amount) END AS 'Net_Adjustments' FROM repo_adjustment rpoAdj LEFT JOIN cross_adjustment crsAdj ON rpoAdj.cross_adjustment_id = crsAdj.cross_adjustment_id WHERE DATE(rpoAdj.adjustment_Date) = CURRENT_DATE AND rpoAdj.adjusted_repo = ?2 AND crsAdj.is_reversed = 0 AND DATE(crsAdj.adjusted_date) = CURRENT_DATE)) AS 'Balance'", nativeQuery = true)
    public BigDecimal getAvailableBalance(String repoId01, String repoId02);
}
