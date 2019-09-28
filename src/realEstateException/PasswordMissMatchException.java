package realEstateException;

public class PasswordMissMatchException extends Exception {
    public PasswordMissMatchException(){
        super("Your password doesn't match with each other!");
    }
}
