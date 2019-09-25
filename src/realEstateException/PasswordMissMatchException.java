package realEstateException;

public class PasswordMissMatchException extends Throwable {
    public PasswordMissMatchException(){
        super("you password don't match to each other");
    }
}
