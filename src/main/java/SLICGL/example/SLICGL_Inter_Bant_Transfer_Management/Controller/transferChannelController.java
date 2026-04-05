package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.transferChannelIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
@RequestMapping(value = "/api/v1/channel")
public class transferChannelController {
    @Autowired
    transferChannelIMPL transferChannel;

    @PostMapping(value = "/add-channel")
    public ResponseEntity<customAPIResponse<String>> addChannel(@RequestBody addNewChannelDTO newChannel) {
        return transferChannel.addChannel(newChannel);
    }

    @GetMapping(value = "/get-channelDetails")
    public ResponseEntity<customAPIResponse<List<channelDetailsDTO>>> channelDetails() {
        return transferChannel.channelDetails();
    }

    @GetMapping(value = "/search-removeChannel")
    public ResponseEntity<customAPIResponse<channelSearchForRemoveDTO>> searchChannelForRemove(@RequestParam String channelId) {
        return transferChannel.searchChannelForRemove(channelId);
    }

    @PutMapping(value = "/remove-channel")
    public ResponseEntity<customAPIResponse<String>> removeChannel(@RequestParam String channelId) {
        return transferChannel.removeChannel(channelId);
    }

    @GetMapping(value = "/priority-levels")
    public ResponseEntity<customAPIResponse<List<setPriorityLevelDTO>>> setPriorityLevel() {
        return transferChannel.setPriorityLevel();
    }

    @PutMapping(value = "/level-update")
    public ResponseEntity<customAPIResponse<String>> changePriorityLevel(@RequestParam String channelId, @RequestParam Integer newLevel) {
        return transferChannel.changePriorityLevel(channelId, newLevel);
    }

    @GetMapping(value = "/define-options")
    public ResponseEntity<customAPIResponse<List<getChannelsForDefineOptionsDTO>>> getChannelsForDefineOptions() {
        return transferChannel.getChannelsForDefineOptions();
    }

    @GetMapping(value = "/delete-options")
    public ResponseEntity<customAPIResponse<List<getChannelsForDefineOptionsDTO>>> getChannelsForDeleteOptions() {
        return transferChannel.getChannelsForDeleteOptions();
    }

    @GetMapping(value = "/getChanel")
    public ResponseEntity<customAPIResponse<List<transferChanelForTransferHistory>>> getTransferChanel() {
        return transferChannel.getTransferChanel();
    }

    @GetMapping(value = "/get-chanel-for-manual-transfer")
    public ResponseEntity<customAPIResponse<List<getTransferChanelForManualTransferDTO>>> getTransferChanelForManualTransfer() {
        return transferChannel.getTransferChanelForManualTransfer();
    }
}
