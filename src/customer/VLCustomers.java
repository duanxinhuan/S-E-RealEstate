package customer;

import property.Property;
import property.Type;

import java.util.ArrayList;

public class VLCustomers extends Customers {
    private ArrayList<String> propertyList = new ArrayList<String>();
    public VLCustomers(String customerId, String passWord,String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public Property addProperty(String id, String address, String suburbCode, Type propertyType, int numOfBedroom,
                                int numOfBath, int numOfCarSpace, double minPrice){
        Property property = new Property(id, address, suburbCode, propertyType,numOfBedroom,
                numOfBath,numOfCarSpace) ;
        propertyList.add(id);
        return property;
    }

    public ArrayList<String> getPropertyList() {
        return propertyList;
    }
}
