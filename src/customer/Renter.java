package customer;

public class Renter extends BRCustomers {
    int numOfRent;

    public Renter(String customerId, String passWord, String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

}
