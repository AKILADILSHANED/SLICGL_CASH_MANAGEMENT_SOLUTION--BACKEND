package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AccountBalanceExceptions.BalanceInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AccountBalanceExceptions.BalanceNotUpdateException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityGrantingFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AuthorityExceptions.AuthorityRevokingFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions.AccountNotUpdateException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelDeletionFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ChannelExceptions.ChannelUpdateFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.FundRequestExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PasswordExceptions.PasswordResetFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PasswordExceptions.PasswordResetInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PasswordExceptions.PasswordUnlockFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PasswordExceptions.PasswordUnlockInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PaymentExceptions.PaymentInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PaymentExceptions.PaymentNotFoundException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.PrintingExceptions.PrintingInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.RepoAdjustmentExceptions.RepoAdjustmentDeletionFailureException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.RepoAdjustmentExceptions.RepoAdjustmentInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.RepoExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.ReportExceptions.ReportInputDataViolationException;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.SessionExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.TransferExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.TransferOptionExceptions.*;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.UserExceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<customAPIResponse<String>> handleSecurityException(SecurityException e, HttpServletRequest request) {
        logger.error("User: {} | {}", request.getSession().getAttribute("userId"), e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(InvalidLoginDataException.class)
    public ResponseEntity<customAPIResponse<String>> InvalidLoginDataException(InvalidLoginDataException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(LoginAttemptExceedException.class)
    public ResponseEntity<customAPIResponse<String>> LoginAttemptExceedException(LoginAttemptExceedException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(InactiveUserException.class)
    public ResponseEntity<customAPIResponse<String>> InactiveUserException(InactiveUserException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PasswordResetException.class)
    public ResponseEntity<customAPIResponse<String>> PasswordResetException(PasswordResetException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(InvalidPasswordResetDataException.class)
    public ResponseEntity<customAPIResponse<String>> InvalidPasswordResetDataException(InvalidPasswordResetDataException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PasswordEncryptException.class)
    public ResponseEntity<customAPIResponse<String>> PasswordEncryptException(PasswordEncryptException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(UserInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> UserInputDataViolationException(UserInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(SelfDeletionException.class)
    public ResponseEntity<customAPIResponse<String>> SelfDeletionException(SelfDeletionException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(AccountInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> AccountInputDataViolationException(AccountInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(AccountNotUpdateException.class)
    public ResponseEntity<customAPIResponse<String>> AccountNotUpdateException(AccountNotUpdateException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(BalanceInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> BalanceInputDataViolationException(BalanceInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(BalanceNotUpdateException.class)
    public ResponseEntity<customAPIResponse<String>> BalanceNotUpdateException(BalanceNotUpdateException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PaymentInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> PaymentInputDataViolationException(PaymentInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<customAPIResponse<String>> Exception(Exception e, HttpServletRequest request) {
        logger.error("User: {} | Message: {}", request.getSession().getAttribute("userId"), e.getMessage());
        logger.error("Cause: {}", e.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        "An Exception occurred. Please contact administrator!",
                        null
                )
        );
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> AccountNotFoundException(AccountNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> AccountNotFoundException(PaymentNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RequestInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> RequestInputDataViolationException(RequestInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RequestUpdateFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RequestUpdateFailureException(RequestUpdateFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RequestDeleteFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RequestDeleteFailureException(RequestDeleteFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RequestApprovalFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RequestApprovalFailureException(RequestApprovalFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RequestReversalFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RequestReversalFailureException(RequestReversalFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoUnavailabilityException.class)
    public ResponseEntity<customAPIResponse<String>> RepoUnAvailabilityException(RepoUnavailabilityException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(AccountBalanceNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> AccountBalanceNotFoundException(AccountBalanceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TransferInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> TransferInputDataViolationException(TransferInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TransferApprovalFailureException.class)
    public ResponseEntity<customAPIResponse<String>> TransferApprovalFailureException(TransferApprovalFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TransferUncheckFailureException.class)
    public ResponseEntity<customAPIResponse<String>> TransferUncheckFailureException(TransferUncheckFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TransferCheckFailureException.class)
    public ResponseEntity<customAPIResponse<String>> TransferCheckFailureException(TransferCheckFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TransferUnApproveFailureException.class)
    public ResponseEntity<customAPIResponse<String>> TransferUnApproveFailureException(TransferUnApproveFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TransferReversalFailureException.class)
    public ResponseEntity<customAPIResponse<String>> TransferReversalFailureException(TransferReversalFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> RepoInputDataViolationException(RepoInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(NegativeRepoBalanceException.class)
    public ResponseEntity<customAPIResponse<String>> NegativeRepoBalanceException(NegativeRepoBalanceException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<customAPIResponse<String>> InsufficientBalanceException(InsufficientBalanceException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoDeletionFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RepoDeletionFailureException(RepoDeletionFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoAdjustmentInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> RepoAdjustmentInputDataViolationException(RepoAdjustmentInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoAdjustmentDeletionFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RepoAdjustmentDeletionFailureException(RepoAdjustmentDeletionFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoInvestmentFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RepoInvestmentFailureException(RepoInvestmentFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(RepoInvestmentReversalFailureException.class)
    public ResponseEntity<customAPIResponse<String>> RepoInvestmentReversalFailureException(RepoInvestmentReversalFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(ReportInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> ReportInputDataViolationException(ReportInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PrintingInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> PrintingInputDataViolationException(PrintingInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(ChannelInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> ChannelInputDataViolationException(ChannelInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> ChannelNotFoundException(ChannelNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(ChannelDeletionFailureException.class)
    public ResponseEntity<customAPIResponse<String>> ChannelDeletionFailureException(ChannelDeletionFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(ChannelUpdateFailureException.class)
    public ResponseEntity<customAPIResponse<String>> ChannelUpdateFailureException(ChannelUpdateFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(OptionInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> OptionInputDataViolationException(OptionInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(EmptyOptionListException.class)
    public ResponseEntity<customAPIResponse<String>> EmptyOptionListException(EmptyOptionListException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(OptionDeactivateFailureException.class)
    public ResponseEntity<customAPIResponse<String>> OptionDeactivateFailureException(OptionDeactivateFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(OptionReactivateFailureException.class)
    public ResponseEntity<customAPIResponse<String>> OptionReactivateFailureException(OptionReactivateFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(OptionDeletionFailureException.class)
    public ResponseEntity<customAPIResponse<String>> OptionDeletionFailureException(OptionDeletionFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(AuthorityInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> AuthorityInputDataViolationException(AuthorityInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(AuthorityGrantingFailureException.class)
    public ResponseEntity<customAPIResponse<String>> AuthorityGrantingFailureException(AuthorityGrantingFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(AuthorityRevokingFailureException.class)
    public ResponseEntity<customAPIResponse<String>> AuthorityRevokingFailureException(AuthorityRevokingFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PasswordUnlockInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> PasswordUnlockInputDataViolationException(PasswordUnlockInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PasswordResetInputDataViolationException.class)
    public ResponseEntity<customAPIResponse<String>> PasswordResetInputDataViolationException(PasswordResetInputDataViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PasswordUnlockFailureException.class)
    public ResponseEntity<customAPIResponse<String>> PasswordUnlockFailureException(PasswordUnlockFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(PasswordResetFailureException.class)
    public ResponseEntity<customAPIResponse<String>> PasswordResetFailureException(PasswordResetFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(SessionValidationException.class)
    public ResponseEntity<customAPIResponse<String>> SessionValidationException(SessionValidationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> TokenNotFoundException(TokenNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(LogoutFailureException.class)
    public ResponseEntity<customAPIResponse<String>> LogoutFailureException(LogoutFailureException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(SessionUserNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> SessionUserNotFoundException(SessionUserNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(CookiesNotFoundException.class)
    public ResponseEntity<customAPIResponse<String>> CookiesNotFoundException(CookiesNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new customAPIResponse<>(
                        false,
                        e.getMessage(),
                        null
                )
        );
    }
}
