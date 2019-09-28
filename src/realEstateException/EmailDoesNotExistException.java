package realEstateException;

public class EmailDoesNotExistException extends Exception {

    public EmailDoesNotExistException() {
        super("Email doesn't exist!");
    }
}
