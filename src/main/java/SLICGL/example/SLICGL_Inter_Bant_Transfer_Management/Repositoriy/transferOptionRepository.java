package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transferOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface transferOptionRepository extends JpaRepository<transferOption, String> {
    @Query(value = "SELECT option_id FROM transfer_option WHERE from_account = ?1 AND to_account = ?2 AND transfer_channel = ?3 AND is_deleted = 0", nativeQuery = true)
    public List<String> availableOption (String fromAccount, String toAccount, String channelId);

    @Query(value = "SELECT option_id FROM transfer_option ORDER BY defined_date DESC LIMIT 1", nativeQuery = true)
    public String getLastOptionId();

    @Modifying
    @Query(value = "UPDATE transfer_option SET is_active = 0 WHERE option_id = ?", nativeQuery = true)
    public int saveDeactivation(String optionId);

    @Modifying
    @Query(value = "UPDATE transfer_option SET is_active = 1 WHERE option_id = ?", nativeQuery = true)
    public int saveActivation(String optionId);

    @Modifying
    @Query(value = "UPDATE transfer_option SET is_deleted = 1, deleted_by = ?1 WHERE option_id = ?2", nativeQuery = true)
    public int deleteTransferOption(String deletedBy, String OptionId);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM transfer_option WHERE from_account = ?1 AND to_account = ?2 AND transfer_channel = ?3 AND is_deleted = 0 AND is_active = 1)", nativeQuery = true)
    public int optionIsAvailable(String fromAccount, String toAccount, String channel);

    @Query(value = "SELECT COUNT(optn.option_ID) FROM transfer_option optn WHERE optn.is_deleted = 0 AND optn.to_account = ?1 OR optn.from_account = ?2", nativeQuery = true)
    public int getOptionCount(String toAccount, String fromAccount);

}
