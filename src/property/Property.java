package property;

import java.util.ArrayList;
import java.util.Scanner;

public class Property {

    private String id;
    private String address;
    private String suburbCode;
    private String propertyType;
    private int numOfBedroom;
    private int numOfBath;
    private int numOfCarSpace;
    private boolean isAssigned = false;
    private String[] type;



	private int ownerID;
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
    private ArrayList<Forsale> forSales = new ArrayList<Forsale>();
    private Scanner sc = new Scanner(System.in);

    public Property(String id, String address, String suburbCode, String propertyType, int numOfBedroom, int numOfBath,
                    int numOfCarSpace) {
        this.id = id;
        this.address = address;
        this.suburbCode = suburbCode;
        this.type = new String[]{"house","unit","flat","townhouse","studio"};
        // check property type
        if(this.checkPropertyType(propertyType)) {
        	this.setPropertyType(propertyType);
        }
        else {
        	boolean check = false;
        	while(!check) {
        		System.out.print("Enter Type:");
        		propertyType = sc.next();
        		check = this.checkPropertyType(propertyType);
        	}
        	this.setPropertyType(propertyType);
        }
        this.setNumOfBedroom(numOfBedroom);
        this.setNumOfBath(numOfBath);
        this.setNumOfCarSpace(numOfCarSpace);
    }

	public ArrayList<Rental> getRentals() {
		return rentals;
	}

	public ArrayList<Forsale> getForSales() {
		return forSales;
	}
    
	public String getAddress() {
		return address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuburbCode() {
		return suburbCode;
	}

	public void setSuburbCode(String suburbCode) {
		this.suburbCode = suburbCode;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public int getNumOfBedroom() {
		return numOfBedroom;
	}

	public void setNumOfBedroom(int numOfBedroom) {
		this.numOfBedroom = numOfBedroom;
	}

	public int getNumOfBath() {
		return numOfBath;
	}

	public void setNumOfBath(int numOfBath) {
		this.numOfBath = numOfBath;
	}

	public int getNumOfCarSpace() {
		return numOfCarSpace;
	}

	public void setNumOfCarSpace(int numOfCarSpace) {
		this.numOfCarSpace = numOfCarSpace;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public void addRental(double weeklyRent, double contractLength){
		Rental r1 = new Rental(weeklyRent, contractLength);
		r1.setRentalId(getId() + "_" + (rentals.size()+1));
    	rentals.add(r1);
	}

	public void addRental(Rental r){
    	rentals.add(r);
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public void addForsale(){
		forSales.add(new Forsale());
	}
	
	public boolean checkPropertyType(String propertyType) {
		for(int i=0; i<5; i++) {
			if(propertyType.compareToIgnoreCase(type[i]) == 0) {
				return true;
			}
		}
		System.out.println("Invalid Type!!!");
		return false;
	}

}
