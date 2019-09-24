package realEstateException;

public class WrongEmailFormatException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WrongEmailFormatException() {
        super("Invalid email format");
    }
}
