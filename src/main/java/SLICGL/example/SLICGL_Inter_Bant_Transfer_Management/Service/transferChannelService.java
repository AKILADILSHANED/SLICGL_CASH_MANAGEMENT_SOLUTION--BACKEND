package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface transferChannelService {
    public ResponseEntity<customAPIResponse<String>> addChannel(addNewChannelDTO newChannel);

    public ResponseEntity<customAPIResponse<List<channelDetailsDTO>>> channelDetails();

    public ResponseEntity<customAPIResponse<String>> removeChannel(String channelId);

    public ResponseEntity<customAPIResponse<channelSearchForRemoveDTO>> searchChannelForRemove(String channelId);

    public ResponseEntity<customAPIResponse<List<setPriorityLevelDTO>>> setPriorityLevel();

    public ResponseEntity<customAPIResponse<String>> changePriorityLevel(String channelId, Integer newLevel);

    public ResponseEntity<customAPIResponse<List<getChannelsForDefineOptionsDTO>>> getChannelsForDefineOptions();

    public ResponseEntity<customAPIResponse<List<getChannelsForDefineOptionsDTO>>> getChannelsForDeleteOptions();

    public ResponseEntity<customAPIResponse<List<transferChanelForTransferHistory>>> getTransferChanel();

    public ResponseEntity<customAPIResponse<List<getTransferChanelForManualTransferDTO>>> getTransferChanelForManualTransfer();

}
