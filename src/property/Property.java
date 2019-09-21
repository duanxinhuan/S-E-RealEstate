package property;

import java.util.ArrayList;

public class Property {

    private String id;
    private String address;
    private String suburbCode;
    private Type propertyType;
    private int numOfBedroom;
    private int numOfBath;
    private int numOfCarSpace;
    private boolean isAssigned = false;
    private ArrayList<Rental> rentals = new ArrayList<Rental>();

    public Property(String id, String address, String suburbCode, Type propertyType, int numOfBedroom, int numOfBath,
                    int numOfCarSpace) {
        this.id = id;
        this.address = address;
        this.suburbCode = suburbCode;
        this.propertyType = propertyType;
        this.numOfBedroom = numOfBedroom;
        this.numOfBath = numOfBath;
        this.numOfCarSpace = numOfCarSpace;
    }





}
