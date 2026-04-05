package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transferChannel;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelDeletionFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelUpdateFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transferChannelRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.channelDetailsMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.getChannelsForDefineOptionsMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.searchChannelForRemoveMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.setPriorityLevelMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class transferChannelIMPL implements transferChannelService {
    @Autowired
    transferChannelRepo transferChannelRepository;
    @Autowired
    HttpRequestUtil httpRequest;
    @Autowired
    UserRepo userRepository;
    @Autowired
    JdbcTemplate template;

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-050")
    @LogActivity(methodDescription = "This method will add a new transfer channel to the system")
    public ResponseEntity<customAPIResponse<String>> addChannel(addNewChannelDTO newChannel) {
        // Check whether user provided all required data
        if (newChannel.getChannelType() == null || newChannel.getChannelType().isEmpty() || newChannel.getShortKey() == null || newChannel.getShortKey().isEmpty() || newChannel.getPriorityLevel() == null) {
            throw new ChannelInputDataViolationException("Please provide all required data");
        } else {
            String newChannelId;
            //Get last Channel ID from the table;
            String lastChannelId = transferChannelRepository.getLastChannelId();
            if (lastChannelId == null) {
                newChannelId = "CHNL-01";
            } else {
                int numericPart = Integer.parseInt(lastChannelId.substring(5, 7)) + 1;
                newChannelId = "CHNL-" + String.format("%02d", numericPart);
            }

            //Save new channel details to the Transfer Channel table;
            transferChannelRepository.save(
                    new transferChannel(
                            newChannelId,
                            newChannel.getChannelType(),
                            newChannel.getShortKey(),
                            newChannel.getPriorityLevel(),
                            LocalDateTime.now(),
                            0,
                            null,
                            userRepository.findById(httpRequest.getSessionUser()).get()
                    )
            );
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            "Transfer Channel registered successfully with Channel ID: " + newChannelId,
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-051")
    @LogActivity(methodDescription = "This method will display active transfer channel details")
    public ResponseEntity<customAPIResponse<List<channelDetailsDTO>>> channelDetails() {
        String Sql = "SELECT CHNL.channel_id, CHNL.channel_type, CHNL.short_key, CHNL.priority_level, CHNL.created_date, CASE WHEN CHNL.deleted_status = 0 THEN 'Active' ELSE 'Deleted' END AS 'deleted_status', CASE WHEN CHNL.delete_by IS NULL THEN 'N/A' ELSE USRDELETED.user_first_name END AS 'delete_by', USRDEFINED.user_first_name FROM transfer_channel CHNL LEFT JOIN user USRDEFINED ON CHNL.defined_by = USRDEFINED.user_id LEFT JOIN user USRDELETED ON CHNL.delete_by = USRDELETED.user_id ORDER BY CHNL.priority_level ASC";
        List<channelDetailsDTO> channelDetails = template.query(Sql, new channelDetailsMapper());
        if (channelDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Channels found",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            channelDetails
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-052")
    @LogActivity(methodDescription = "This method will delete channel details")
    public ResponseEntity<customAPIResponse<String>> removeChannel(String channelId) {
        //Check whether user provided channel id
        if (channelId == null || channelId.isEmpty()) {
            throw new ChannelInputDataViolationException("Please provide channel id");
        } else {
            //Check whether any record is available for provided channel id;
            Optional<transferChannel> channel = transferChannelRepository.findById(channelId);
            if (channel.isEmpty()) {
                throw new ChannelNotFoundException("No Transfer Channel is identified for provided Channel ID");
            } else {
                int affectedRow = transferChannelRepository.removeChannel(httpRequest.getSessionUser(), channelId);
                if (affectedRow > 0) {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    true,
                                    "Channel ID: " + channelId + " deleted successfully",
                                    null
                            )
                    );
                } else {
                    throw new ChannelDeletionFailureException("Couldn't delete channel. Please contact administrator");
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-052")
    @LogActivity(methodDescription = "This method will display channel details for delete")
    public ResponseEntity<customAPIResponse<channelSearchForRemoveDTO>> searchChannelForRemove(String channelId) {
        //Check whether user provided channel id
        if (channelId == null || channelId.isEmpty()) {
            throw new ChannelInputDataViolationException("Please provide channel id");
        } else {
            //Check whether any record is available for provided channel id;
            Optional<transferChannel> channel = transferChannelRepository.findById(channelId);
            if (channel.isEmpty()) {
                throw new ChannelNotFoundException("No Transfer Channel is identified for provided Channel ID");
            } else {
                String Sql = "SELECT CHNL.channel_id, CHNL.channel_type, CHNL.short_key, CHNL.priority_level, CHNL.deleted_status, CHNL.created_date, USRDEFINED.user_first_name FROM transfer_channel CHNL LEFT JOIN user USRDEFINED ON CHNL.defined_by = USRDEFINED.user_id WHERE CHNL.channel_id = ?";
                List<channelSearchForRemoveDTO> searchedChannel = template.query(Sql, new Object[]{channelId}, new searchChannelForRemoveMapper());
                //Check whether the channel is already deleted or not
                if (searchedChannel.get(0).getDeleteStatus() == 1) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(
                            new customAPIResponse<>(
                                    false,
                                    "This channel is already deleted",
                                    null
                            )
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    true,
                                    null,
                                    searchedChannel.get(0)
                            )
                    );
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-053")
    @LogActivity(methodDescription = "This method will display channel details for setting up priority levels")
    public ResponseEntity<customAPIResponse<List<setPriorityLevelDTO>>> setPriorityLevel() {
        String Sql = "SELECT channel_id, channel_type, short_key, priority_level FROM transfer_channel WHERE deleted_status = 0 ORDER BY priority_level ASC";
        List<setPriorityLevelDTO> priorityLevelList = template.query(Sql, new setPriorityLevelMapper());
        if (priorityLevelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Channels available",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            priorityLevelList
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-053")
    @LogActivity(methodDescription = "This method will update priority level of selected channel")
    public ResponseEntity<customAPIResponse<String>> changePriorityLevel(String channelId, Integer newLevel) {
        //Check whether user provided all required data
        if (channelId == null || channelId.isEmpty() || newLevel == null) {
            throw new ChannelInputDataViolationException("Please provide both channel id and updated level");
        } else {
            int affectedRows = transferChannelRepository.updateNewLevel(newLevel, channelId);
            if (affectedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Successfully set level for Channel ID: " + channelId,
                                null
                        )
                );
            } else {
                throw new ChannelUpdateFailureException("Couldn't update channel. Please contact administrator");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-054")
    @LogActivity(methodDescription = "This method will load transfer channels for define transfer options")
    public ResponseEntity<customAPIResponse<List<getChannelsForDefineOptionsDTO>>> getChannelsForDefineOptions() {
        String Sql = "SELECT channel_id, channel_type, short_key FROM transfer_channel WHERE deleted_status = 0;";
        List<getChannelsForDefineOptionsDTO> channelList = template.query(Sql, new getChannelsForDefineOptionsMapper());
        if (channelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Channel Details available",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            channelList
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-057")
    @LogActivity(methodDescription = "This method will load transfer channels for delete transfer options")
    public ResponseEntity<customAPIResponse<List<getChannelsForDefineOptionsDTO>>> getChannelsForDeleteOptions() {
        String Sql = "SELECT channel_id, channel_type, short_key FROM transfer_channel WHERE deleted_status = 0;";
        List<getChannelsForDefineOptionsDTO> channelList = template.query(Sql, new getChannelsForDefineOptionsMapper());
        if (channelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Channel Details available",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            channelList
                    )
            );
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will fetch active transfer chanel list")
    public ResponseEntity<customAPIResponse<List<transferChanelForTransferHistory>>> getTransferChanel() {
        List<transferChanelForTransferHistory> chanelList = transferChannelRepository.chanelList();
        if (!chanelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<List<transferChanelForTransferHistory>>(
                            true,
                            null,
                            chanelList
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Channel details to fetch",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-024")
    @LogActivity(methodDescription = "This method will fetch available transfer channels for manual transfers")
    public ResponseEntity<customAPIResponse<List<getTransferChanelForManualTransferDTO>>> getTransferChanelForManualTransfer() {
        List<getTransferChanelForManualTransferDTO> channelList = transferChannelRepository.channelsListForManualTransfer();
        if (channelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Channels found",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            channelList
                    )
            );
        }
    }
}
