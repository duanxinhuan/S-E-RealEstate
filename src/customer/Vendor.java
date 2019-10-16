package customer;

import property.Property;

public class Vendor extends VLCustomers {


    int numOfSell = 0;

    public Vendor(String customerId, String passWord, String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public void addProperty(Property p) {
        numOfSell+=1;
    }

    @Override
    public void editDetails() {

    }


}








