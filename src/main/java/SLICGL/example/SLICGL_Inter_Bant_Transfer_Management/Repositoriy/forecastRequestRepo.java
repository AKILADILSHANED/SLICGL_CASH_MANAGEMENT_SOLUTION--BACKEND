package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.forecastRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface forecastRequestRepo extends JpaRepository<forecastRequest, String> {

    @Query(value = "SELECT request_id FROM forecast_request ORDER BY request_date DESC LIMIT 1", nativeQuery = true)
    public String getLastRequestId();

    @Query(value = "SELECT request_id FROM forecast_request WHERE payment = ?1 AND delete_status = 0 AND required_date = ?2", nativeQuery = true)
    public String availableRequest(String payment_Id, LocalDate requiredDate );

    @Modifying
    @Query(value = "UPDATE forecast_request SET bank_account = ?1, request_amount = ?2, payment = ?3 WHERE request_id = ?4", nativeQuery = true)
    public int updateForecastRequest(String accountId, BigDecimal requestAmount, String payment, String requestId);

    @Modifying
    @Query(value = "UPDATE forecast_request SET delete_status = 1, deleted_by = ?1 WHERE request_id = ?2", nativeQuery = true)
    public int deleteFundRequest(String userId, String requestId);

    @Query(value = "SELECT SUM(FRC.request_amount) FROM forecast_request FRC LEFT JOIN payment PMNT ON FRC.payment = PMNT.payment_id LEFT JOIN bank_account ACC ON FRC.bank_account = ACC.account_id LEFT JOIN user USR ON FRC.request_by = USR.user_id WHERE FRC.delete_status = 0 AND DATE(FRC.required_date) >= ?1", nativeQuery = true)
    public float getTotalForecastAmount(LocalDate date);

}
