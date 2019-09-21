package customer;

import java.util.ArrayList;

import property.Property;



public class Landlord extends VLCustomers {

    private ArrayList<Property> properties = new ArrayList<Property>();
	private int numOfRent = 0;
	private boolean negotiate = false;

    public Landlord(String customerId, String passWord,String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public void addProperty(Property property) {
        properties.add(property);
        numOfRent++;
    }
    
    public int getNumOfRent() {
    	return this.numOfRent;
    }
    
    public boolean getNegotiate() {
    	return negotiate;
    }
    
    public void setNegotiate() {
    	this.negotiate = true;
    }
}
