package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.ExceptionHandlers.TransferExceptions;

public class AccountBalanceNotFoundException extends TransferException{
    public AccountBalanceNotFoundException(String message){
        super(message);
    }
}
