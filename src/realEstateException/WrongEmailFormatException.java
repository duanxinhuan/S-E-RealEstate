package realEstateException;

public class WrongEmailFormatException extends Exception {

    public WrongEmailFormatException() {
        super("Invalid email format");
    }
}
