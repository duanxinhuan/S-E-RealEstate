package realEstateException;

public class WrongIdException extends Exception {

    public WrongIdException() {
        super("Id entered is wrong!");
    }
}
