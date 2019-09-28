package customer;

import property.Property;
import realEstateException.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Customers {
    private final String customerId;
    private final String passWord;
    private  final String customerName;
    private final String emailAddress;

    public Customers(String customerId, String passWord, String customerName, String emailAddress) {
        this.customerId = customerId;
        this.passWord = passWord;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
    }


    public static void checkCustName(String custName) throws WrongCustomerNameFormatException {

        String pattern = "[a-zA-Z ]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custName);
        boolean b = m.matches();
        if(!b)
            throw new WrongCustomerNameFormatException();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
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

    public void addSuburb(String suburbCode) throws DuplicateSuburbException {};

    public static  void checkEmailFormat(String emailAddress) throws WrongEmailFormatException {
        String[] domainName  = {".org",".edu", ".au", ".eu",".na",".cn",".com",".net",".gov"};

        if(!emailAddress.contains("@")){
            throw new WrongEmailFormatException();
        }

        for(int i = 0;i<domainName.length;i++){
            if(emailAddress.contains(domainName[i])){
                System.out.println("your email Address is valid!");
                return;
            }
        }
        throw new WrongEmailFormatException();
    }

    public static void confirmPassword(String passWord1, String passWord2) throws PasswordMissMatchException {
        if(passWord1.equals(passWord2)){
            return;
        }
        else throw new PasswordMissMatchException();
    }

    public String[] getSuburbCodeList(){
        return null;
    }
}
