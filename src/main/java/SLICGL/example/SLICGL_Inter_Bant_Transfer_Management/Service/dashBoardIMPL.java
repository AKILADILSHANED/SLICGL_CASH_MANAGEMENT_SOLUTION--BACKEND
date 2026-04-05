package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getForecastingDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRequestDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransactionDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.forecastRequestRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transferChannelRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transfersRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.getBalanceDetailsMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.getForecastingDetailsMapper;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.getRequestDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class dashBoardIMPL implements dashBoardService {
    @Autowired
    transfersRepo transfersRepository;

    @Autowired
    transferChannelRepo transferChannelRepository;

    @Autowired
    JdbcTemplate template;

    @Autowired
    forecastRequestRepo forecastRequestRepository;


    @Override
    @Session()
    public ResponseEntity<customAPIResponse<getTransactionDetailsDTO>> getTransactionDetails() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new customAPIResponse<>(
                            true,
                            null,
                            new getTransactionDetailsDTO(
                                    transfersRepository.getTotalTransferCount(LocalDate.now()),
                                    transfersRepository.getTotalApproveTransferCount(LocalDate.now()),
                                    transfersRepository.getTotalPendingTransferCount(LocalDate.now()),
                                    transfersRepository.getTotalRejectedTransferCount(LocalDate.now()),
                                    transfersRepository.getTotalIBTTransferCount(LocalDate.now(), "CHNL-01"),
                                    transfersRepository.getTotalCEFTTransferChanelCount(LocalDate.now(), "CHNL-02"),
                                    transfersRepository.getTotalRTGSTransferCount(LocalDate.now(), "CHNL-03"),
                                    transfersRepository.getTotalChequeTransferCount(LocalDate.now(), "CHNL-04")
                            )
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new customAPIResponse<>(
                            false,
                            "Un-expected error occurred while loading Transfer Details. Please contact the administrator!",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    public ResponseEntity<customAPIResponse<List<getForecastingDetailsDTO>>> getForecastingDetails() {
        try {
            //Fetch relevant all Forecasting details;
            String Sql = "SELECT PMNT.payment_type, ACC.account_number, FRC.request_amount, FRC.required_date, USR.user_first_name FROM forecast_request FRC LEFT JOIN payment PMNT ON FRC.payment = PMNT.payment_id LEFT JOIN bank_account ACC ON FRC.bank_account = ACC.account_id LEFT JOIN user USR ON FRC.request_by = USR.user_id WHERE FRC.delete_status = 0 AND DATE(FRC.required_date) >= ?";
            List<getForecastingDetailsDTO> forecastList = template.query(Sql, new getForecastingDetailsMapper(forecastRequestRepository.getTotalForecastAmount(LocalDate.now())), LocalDate.now());
            if (!forecastList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                forecastList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new customAPIResponse<>(
                                false,
                                "No Forecasted Request available!",
                                null
                        )
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new customAPIResponse<>(
                            false,
                            "No Forecasting Details available or Un-expected error occurred!",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    public ResponseEntity<customAPIResponse<List<getBalanceDetailsDTO>>> getBalanceDetails() {
        try {
            //Fetch relevant all Account Balance details;
            String Sql = "SELECT acc.account_number AS 'Bank Account', bal.balance_amount AS 'Opening Balance', (bal.balance_amount + COALESCE(adj.Adjustment_Total, 0)) AS 'Final Balance' FROM account_balance bal LEFT JOIN bank_account acc ON acc.account_id = bal.account_id LEFT JOIN (SELECT baladj.adjustment_balance, SUM(baladj.adjustment_amount) AS 'Adjustment_Total' FROM account_balance_adjustments baladj LEFT JOIN cross_adjustment crsadj ON crsadj.cross_adjustment_id = baladj.cross_adjustment_id WHERE crsadj.is_reversed = 0 AND DATE(baladj.adjustment_date) = CURRENT_DATE GROUP BY baladj.adjustment_balance) adj ON adj.adjustment_balance = bal.balance_id WHERE DATE(bal.balance_date) = CURRENT_DATE AND bal.delete_status = 0 AND acc.delete_status = 0\n" +
                    "ORDER BY acc.account_number";
            List<getBalanceDetailsDTO> balanceList = template.query(Sql, new getBalanceDetailsMapper());
            if (!balanceList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                balanceList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new customAPIResponse<>(
                                false,
                                "No Account Balances available!",
                                null
                        )
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new customAPIResponse<>(
                            false,
                            "Un-expected error occurred while loading Balance Details. Please contact the administrator!",
                            null
                    )
            );
        }
    }

    @Override
    @Session()
    public ResponseEntity<customAPIResponse<List<getRequestDetailsDTO>>> getFundRequestDetails() {
        try {
            //Fetch relevant all fund request details;
            String Sql = "SELECT pmnt.payment_type, req.request_amount, acc.account_number, req.request_date, req.required_date, usr.user_first_name FROM fund_request req LEFT JOIN payment pmnt ON pmnt.payment_id = req.payment LEFT JOIN bank_account acc ON acc.account_id = req.bank_account LEFT JOIN user usr ON usr.user_id = req.request_by WHERE req.delete_status = 0 AND DATE(req.required_date) = ?";
            List<getRequestDetailsDTO> requestList = template.query(Sql, new getRequestDetailsMapper(), LocalDate.now());
            if (!requestList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new customAPIResponse<>(
                                true,
                                null,
                                requestList
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new customAPIResponse<>(
                                false,
                                "No Fund Request available for current date!",
                                null
                        )
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new customAPIResponse<>(
                            false,
                            "No Fund Request Details available or Un-expected error occurred!",
                            null
                    )
            );
        }
    }
}
