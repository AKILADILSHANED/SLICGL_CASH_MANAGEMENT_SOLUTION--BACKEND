package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.AccountBalanceExceptions;

public class BalanceInputDataViolationException extends AccountBalanceException{
    public BalanceInputDataViolationException(String message){
        super(message);
    }
}
