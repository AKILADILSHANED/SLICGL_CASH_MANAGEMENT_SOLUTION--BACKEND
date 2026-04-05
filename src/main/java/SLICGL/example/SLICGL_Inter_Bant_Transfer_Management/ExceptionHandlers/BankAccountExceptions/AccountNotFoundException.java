package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.BankAccountExceptions;

public class AccountNotFoundException extends BankAccountException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
