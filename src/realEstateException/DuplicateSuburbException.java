package realEstateException;

public class DuplicateSuburbException extends Exception {

    private static final long serialVersionUID = 1L;

    public DuplicateSuburbException() {
        super("Property Already exists!!!");
    }

}
