package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getPaymentsForFundRequestDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentRegisterDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentSearchDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.payment;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PaymentExceptions.PaymentInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PaymentExceptions.PaymentNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.paymentRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.paymentSearchMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class paymentServiceIMPL implements paymentService {
    @Autowired
    paymentRepo paymentRepository;

    @Autowired
    UserRepo userRepository;

    @Autowired
    JdbcTemplate template;

    @Autowired
    HttpRequestUtil httpRequest;

    private static final Logger logger = LoggerFactory.getLogger(paymentServiceIMPL.class);

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-014")
    @LogActivity(methodDescription = "This method will register a new payment type")
    public ResponseEntity<customAPIResponse<paymentRegisterDTO>> paymentRegister(String paymentType) {
        //Check whether user provided a value for Payment Type
        if (paymentType == null || paymentType.isEmpty()) {
            throw new PaymentInputDataViolationException("Please provide a Payment Type.");
        } else {
            String newPaymentId;
            String currentYear = String.valueOf(LocalDate.now().getYear());

            //Creating new payment id;
            //Get last payment id from payment table;
            String lastPaymentId = paymentRepository.getLastPaymentId();
            if (lastPaymentId == null) {
                newPaymentId = "PAY-" + currentYear + "-001";
            } else {
                //If any last id is available, then check the year of last is equal with current year;
                if (!lastPaymentId.substring(4, 8).equals(currentYear)) {
                    newPaymentId = "PAY-" + currentYear + "-001";
                } else {
                    int newNumericPartOfId = Integer.parseInt(lastPaymentId.substring(9, 12)) + 1;
                    newPaymentId = "PAY-" + currentYear + "-" + String.format("%03d", newNumericPartOfId);
                }
            }
            //Register payment to payment table;
            payment paymentObject = new payment(
                    newPaymentId,
                    paymentType,
                    LocalDateTime.now(),
                    userRepository.findById(httpRequest.getSessionUser()).get(),
                    0,
                    null
            );
            paymentRepository.save(paymentObject);
            paymentRegisterDTO registerObject = new paymentRegisterDTO(
                    newPaymentId,
                    paymentType,
                    LocalDateTime.now(),
                    userRepository.findById(httpRequest.getSessionUser()).get().getUserFirstName()
            );
            customAPIResponse<paymentRegisterDTO> response = new customAPIResponse(
                    true,
                    "Payment registered successfully with Payment ID: " + newPaymentId + ".",
                    registerObject
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-015")
    @LogActivity(methodDescription = "This method will search any payment details")
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPayment(String paymentId) {
        //Check whether the user has been provided a value for Payment ID
        if (paymentId == null || paymentId.isEmpty()) {
            throw new PaymentInputDataViolationException("Please provide all required data for successful searching.");
        } else {
            String Sql = "SELECT PAY.payment_id, PAY.payment_type, PAY.registered_date, USR.user_first_name, PAY.is_deleted FROM payment PAY LEFT JOIN user USR ON PAY.registered_by = USR.user_id WHERE PAY.payment_id = ?";
            List<paymentSearchDTO> searchedPaymentObj = template.query(Sql, new Object[]{paymentId}, new paymentSearchMapper());
            //Check whether any record is available for provided Payment ID;
            if (searchedPaymentObj.isEmpty()) {
                throw new PaymentInputDataViolationException("Incorrect Payment ID. Payment Details not available.");
            } else {
                //Check whether the payment is already deleted or not;
                if (searchedPaymentObj.get(0).getDeleteStatus() == 1) {
                    customAPIResponse<paymentSearchDTO> response = new customAPIResponse(
                            false,
                            "This Payment ID is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                } else {
                    customAPIResponse<paymentSearchDTO> response = new customAPIResponse(
                            true,
                            null,
                            searchedPaymentObj.get(0)
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-016")
    @LogActivity(methodDescription = "This method will display payment details for update")
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPaymentForUpdate(String paymentId) {
        //Check whether the user has been provided a value for Payment ID
        if (paymentId == null || paymentId.isEmpty()) {
            throw new PaymentInputDataViolationException("Please provide a value for Payment ID.");
        } else {
            String Sql = "SELECT PAY.payment_id, PAY.payment_type, PAY.registered_date, USR.user_first_name, PAY.is_deleted FROM payment PAY LEFT JOIN user USR ON PAY.registered_by = USR.user_id WHERE PAY.payment_id = ?";
            List<paymentSearchDTO> searchedPaymentObj = template.query(Sql, new Object[]{paymentId}, new paymentSearchMapper());
            //Check whether any record is available for provided Payment ID;
            if (searchedPaymentObj.isEmpty()) {
                throw new PaymentInputDataViolationException("Incorrect Payment ID. Payment Details not available.");
            } else {
                //Check whether the payment is already deleted or not;
                if (searchedPaymentObj.get(0).getDeleteStatus() == 1) {
                    customAPIResponse<paymentSearchDTO> response = new customAPIResponse(
                            false,
                            "This Payment ID is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                } else {
                    customAPIResponse<paymentSearchDTO> response = new customAPIResponse(
                            true,
                            null,
                            searchedPaymentObj.get(0)
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-016")
    @LogActivity(methodDescription = "This method will update payment details")
    public ResponseEntity<customAPIResponse<String>> updatePayment(@RequestParam String paymentType, @RequestParam String paymentId) {
        //Check whether user has been provided values for all required fields
        if (paymentType == null || paymentId == null || paymentType.isEmpty() || paymentId.isEmpty()) {
            throw new PaymentInputDataViolationException("Please provide all required data for successful payment update.");
        } else {
            int affectedRow = paymentRepository.updatePayment(paymentType, paymentId);
            if (affectedRow == 0) {
                throw new PaymentInputDataViolationException("Payment update failed. No affected rows found.");
            } else {
                customAPIResponse<String> response = new customAPIResponse(
                        true,
                        "Payment " + paymentId + " updated successfully.",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @Override
    @Session()
    @RequiresPermission("FUNC-017")
    @LogActivity(methodDescription = "This method will display payment details for delete")
    public ResponseEntity<customAPIResponse<paymentSearchDTO>> searchPaymentForDelete(String paymentId) {
        //Check whether the user has been provided a value for Payment ID
        if (paymentId == null || paymentId.isEmpty()) {
            throw new PaymentInputDataViolationException("Please provide Payment ID.");
        } else {
            String Sql = "SELECT PAY.payment_id, PAY.payment_type, PAY.registered_date, USR.user_first_name, PAY.is_deleted FROM payment PAY LEFT JOIN user USR ON PAY.registered_by = USR.user_id WHERE PAY.payment_id = ?";
            List<paymentSearchDTO> searchedPaymentObj = template.query(Sql, new Object[]{paymentId}, new paymentSearchMapper());
            //Check whether any record is available for provided Payment ID;
            if (searchedPaymentObj.isEmpty()) {
                throw new PaymentInputDataViolationException("Incorrect Payment ID. Payment Details not available.");
            } else {
                //Check whether the payment is already deleted or not;
                if (searchedPaymentObj.get(0).getDeleteStatus() == 1) {
                    customAPIResponse<paymentSearchDTO> response = new customAPIResponse(
                            false,
                            "This Payment ID is already deleted.",
                            null
                    );
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                } else {
                    customAPIResponse<paymentSearchDTO> response = new customAPIResponse(
                            true,
                            null,
                            searchedPaymentObj.get(0)
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission("FUNC-017")
    @LogActivity(methodDescription = "This method will delete payment details")
    public ResponseEntity<customAPIResponse<String>> deletePayment(String paymentId) {
        //Check whether user has been provided a value for Payment ID
        if (paymentId == null || paymentId.isEmpty()) {
            throw new PaymentInputDataViolationException("Please provide a valid Payment ID.");
        } else {
            int affectedRow = paymentRepository.deletePayment(httpRequest.getSessionUser(), paymentId);
            if (affectedRow == 0) {
                throw new PaymentInputDataViolationException("Payment deletion failed. No affected rows found.");
            } else {
                customAPIResponse<String> response = new customAPIResponse(
                        true,
                        "Payment " + paymentId + " deleted successfully.",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will fetch payment list")
    public ResponseEntity<customAPIResponse<List<getPaymentsForFundRequestDTO>>> getPaymentsForFundRequest() {
        List<getPaymentsForFundRequestDTO> paymentList = paymentRepository.paymentListForFundRequest();
        if (!paymentList.isEmpty()) {
            customAPIResponse<List<getPaymentsForFundRequestDTO>> response = new customAPIResponse(
                    true,
                    null,
                    paymentList
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            throw new PaymentNotFoundException("No Payment Types found");
        }
    }
}
