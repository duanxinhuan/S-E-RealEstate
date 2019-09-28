package customer;

import realEstateException.InvalidPropertyIdFormatException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class VLCustomers extends Customers {

    private ArrayList<String> propertyList = new ArrayList<String>();

    public VLCustomers(String customerId, String passWord,String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }


    public ArrayList<String> getPropertyList() {
        return propertyList;
    }

    // check Property ID format when you upload property to the real estate system
    public static void checkPropertyIDFormat(String id) throws InvalidPropertyIdFormatException {

        String pattern = "[P][0-9]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(id);
        boolean b = m.matches();
        if(!b)
            throw new InvalidPropertyIdFormatException();
    }
}
