package customer;

public abstract class Customers {
    private final String customerId;
    private final String passWord;
    private  final String custName;
    private final String emailAddress;

    public Customers(String customerId, String passWord, String custName, String emailAddress) {
        this.customerId = customerId;
        this.passWord = passWord;
        this.custName = custName;
        this.emailAddress = emailAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustName() {
        return custName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static String listToString(String[] s){
        String listDetails = s[0];

        for(int i =1 ;i<s.length; i++){
            listDetails = listDetails+"_" + s[i];
        }

        return  listDetails;
    }

    public abstract void addSuburb();
}
