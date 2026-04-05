package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getChannelsForDefineOptionsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransferChanelForManualTransferDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.transferChanelForTransferHistory;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.transferHistoryDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transferChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface transferChannelRepo extends JpaRepository<transferChannel, String> {

    @Query(value = "SELECT channel_id FROM transfer_channel ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    public String getLastChannelId();

    @Modifying
    @Query(value = "UPDATE transfer_channel SET deleted_status = 1, delete_by = ? WHERE channel_id = ?", nativeQuery = true)
    public int removeChannel(String deleteBy, String channelId);

    @Modifying
    @Query(value = "UPDATE transfer_channel SET priority_level = ? WHERE channel_id = ?", nativeQuery = true)
    public int updateNewLevel(int newLevel, String channelId);

    @Query(value = "SELECT channel_id, channel_type FROM transfer_channel WHERE deleted_status = 0", nativeQuery = true)
    public List<transferChanelForTransferHistory> chanelList();

    @Query(value = "SELECT chnl.channel_id FROM transfer_channel chnl WHERE chnl.deleted_status = 0 ORDER BY chnl.priority_level ASC", nativeQuery = true)
    public List<String> channelsWithPriorities();

    @Query(value = "select chnl.channel_id, chnl.channel_type, chnl.short_key from transfer_channel chnl WHERE chnl.deleted_status = 0", nativeQuery = true)
    public List<getTransferChanelForManualTransferDTO> channelsListForManualTransfer();

}
