package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.forecastRequest;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.fundRequest;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.FundRequestExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class fundRequestIMPL implements fundRequestService {
    @Autowired
    fundRequestRepo fundRequestRepository;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepo userRepository;

    @Autowired
    paymentRepo paymentRepository;

    @Autowired
    bankAccountRepo bankAccountRepository;

    @Autowired
    forecastRequestRepo forecastRequestRepository;

    @Autowired
    JdbcTemplate template;

    @Autowired
    private HttpRequestUtil httpRequestUtil;

    private static final Logger logger = LoggerFactory.getLogger(fundRequestIMPL.class);

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-018")
    @LogActivity(methodDescription = "This method will initiate a new fund request")
    public ResponseEntity<customAPIResponse<String>> newFundRequest(newFundRequestDTO fundRequest) {
        //Check whether user has been provided values for all required fields
        if (fundRequest.getAccountId() == null || fundRequest.getAccountId().isEmpty() || fundRequest.getPaymentId() == null || fundRequest.getPaymentId().isEmpty() || fundRequest.getRequestAmount() == null || fundRequest.getRequestType() == null || fundRequest.getRequiredDate() == null) {
            throw new RequestInputDataViolationException("Please provide all required data for successful Fund Request");
        } else {
            //Check whether the funds required date is a previous date or not;
            if (fundRequest.getRequiredDate().isBefore(LocalDate.now())) {
                throw new RequestInputDataViolationException("You are not authorized to request funds for a previous date");
            } else {
                //Check whether the request is an actual request or forecasted request;
                if (fundRequest.getRequestType() == 0) {
                /*Check whether a Fund Request is already being initiated for same payment in same date.
                If it already has request, user not authorized to create a new request for same payment type; */
                    String availableRequestId = fundRequestRepository.availableRequest(fundRequest.getPaymentId(), fundRequest.getRequiredDate());
                    if (availableRequestId != null) {
                        customAPIResponse<String> response = new customAPIResponse<>(
                                false,
                                "Funds already being requested for same selected Payment Type and selected date. If you need additional funds, please use update option",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    } else {
                        //Check whether an account balance is already entered for fund request bank account for actual forecasting;
                        if (fundRequest.getRequiredDate().equals(LocalDate.now())) {
                            int balanceAvailability = fundRequestRepository.accountBalanceForFundRequest(fundRequest.getAccountId(), LocalDate.now());
                            if (balanceAvailability == 0) {
                                customAPIResponse<String> response = new customAPIResponse<>(
                                        false,
                                        "Account balance not entered for selected bank account. Please enter the account balance before request the funds",
                                        null
                                );
                                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                            } else {
                                //Actual fund request;
                                //Create new Request id for actual fund requests;
                                String newRequestId;
                                String currentYear = String.valueOf(LocalDate.now().getYear());
                                String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

                                //Get last request id from actual fund request table;
                                String lastRequestId = fundRequestRepository.getLastRequestId();
                                if (lastRequestId == null) {
                                    newRequestId = "FRQ-" + currentYear + currentMonth + "-001";
                                } else {
                                    if ((currentYear + currentMonth).equals(lastRequestId.substring(4, 10))) {
                                        int newNumericRequestId = Integer.parseInt(lastRequestId.substring(11, 14)) + 1;
                                        newRequestId = "FRQ-" + currentYear + currentMonth + String.format("-%03d", newNumericRequestId);
                                    } else {
                                        newRequestId = "FRQ-" + currentYear + currentMonth + "-001";
                                    }
                                }
                                fundRequest requestObj = new fundRequest(
                                        newRequestId,
                                        fundRequest.getRequestAmount(),
                                        LocalDateTime.now(),
                                        fundRequest.getRequiredDate(),
                                        0,
                                        null,
                                        0,
                                        null,
                                        userRepository.findById(httpRequestUtil.getSessionUser()).get(),
                                        paymentRepository.findById(fundRequest.getPaymentId()).get(),
                                        bankAccountRepository.findById(fundRequest.getAccountId()).get()
                                );
                                fundRequestRepository.save(requestObj);
                                customAPIResponse<String> response = new customAPIResponse<>(
                                        true,
                                        "Funds have been requested successfully with Request ID: " + newRequestId,
                                        null
                                );
                                return ResponseEntity.status(HttpStatus.OK).body(response);
                            }
                        } else {
                            //Actual fund request;
                            //Create new Request id for actual fund requests;
                            String newRequestId;
                            String currentYear = String.valueOf(LocalDate.now().getYear());
                            String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

                            //Get last request id from actual fund request table;
                            String lastRequestId = fundRequestRepository.getLastRequestId();
                            if (lastRequestId == null) {
                                newRequestId = "FRQ-" + currentYear + currentMonth + "-001";
                            } else {
                                if ((currentYear + currentMonth).equals(lastRequestId.substring(4, 10))) {
                                    int newNumericRequestId = Integer.parseInt(lastRequestId.substring(11, 14)) + 1;
                                    newRequestId = "FRQ-" + currentYear + currentMonth + String.format("-%03d", newNumericRequestId);
                                } else {
                                    newRequestId = "FRQ-" + currentYear + currentMonth + "-001";
                                }
                            }
                            fundRequest requestObj = new fundRequest(
                                    newRequestId,
                                    fundRequest.getRequestAmount(),
                                    LocalDateTime.now(),
                                    fundRequest.getRequiredDate(),
                                    0,
                                    null,
                                    0,
                                    null,
                                    userRepository.findById(httpRequestUtil.getSessionUser()).get(),
                                    paymentRepository.findById(fundRequest.getPaymentId()).get(),
                                    bankAccountRepository.findById(fundRequest.getAccountId()).get()
                            );
                            fundRequestRepository.save(requestObj);
                            customAPIResponse<String> response = new customAPIResponse<>(
                                    true,
                                    "Funds have been requested successfully with Request ID: " + newRequestId,
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.OK).body(response);
                        }
                    }
                } else {
                /*Check whether a Forecasted Request is already being initiated for same payment in same date.
                If it already has request, user not authorized to create a new forecasted request for same payment type; */
                    String availableRequestId = forecastRequestRepository.availableRequest(fundRequest.getPaymentId(), fundRequest.getRequiredDate());
                    if (availableRequestId != null) {
                        customAPIResponse<String> response = new customAPIResponse<>(
                                false,
                                "Funds already being forecasted for same selected Payment Type and selected date. If you need additional funds, please use update option",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    } else {
                        //Forecasted fund request;
                        //Create new Request id for forecasted fund requests;
                        String newRequestId;
                        String currentYear = String.valueOf(LocalDate.now().getYear());
                        String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

                        //Get last request id from forecasted fund request table;
                        String lastRequestId = forecastRequestRepository.getLastRequestId();
                        if (lastRequestId == null) {
                            newRequestId = "FRC-" + currentYear + currentMonth + "-01";
                        } else {
                            if ((currentYear + currentMonth).equals(lastRequestId.substring(4, 10))) {
                                int newNumericRequestId = Integer.parseInt(lastRequestId.substring(11, 13)) + 1;
                                newRequestId = "FRC-" + currentYear + currentMonth + String.format("-%02d", newNumericRequestId);
                            } else {
                                newRequestId = "FRC-" + currentYear + currentMonth + "-01";
                            }
                        }
                        forecastRequest requestObj = new forecastRequest(
                                newRequestId,
                                fundRequest.getRequestAmount(),
                                LocalDateTime.now(),
                                fundRequest.getRequiredDate(),
                                0,
                                null,
                                userRepository.findById(httpRequestUtil.getSessionUser()).get(),
                                paymentRepository.findById(fundRequest.getPaymentId()).get(),
                                bankAccountRepository.findById(fundRequest.getAccountId()).get()
                        );
                        forecastRequestRepository.save(requestObj);
                        customAPIResponse<String> response = new customAPIResponse<>(
                                true,
                                "Funds have been forecasted successfully with Forecast Request ID: " + newRequestId,
                                null
                        );
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    }
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-019")
    @LogActivity(methodDescription = "This method will display all fund request details")
    public ResponseEntity<customAPIResponse<List<getFundRequestDetailsDTO>>> requestDetails(LocalDate requiredDate, String requestType) {
        //Check whether user has been provided the values for Required Date and Request Type
        if (requiredDate == null || requestType == null || requestType.isEmpty()) {
            throw new RequestInputDataViolationException("Please provide all required details for successful searching.");
        } else {
            //Check whether the user selected request type is Actual fund request or Forecasted fund request;
            if (requestType.equals("0")) { //Actual Fund Request
                String Sql = "SELECT REQ.request_id, BNK.account_number, PMNT.payment_type, REQ.request_amount, REQ.request_date, REQ.required_date, CASE WHEN REQ.approve_status = 0 THEN 'Not-Approved' WHEN REQ.approve_status = 1 THEN 'Approved' END AS 'approve_status', CASE WHEN REQ.approved_by IS NULL THEN 'N/A' ELSE USRAPR.user_first_name END AS 'approved_by', CASE WHEN REQ.delete_status = 0 THEN 'Active' WHEN REQ.delete_status = 1 THEN 'Deleted' END AS 'delete_status', CASE WHEN REQ.deleted_by IS NULL THEN 'N/A' ELSE USRDEL.user_first_name END AS 'deleted_by', USR.user_first_name FROM fund_request REQ LEFT JOIN bank_account BNK ON REQ.bank_account = BNK.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id LEFT JOIN user USRAPR ON REQ.approved_by = USRAPR.user_id LEFT JOIN user USRDEL ON REQ.deleted_by = USRDEL.user_id LEFT JOIN user USR ON REQ.request_by = USR.user_id WHERE REQ.required_date = ?";
                List<getFundRequestDetailsDTO> requestDetails = template.query(Sql, new Object[]{requiredDate}, new fundRequestDetailsMapper());
                //Check whether any Fund request data available for provided date;
                if (requestDetails.isEmpty()) {
                    customAPIResponse<List<getFundRequestDetailsDTO>> response = new customAPIResponse<>(
                            false,
                            "No Fund Request data found for provided date.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                } else {
                    customAPIResponse<List<getFundRequestDetailsDTO>> response = new customAPIResponse<>(
                            true,
                            null,
                            requestDetails
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            } else {
                String Sql = "SELECT REQ.request_id, BNK.account_number, PMNT.payment_type, REQ.request_amount, REQ.request_date, REQ.required_date, CASE WHEN REQ.delete_status = 0 THEN 'Active' WHEN REQ.delete_status = 1 THEN 'Deleted' END AS 'delete_status', CASE WHEN REQ.deleted_by IS NULL THEN 'N/A' ELSE USRDEL.user_first_name END AS 'deleted_by', USR.user_first_name FROM forecast_request REQ LEFT JOIN bank_account BNK ON REQ.bank_account = BNK.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id LEFT JOIN user USRDEL ON REQ.deleted_by = USRDEL.user_id LEFT JOIN user USR ON REQ.request_by = USR.user_id WHERE REQ.required_date = ?";
                List<getFundRequestDetailsDTO> requestDetails = template.query(Sql, new Object[]{requiredDate}, new forecastedRequestDetailsMapper());
                //Check whether any Fund request data available for provided date;
                if (requestDetails.isEmpty()) {
                    customAPIResponse<List<getFundRequestDetailsDTO>> response = new customAPIResponse<>(
                            false,
                            "No Forecasted Fund Request data found for provided date'",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                } else {
                    customAPIResponse<List<getFundRequestDetailsDTO>> response = new customAPIResponse<>(
                            true,
                            null,
                            requestDetails
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-020")
    @LogActivity(methodDescription = "This method will display fund request details for update")
    public ResponseEntity<customAPIResponse<searchRequestForUpdateDTO>> searchRequestForUpdate(String requestId, String requestType) {
        //Check whether the user has been provided Request ID and Request Type
        if (requestId == null || requestId.isEmpty() || requestType == null || requestType.isEmpty()) {
            throw new RequestInputDataViolationException("Please provide all required data for successful searching.");
        } else {
            //Get the list of available Bank Accounts;
            List<getBankAccountListDTO> accountList = bankAccountRepository.accountList();
            //Get the list of available Payment Types;
            List<getPaymentListDTO> paymentList = paymentRepository.paymentList();

            //Check whether the user selected request type is actual or forecasted fund request;
            if (requestType.equals("0")) {
                //Actual Fund Request;
                String Sql = "SELECT REQ.request_id, BNK.account_id, PMNT.payment_id, REQ.request_amount, REQ.request_date, REQ.required_date, REQ.approve_status, REQ.delete_status FROM fund_request REQ LEFT JOIN bank_account BNK ON REQ.bank_account = BNK.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id WHERE REQ.request_id = ?";
                List<searchRequestForUpdateDTO> requestList = template.query(Sql, new Object[]{requestId}, new searchRequestForUpdateMapper());
                //Check whether any Fund Request record is available for provided request id;
                if (requestList.isEmpty()) {
                    customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                            false,
                            "No Fund Request found for provided Request ID.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                } else {
                    //Check whether the record is related to a previous day;
                    if (requestList.get(0).getRequiredDate().isBefore(LocalDate.now())) {
                        customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                false,
                                "You are not authorized to update a previous day Fund Request record.",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    } else {
                        //Check whether the record is already deleted or not;
                        if (requestList.get(0).getDeleteStatus().equals("1")) {
                            customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                    false,
                                    "This Fund Request is already deleted.",
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                        } else {
                            //Check whether the fund request record is already approved or not;
                            if (requestList.get(0).getApproveStatus().equals("1")) {
                                customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                        false,
                                        "This Fund Request is already approved. Please reverse the approval before update.",
                                        null
                                );
                                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                            } else {
                                //Set bank account list and payment list to searchRequestForUpdateDTO class;
                                requestList.get(0).setAccountList(accountList);
                                requestList.get(0).setPaymentList(paymentList);

                                customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                        true,
                                        null,
                                        requestList.get(0)
                                );
                                return ResponseEntity.status(HttpStatus.OK).body(response);
                            }
                        }
                    }
                }
            } else {
                //Forecasted Fund Request;
                String Sql = "SELECT REQ.request_id, BNK.account_id, PMNT.payment_id, REQ.request_amount, REQ.request_date, REQ.required_date, REQ.delete_status FROM forecast_request REQ LEFT JOIN bank_account BNK ON REQ.bank_account = BNK.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id WHERE REQ.request_id = ?";
                List<searchRequestForUpdateDTO> requestList = template.query(Sql, new Object[]{requestId}, new searchForecastRequestForUpdateMapper());
                //Check whether any Forecasted Request record is available for provided request id;
                if (requestList.isEmpty()) {
                    customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                            false,
                            "No Forecasted Request found for provided Request ID.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                } else {
                    //Check whether the record is related to a previous day;
                    if (requestList.get(0).getRequiredDate().isBefore(LocalDate.now())) {
                        customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                false,
                                "You are not authorized to update a previous day Forecast Request record!",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    } else {
                        //Check whether the record is already deleted or not;
                        if (requestList.get(0).getDeleteStatus().equals("1")) {
                            customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                    false,
                                    "This Forecast Request is already deleted!",
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                        } else {
                            //Set bank account list and payment list to searchRequestForUpdateDTO class;
                            requestList.get(0).setAccountList(accountList);
                            requestList.get(0).setPaymentList(paymentList);

                            customAPIResponse<searchRequestForUpdateDTO> response = new customAPIResponse<>(
                                    true,
                                    null,
                                    requestList.get(0)
                            );
                            return ResponseEntity.status(HttpStatus.OK).body(response);
                        }
                    }
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-020")
    @LogActivity(methodDescription = "This method will update fund request data")
    public ResponseEntity<customAPIResponse<String>> saveUpdateFundRequest(updateFundRequestDTO updatedRequest) {
        // Create a formatter for consistent number formatting
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

        //Check whether the user has been provided all required values for update
        if (updatedRequest.getRequestId() == null || updatedRequest.getAccountId() == null ||
                updatedRequest.getRequestAmount() == null || updatedRequest.getPaymentType() == null ||
                updatedRequest.getAdjustmentType() == null || updatedRequest.getAdjustmentAmount() == null ||
                updatedRequest.getRequestId().isEmpty() || updatedRequest.getAccountId().isEmpty() ||
                updatedRequest.getPaymentType().isEmpty() || updatedRequest.getAdjustmentType().isEmpty()) {

            throw new RequestInputDataViolationException("Please provide all required values for successful Fund Request update.");
        } else {
            /* Check whether the user is trying to update the Payment Type, which has been already defined
             for same fund required date */
            Integer result = fundRequestRepository.existsByPaymentAndRequestDate(updatedRequest.getPaymentType(), updatedRequest.getRequestId(), updatedRequest.getRequestId());
            if (result != null && result == 1) {
                throw new RequestUpdateFailureException("Funds already being requested or forecasted for same selected Payment Type and selected date. If you need additional funds, please use update option.");
            } else {
                //Check whether the user selected adjustment type is plus or minus;
                if (updatedRequest.getAdjustmentType().equals("+")) {
                    //Set new Request Amount and Outstanding Amount;
                    BigDecimal updatedRequestAmount = updatedRequest.getRequestAmount().add(updatedRequest.getAdjustmentAmount());

                    int affectedFundRequestRows = fundRequestRepository.updateFundRequest(
                            updatedRequest.getAccountId(), updatedRequestAmount,
                            updatedRequest.getPaymentType(), updatedRequest.getRequestId());

                    if (affectedFundRequestRows > 0) {
                        customAPIResponse<String> response = new customAPIResponse<>(
                                true,
                                "Fund Request " + updatedRequest.getRequestId() + " updated successfully.",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    } else {
                        int affectedForecastedRows = forecastRequestRepository.updateForecastRequest(
                                updatedRequest.getAccountId(), updatedRequestAmount,
                                updatedRequest.getPaymentType(), updatedRequest.getRequestId());

                        if (affectedForecastedRows > 0) {
                            customAPIResponse<String> response = new customAPIResponse<>(
                                    true,
                                    "Forecasted Request " + updatedRequest.getRequestId() + " updated successfully!",
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.OK).body(response);
                        } else {
                            throw new RequestUpdateFailureException("Unable to update request. No affected rows found.");
                        }
                    }
                } else {
                    BigDecimal updatedRequestAmount = updatedRequest.getRequestAmount().add(updatedRequest.getAdjustmentAmount());

                    if (updatedRequest.getRequestAmount().compareTo(updatedRequest.getAdjustmentAmount()) < 0) {
                        throw new RequestUpdateFailureException("Adjustment amount cannot be higher than fund request amount.");
                    } else {
                        int affectedFundRequestRows = fundRequestRepository.updateFundRequest(
                                updatedRequest.getAccountId(), updatedRequestAmount,
                                updatedRequest.getPaymentType(), updatedRequest.getRequestId());

                        if (affectedFundRequestRows > 0) {
                            customAPIResponse<String> response = new customAPIResponse<>(
                                    true,
                                    "Fund Request " + updatedRequest.getRequestId() + " updated successfully!",
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.OK).body(response);
                        } else {
                            int affectedForecastedRows = forecastRequestRepository.updateForecastRequest(
                                    updatedRequest.getAccountId(), updatedRequestAmount,
                                    updatedRequest.getPaymentType(), updatedRequest.getRequestId());

                            if (affectedForecastedRows > 0) {
                                customAPIResponse<String> response = new customAPIResponse<>(
                                        true,
                                        "Forecasted Request " + updatedRequest.getRequestId() + " updated successfully!",
                                        null
                                );
                                return ResponseEntity.status(HttpStatus.OK).body(response);
                            } else {
                                throw new RequestUpdateFailureException("Unable to update request. No affected rows found.");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-021")
    @LogActivity(methodDescription = "This method will display fund request data for delete")
    public ResponseEntity<customAPIResponse<fundRequestDeleteDTO>> deleteFundRequest(String requestId, Integer requestType) {
        // Check whether the user provided the values for both Request ID and Request Type
        if (requestId == null || requestId.isEmpty() || requestType == null) {
            throw new RequestInputDataViolationException("Please provide all required data for successful searching.");
        } else {
            //Check whether user selected fund request type is Actual or Forecasted;
            if (requestType == 0) {
                //Actual Request;
                Optional<fundRequest> actualRequestObj = fundRequestRepository.findById(requestId);
                if (actualRequestObj.isPresent()) {
                    //Check whether the record is a previous day record;
                    if (!actualRequestObj.get().getRequiredDate().isBefore(LocalDate.now())) {
                        //Check whether the request is already deleted or not;
                        if (actualRequestObj.get().getDeleteStatus() == 0) {
                            //Check whether this record is already approved or not;
                            if (actualRequestObj.get().getApproveStatus() == 0) {
                                String Sql = "SELECT REQ.request_id, ACC.account_number, PMNT.payment_type, REQ.request_amount, REQ.request_date, REQ.required_date FROM fund_request REQ LEFT JOIN bank_account ACC ON REQ.bank_account = ACC.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id WHERE REQ.request_id = ?";
                                List<fundRequestDeleteDTO> requestList = template.query(Sql, new Object[]{requestId}, new fundRequestDeleteMapper());
                                //Set Fund Request Type;
                                requestList.get(0).setRequestType(String.valueOf(requestType));
                                customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                        true,
                                        null,
                                        requestList.get(0)
                                );
                                return ResponseEntity.status(HttpStatus.OK).body(response);
                            } else {
                                customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                        false,
                                        "This Fund Request record is already approved. Please reverse the approval before you delete.",
                                        null
                                );
                                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                            }
                        } else {
                            customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                    false,
                                    "This Fund Request record is already deleted. No further deletion is required.",
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                        }
                    } else {
                        customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                false,
                                "You are not authorized to delete a previous day Fund Request record.",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    }
                } else {
                    customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                            false,
                            "No Fund Request record found for provided Fund Request ID.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            } else {
                //Forecasted Request;
                Optional<forecastRequest> forecastRequestObj = forecastRequestRepository.findById(requestId);
                if (forecastRequestObj.isPresent()) {
                    //Check whether the record is a previous day record;
                    if (!forecastRequestObj.get().getRequiredDate().isBefore(LocalDate.now())) {
                        //Check whether the request is already deleted or not;
                        if (forecastRequestObj.get().getDeleteStatus() == 0) {
                            String Sql = "SELECT REQ.request_id, ACC.account_number, PMNT.payment_type, REQ.request_amount, REQ.request_date, REQ.required_date FROM forecast_request REQ LEFT JOIN bank_account ACC ON REQ.bank_account = ACC.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id WHERE REQ.request_id = ?";
                            fundRequestDeleteDTO requestList = template.query(Sql, new Object[]{requestId}, new fundRequestDeleteMapper()).get(0);
                            //Set Fund Request Type;
                            requestList.setRequestType(String.valueOf(requestType));
                            customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                    true,
                                    null,
                                    requestList
                            );
                            return ResponseEntity.status(HttpStatus.OK).body(response);
                        } else {
                            customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                    false,
                                    "This Forecasted Request record is already deleted. No further deletion is required.",
                                    null
                            );
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                        }
                    } else {
                        customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                                false,
                                "You are not authorized to delete a previous day Forecasted Request record.",
                                null
                        );
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    }
                } else {
                    customAPIResponse<fundRequestDeleteDTO> response = new customAPIResponse<>(
                            false,
                            "No Forecasted Request record found for provided Request ID.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-021")
    @LogActivity(methodDescription = "This method will delete fund request data")
    public ResponseEntity<customAPIResponse<String>> saveDeleteFundRequest(String requestId, Integer requestType) {
        //Check whether user provided the values for all required fields.
        if (requestId == null || requestId.isEmpty() || requestType == null) {
            throw new RequestInputDataViolationException("Please provide all required details for successful deletion.");
        } else {
            //Check whether the delete fund request is actual or forecasted;
            if (requestType == 0) {
                int affectedRow = fundRequestRepository.deleteFundRequest(httpRequestUtil.getSessionUser(), requestId);
                if (affectedRow > 0) {
                    customAPIResponse<String> response = new customAPIResponse<>(
                            true,
                            "Fund Request " + requestId + " deleted successfully.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                } else {
                    throw new RequestDeleteFailureException("Fund Request record deletion failed. No affected rows found.");
                }
            } else {
                int affectedRow = forecastRequestRepository.deleteFundRequest(httpRequestUtil.getSessionUser(), requestId);
                if (affectedRow > 0) {
                    customAPIResponse<String> response = new customAPIResponse<>(
                            true,
                            "Forecast Request " + requestId + " deleted successfully.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                } else {
                    throw new RequestDeleteFailureException("Forecasted Request record deletion failed. No affected rows found.");
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-022")
    @LogActivity(methodDescription = "This method will display fund request list for approval")
    public ResponseEntity<customAPIResponse<List<getFundRequestForApproveDTO>>> getFundRequestForApprove() {
        String Sql = "SELECT REQ.request_id, BNK.account_number, PMNT.payment_type, USR.user_first_name, REQ.request_amount, REQ.request_date, REQ.required_date FROM fund_request REQ LEFT JOIN bank_account BNK ON REQ.bank_account = BNK.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id LEFT JOIN user USR ON REQ.request_by = USR.user_id WHERE REQ.required_date = ? AND REQ.delete_status = 0 AND REQ.approve_status = 0";
        List<getFundRequestForApproveDTO> requestList = template.query(Sql, new Object[]{LocalDate.now()}, new getFundRequestForApproveMapper());
        if (requestList.isEmpty()) {
            customAPIResponse<List<getFundRequestForApproveDTO>> response = new customAPIResponse<>(
                    false,
                    "No pending Fund Requests found for approve.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            customAPIResponse<List<getFundRequestForApproveDTO>> response = new customAPIResponse<>(
                    true,
                    null,
                    requestList
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-022")
    @LogActivity(methodDescription = "This method will approve fund requests")
    public ResponseEntity<customAPIResponse<String>> approveRequest(String requestId) {
        // Check whether user provided Requested ID
        if (requestId == null || requestId.isEmpty()) {
            throw new RequestInputDataViolationException("Please provide Request ID for successful Fund Request approval.");
        } else {
            int affectedRow = fundRequestRepository.approveRequest(httpRequestUtil.getSessionUser(), requestId);
            if (affectedRow > 0) {
                customAPIResponse<String> response = new customAPIResponse<>(
                        true,
                        "Request ID: " + requestId + " approved successfully.",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                throw new RequestApprovalFailureException("Fund Request approval failed. No affected rows found.");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-023")
    @LogActivity(methodDescription = "This method will display fund request list for reversal of approval")
    public ResponseEntity<customAPIResponse<List<getFundRequestForReverseDTO>>> getFundRequestForReverse() {
        String Sql = "SELECT REQ.request_id, BNK.account_number, PMNT.payment_type, USR.user_first_name, REQ.request_amount, REQ.request_date, REQ.required_date FROM fund_request REQ LEFT JOIN bank_account BNK ON REQ.bank_account = BNK.account_id LEFT JOIN payment PMNT ON REQ.payment = PMNT.payment_id LEFT JOIN user USR ON REQ.request_by = USR.user_id WHERE REQ.required_date = ? AND REQ.delete_status = 0 AND REQ.approve_status = 1";
        List<getFundRequestForReverseDTO> requestList = template.query(Sql, new Object[]{LocalDate.now()}, new getFundRequestForReverseMapper());
        if (requestList.isEmpty()) {
            customAPIResponse<List<getFundRequestForReverseDTO>> response = new customAPIResponse<>(
                    false,
                    "No pending Fund Requests found for reversal.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            customAPIResponse<List<getFundRequestForReverseDTO>> response = new customAPIResponse<>(
                    true,
                    null,
                    requestList
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-023")
    @LogActivity(methodDescription = "This method will reverse fund request approvals")
    public ResponseEntity<customAPIResponse<String>> reverseApproval(String requestId) {
        // Check whether user provided Requested ID
        if (requestId == null || requestId.isEmpty()) {
            throw new RequestInputDataViolationException("Please provide Request ID for successful Fund Request approval.");
        } else {
            int affectedRow = fundRequestRepository.reverseApproval(requestId);
            if (affectedRow > 0) {
                customAPIResponse<String> response = new customAPIResponse<>(
                        true,
                        "Request ID: " + requestId + " reversed successfully!",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                throw new RequestReversalFailureException("Fund Request reversal failed. No affected rows found.");
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-049")
    @LogActivity(methodDescription = "This method will fetch fund request details for cash flow printing")
    public ResponseEntity<customAPIResponse<List<getRequestBalancesDTO>>> getRequestBalances(LocalDate requestDate) {
        // Check whether user provided fund request date
        if (requestDate == null) {
            throw new RequestInputDataViolationException("Please provide fund request date");
        } else {
            String Sql = "SELECT req.request_id, pmnt.payment_type, req.request_amount FROM fund_request req LEFT JOIN payment pmnt ON req.payment = pmnt.payment_id WHERE req.approve_status = 1 AND req.delete_status = 0 AND DATE(req.request_date) = ?";
            List<getRequestBalancesDTO> requestList = template.query(Sql, new getRequestBalancesMapper(), requestDate);
            return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                    true,
                    null,
                    requestList
            ));
        }
    }
}
