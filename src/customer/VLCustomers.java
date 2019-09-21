package customer;

import property.Property;

import java.util.ArrayList;

public abstract class VLCustomers extends Customers {
    private ArrayList<String> propertyList = new ArrayList<String>();
    public VLCustomers(String customerId, String passWord,String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }


    public ArrayList<String> getPropertyList() {
        return propertyList;
    }
}
