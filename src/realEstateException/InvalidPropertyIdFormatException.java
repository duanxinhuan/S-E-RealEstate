package realEstateException;

public class InvalidPropertyIdFormatException extends Exception {
    public InvalidPropertyIdFormatException() {
        super("This property Id format is invalid");
    }
}
