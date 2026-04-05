package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bankAccount;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transferChannel;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transferOption;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.TransferOptionExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.bankAccountRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transferChannelRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transferOptionRepository;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class transferOptionIMPL implements transferOptionService {
    @Autowired
    JdbcTemplate template;
    @Autowired
    transferOptionRepository transferOptionRepo;
    @Autowired
    bankAccountRepo bankAccountRepo;
    @Autowired
    transferChannelRepo transferChannelRepository;
    @Autowired
    HttpRequestUtil httpRequest;
    @Autowired
    UserRepo userRepository;

    @Override
    @Session()
    @RequiresPermission("FUNC-054")
    @LogActivity(methodDescription = "This method will display all available transfer options to define")
    public ResponseEntity<customAPIResponse<List<transferOptionDTO>>> displayAvailableOptions(String accountId, String channelId) {
        // Check whether user provided all required data
        if (accountId == null || accountId.isEmpty() || channelId == null || channelId.isEmpty()) {
            throw new OptionInputDataViolationException("Please provide both account id and channel id");
        } else {
            List<transferOptionDTO> optionList = new ArrayList<>();
            bankAccount fromAccount = bankAccountRepo.findById(accountId).get();
            transferChannel channel = transferChannelRepository.findById(channelId).get();
            String Sql = "SELECT account_id, account_number FROM bank_account WHERE delete_status = 0 AND account_id != ?";
            List<toAccountsForTransferOptionsDTO> accountList = template.query(Sql, new Object[]{accountId}, new displayAvailableTransferOptionsMapper());
            //Check whether any Account List is available;
            if (accountList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No Bank Accounts available to set up Transfer Option",
                                null
                        )
                );
            } else {
                //Loop account list;
                Iterator<toAccountsForTransferOptionsDTO> iterator = accountList.iterator();
                while (iterator.hasNext()) {
                    toAccountsForTransferOptionsDTO toAccount = iterator.next();
                    List<String> availableOption = transferOptionRepo.availableOption(accountId, toAccount.getAccountId(), channelId);
                    if (!availableOption.isEmpty()) {
                        iterator.remove();
                    } else {
                        //No code block to be run here;
                        optionList.add(0,
                                new transferOptionDTO(
                                        accountId,
                                        fromAccount.getAccountNumber(),
                                        toAccount.getAccountId(),
                                        toAccount.getAccountNumber(),
                                        channelId,
                                        channel.getChannelType()
                                )
                        );
                    }
                }
            }
            if (optionList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No options available to set under this criteria",
                                null
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                optionList
                        )
                );
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-054")
    @LogActivity(methodDescription = "This method will save transfer options")
    public ResponseEntity<customAPIResponse<String>> saveTransferOptions(List<saveTransferOptionsDTO> savingOptions) {
        // Check whether options available in the list for save
        if (savingOptions.isEmpty()) {
            throw new EmptyOptionListException("No options found for define");
        } else {
            //Loop each options in savingOption list and save to the transfer option table;
            for (saveTransferOptionsDTO option : savingOptions) {
                //Creation of new Option ID;
                String newOptionId;
                //Get the last option id from transfer option table.
                String lastOptionId = transferOptionRepo.getLastOptionId();
                if (lastOptionId != null) {
                    int numericValue = Integer.parseInt(lastOptionId.substring(5)) + 1;
                    newOptionId = "OPTN" + String.format("-%04d", numericValue);
                    System.out.println(newOptionId);
                } else {
                    newOptionId = "OPTN-0001";
                }
                //Saving to the transfer option table;
                transferOptionRepo.save(
                        new transferOption(
                                newOptionId,
                                1,
                                0,
                                null,
                                bankAccountRepo.findById(option.getFromAccountId()).get(),
                                bankAccountRepo.findById(option.getToAccountId()).get(),
                                transferChannelRepository.findById(option.getChannelId()).get(),
                                LocalDateTime.now(),
                                userRepository.findById(httpRequest.getSessionUser()).get()
                        )
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            "These options are now in active status. If you need to deactivate them, please use the 'Deactivate' option",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-055")
    @LogActivity(methodDescription = "This method will display transfer options to de-activate")
    public ResponseEntity<customAPIResponse<List<deactivateOptionsDTO>>> deactivateOptions() {
        String Sql = "SELECT OPTN.option_id, OPTN.defined_date, FROMACC.account_number, TOACC.account_number, CHNL.channel_type FROM transfer_option OPTN LEFT JOIN bank_account FROMACC ON OPTN.from_account = FROMACC.account_id LEFT JOIN bank_account TOACC ON OPTN.to_account = TOACC.account_id LEFT JOIN transfer_channel CHNL ON OPTN.transfer_channel = CHNL.channel_id WHERE OPTN.is_active = 1 AND OPTN.is_deleted = 0 ORDER BY FROMACC.account_number ASC, CHNL.channel_type ASC";
        List<deactivateOptionsDTO> optionList = template.query(Sql, new deactivateOptionsMapper());
        if (optionList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Options available to De-activate",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            optionList
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-056")
    @LogActivity(methodDescription = "This method will display transfer options to re-activate")
    public ResponseEntity<customAPIResponse<List<reactivateOptionsDTO>>> reactivateOptions() {
        String Sql = "SELECT OPTN.option_id, OPTN.defined_date, FROMACC.account_number, TOACC.account_number, CHNL.channel_type FROM transfer_option OPTN LEFT JOIN bank_account FROMACC ON OPTN.from_account = FROMACC.account_id LEFT JOIN bank_account TOACC ON OPTN.to_account = TOACC.account_id LEFT JOIN transfer_channel CHNL ON OPTN.transfer_channel = CHNL.channel_id WHERE OPTN.is_active = 0 AND OPTN.is_deleted = 0 ORDER BY FROMACC.account_number ASC, CHNL.channel_type ASC";
        List<reactivateOptionsDTO> optionList = template.query(Sql, new reactivateOptionMapper());
        if (optionList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Options available to Re-Activate",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            optionList
                    )
            );
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-055")
    @LogActivity(methodDescription = "This method will de-activate transfer option")
    public ResponseEntity<customAPIResponse<String>> saveDeactivation(String optionId) {
        // Check whether user provided option id
        if (optionId == null || optionId.isEmpty()) {
            throw new OptionInputDataViolationException("Please provide transfer option id");
        } else {
            int affectedRows = transferOptionRepo.saveDeactivation(optionId);
            if (affectedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Option ID: " + optionId + " De-activated successfully",
                                null
                        )
                );
            } else {
                throw new OptionDeactivateFailureException("Couldn't de-activate option. Please contact administrator");
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-056")
    @LogActivity(methodDescription = "This method will re-activate transfer option")
    public ResponseEntity<customAPIResponse<String>> saveActivation(String optionId) {
        // Check whether user provided option id
        if (optionId == null || optionId.isEmpty()) {
            throw new OptionInputDataViolationException("Please provide transfer option id");
        } else {
            int affectedRows = transferOptionRepo.saveActivation(optionId);
            if (affectedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                "Option ID: " + optionId + " Re-activated successfully",
                                null
                        )
                );
            } else {
                throw new OptionReactivateFailureException("Couldn't re-activate option. Please contact administrator");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-058")
    @LogActivity(methodDescription = "This method will display available transfer options")
    public ResponseEntity<customAPIResponse<List<availableTransferOptionsDTO>>> availableOptions() {
        String SqlQuery = "SELECT OPTN.option_id, OPTN.defined_date, USRDEFINED.user_first_name, CASE WHEN OPTN.is_active = 1 THEN 'Active' ELSE 'Inactive' END AS is_active, CASE WHEN OPTN.is_deleted = 0 THEN 'Not-Deleted' ELSE 'Deleted' END AS is_deleted, CASE WHEN OPTN.deleted_by IS NULL THEN 'N/A' ELSE USRDELETE.user_first_name END AS deleted_by, ACCFROM.account_number as from_account, ACCTO.account_number as to_account, CHNL.channel_type as transfer_channel FROM transfer_option OPTN LEFT JOIN bank_account ACCFROM ON OPTN.from_account = ACCFROM.account_id LEFT JOIN bank_account ACCTO ON OPTN.to_account = ACCTO.account_id LEFT JOIN transfer_channel CHNL ON OPTN.transfer_channel = CHNL.channel_id LEFT JOIN user USRDELETE ON OPTN.deleted_by = USRDELETE.user_id LEFT JOIN user USRDEFINED ON OPTN.defined_by = USRDEFINED.user_id";
        List<availableTransferOptionsDTO> availableOptions = template.query(SqlQuery, new availableTransferOptionsMapper());
        if (availableOptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new customAPIResponse<>(
                            false,
                            "No Transfer Options available",
                            null
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            availableOptions
                    )
            );
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-057")
    @LogActivity(methodDescription = "This method will display transfer options for delete")
    public ResponseEntity<customAPIResponse<getOptionForDeleteDTO>> getOptionForDelete(String fromAccount, String toAccount, String transferChannel) {
        // Check whether user provided all required data
        if (fromAccount == null || fromAccount.isEmpty() || toAccount == null || toAccount.isEmpty() || transferChannel == null || transferChannel.isEmpty()) {
            throw new OptionInputDataViolationException("Please provide required data");
        } else {
            //Get option details that matches to the provided From Account, To Account and Chanel ID;
            String SqlQuery = "SELECT OPTN.option_id, OPTN.defined_date, USRDEFINED.user_first_name, OPTN.is_active, OPTN.is_deleted, OPTN.deleted_by, ACCFROM.account_number as from_account, ACCTO.account_number as to_account, CHNL.channel_type as transfer_channel FROM transfer_option OPTN LEFT JOIN bank_account ACCFROM ON OPTN.from_account = ACCFROM.account_id LEFT JOIN bank_account ACCTO ON OPTN.to_account = ACCTO.account_id LEFT JOIN transfer_channel CHNL ON OPTN.transfer_channel = CHNL.channel_id LEFT JOIN user USRDELETE ON OPTN.deleted_by = USRDELETE.user_id LEFT JOIN user USRDEFINED ON OPTN.defined_by = USRDEFINED.user_id WHERE OPTN.from_account = ? AND OPTN.to_account = ? AND OPTN.transfer_channel = ?";
            //Set parameters;
            Object[] params = new Object[]{
                    fromAccount,
                    toAccount,
                    transferChannel
            };
            List<getOptionForDeleteDTO> availableOption = template.query(SqlQuery, new getOptionForDeleteMapper(), params);
            //Check whether any record is available for provided data;
            if (availableOption.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No record available for provided data. Please check",
                                null
                        )
                );
            } else {
                //Check whether the record is already deleted or not;
                if (availableOption.get(0).getIsDeleted() == 1) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(
                            new customAPIResponse<>(
                                    false,
                                    "This Transfer Option is already deleted. No further deletion is required",
                                    null
                            )
                    );
                } else {
                    //Check whether the record is already activated or not;
                    if (availableOption.get(0).getIsActive() == 1) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                new customAPIResponse<>(
                                        false,
                                        "This Transfer Option is already in active status. Please deactivate before delete",
                                        null
                                )
                        );
                    } else {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                new customAPIResponse<>(
                                        true,
                                        null,
                                        availableOption.get(0)
                                )
                        );
                    }
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-057")
    @LogActivity(methodDescription = "This method will delete transfer option")
    public ResponseEntity<customAPIResponse<String>> saveOptionDelete(String optionId) {
        // Check whether user provided option id
        if (optionId == null || optionId.isEmpty()) {
            throw new OptionInputDataViolationException("Please provide option id");
        } else {
            //Check whether any option is available for provided Option ID;
            boolean availableOption = transferOptionRepo.findById(optionId).isEmpty();
            if (availableOption) {
                //If record is not available;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new customAPIResponse<>(
                                false,
                                "No Transfer Option is available for provided Option ID",
                                null
                        )
                );
            } else {
                //If record is available;
                int affectedRows = transferOptionRepo.deleteTransferOption(httpRequest.getSessionUser(), optionId);
                if (affectedRows == 0) {
                    throw new OptionDeletionFailureException("Couldn't delete transfer option. Please contact administrator");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new customAPIResponse<>(
                                    true,
                                    "Transfer Option " + optionId + " deleted successfully",
                                    null
                            )
                    );
                }
            }
        }
    }
}
