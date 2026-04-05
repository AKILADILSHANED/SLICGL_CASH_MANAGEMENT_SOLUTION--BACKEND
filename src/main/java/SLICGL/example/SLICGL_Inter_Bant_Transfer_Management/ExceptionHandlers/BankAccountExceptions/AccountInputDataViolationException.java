package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions;

public class AccountInputDataViolationException extends BankAccountException{
    public AccountInputDataViolationException(String message){
        super(message);
    }
}
