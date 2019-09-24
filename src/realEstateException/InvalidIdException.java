package realEstateException;

public class InvalidIdException extends Exception {
    public InvalidIdException() {
        super("the Id format is invalid");
    }
}
