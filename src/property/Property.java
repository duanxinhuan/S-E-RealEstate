package property;

import customer.Customers;
import employees.Employee;
import realEstateException.CanNotRecommendException;
import realEstateException.HouseAlreadyAssignedException;
import realEstateException.WrongIdException;

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
	private String[] type;
	private int ownerID;
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
    private ArrayList<ForSale> forSales = new ArrayList<ForSale>();
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

	public ArrayList<Rental> getRental() {
		return rentals;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public ArrayList<ForSale> getForSales() {
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

	public void setAssigned(boolean isAssigned) {
	}

	public void addRental(double weeklyRent, double contractLength){
		Rental r1 = new Rental(weeklyRent, contractLength);
		r1.setRentalId(getId() + "_R" + (rentals.size()+1));
    	rentals.add(r1);
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public void addForSale(ForSale forSale){
    	forSales.add(forSale);
	}

	public void addForSale(double minPrice, double commissionRate){
		ForSale f1 = new ForSale(minPrice, commissionRate);
		f1.setSaleId(getId() + "_S" + (forSales.size()+1));
		forSales.add(f1);
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

	public void assign(Employee emp, String ID) throws HouseAlreadyAssignedException {
    	if(emp.getClass().getName().equals("employees.PropertyManager")){
			for(int i =0; i< rentals.size(); i++){
				if (rentals.get(i).getRentalId().equals(ID))
					rentals.get(i).assign(emp);
			}
		}
    	else {
			for (int i = 0; i < forSales.size(); i++) {
				if (forSales.get(i).getSaleId().equals(ID))
					rentals.get(i).assign(emp);
			}
		}
	}

    public int getOwnerID() {
        return ownerID;
    }

    public void negotiate(String rentalId, boolean b) {
        for(int i =0; i<rentals.size(); i++){
            if(rentals.get(i).getRentalId().equals(rentalId))
                rentals.get(i).negotiate(b);
        }
    }

    public Rental getRental(String rentalId) throws WrongIdException {
        for(int i = 0; i<rentals.size();i++){
            if(rentals.get(i).getRentalId().equals(rentalId)){
                return rentals.get(i);
            }
        else
            throw new WrongIdException();
        }
        return null;
    }

	public String generateRecommendation  () {
    	String s = "";
    	for(int i =0; i<rentals.size();i++){

			try {
				s+=rentals.get(i).generateRecommendation(getId());
			} catch (CanNotRecommendException e) {
			}
			s+="\naddress: " +getAddress()
				+ "\nproperty type: " +getPropertyType()
				+ "\nnumber of bed: " +getNumOfBedroom()
				+"\nnumber of bath: " +getNumOfBath()
				+"\ncar space: " + getNumOfCarSpace()
				+"\n-------------------------------------------------------";
		}

		return s;
	}
}
