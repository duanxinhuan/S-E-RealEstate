package realEstateException;

public class WrongCustomerNameFormatException extends Exception {

    public WrongCustomerNameFormatException() {
        super("Customer name format is invalid!!");
    }
}
