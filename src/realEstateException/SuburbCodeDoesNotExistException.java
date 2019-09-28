package realEstateException;

public class SuburbCodeDoesNotExistException extends Exception {

    public SuburbCodeDoesNotExistException() {
        super("This suburb code does not exist!!");
    }
}
