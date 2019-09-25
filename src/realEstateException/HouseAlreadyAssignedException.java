package realEstateException;

public class HouseAlreadyAssignedException extends Exception {


    public HouseAlreadyAssignedException() {
        super("The house has been assigned");
    }

}
