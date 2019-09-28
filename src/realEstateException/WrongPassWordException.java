package realEstateException;

public class WrongPassWordException extends Exception {
    public WrongPassWordException(){
        super("Wrong password");
    }
}

