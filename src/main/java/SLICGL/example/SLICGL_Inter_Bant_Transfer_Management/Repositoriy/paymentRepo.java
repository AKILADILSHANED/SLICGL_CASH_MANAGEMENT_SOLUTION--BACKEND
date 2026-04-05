package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getPaymentListDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getPaymentsForFundRequestDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface paymentRepo extends JpaRepository<payment, String> {
    @Query(value = "SELECT payment_id FROM payment ORDER BY registered_date DESC LIMIT 1", nativeQuery = true)
    public String getLastPaymentId();

    @Modifying
    @Query(value = "UPDATE payment SET payment_type = ?1 WHERE payment_id = ?2", nativeQuery = true)
    public int updatePayment(String paymentType, String paymentId);

    @Modifying
    @Query(value = "UPDATE payment SET is_deleted = 1, deleted_by = ?1 WHERE payment_id = ?2", nativeQuery = true)
    public int deletePayment(String deletedBy, String paymentId);

    @Query(value = "SELECT payment_id, payment_type FROM payment WHERE is_deleted = 0", nativeQuery = true)
    public List<getPaymentsForFundRequestDTO> paymentListForFundRequest();

    @Query(value = "SELECT payment_id, payment_type FROM payment WHERE is_deleted = 0", nativeQuery = true)
    public List<getPaymentListDTO> paymentList();
}
