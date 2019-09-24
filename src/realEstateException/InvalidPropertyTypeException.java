package realEstateException;

public class InvalidPropertyTypeException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidPropertyTypeException(String errorMessage) {
        super("The property type entered is invalid!");
    }

}

