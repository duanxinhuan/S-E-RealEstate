package application;

import java.util.*;

import customer.*;
import property.Property;

import realEstateException.InvalidPropertyTypeException;
//import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.*;
//import static java.lang.System.exit;


public class RealEstate {
    private HashMap<String, String> suburb_list = new HashMap<String, String>();
    private ArrayList<Property> pr = new ArrayList<Property>();
    private Scanner sc = new Scanner(System.in);
    Customers current_customer;
    int choice;


    public void startRealEstate(){
        LinkDatabase.connectJDBCToAWSEC2();

        loadSuburb();
       do{
        System.out.println("1.register\n" +"2.login");
        choice = sc.nextInt();

        switch (choice){
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("See you next time!");
            
        }
       }while(choice !=3);
    }

    public void login() {
        try {
            String emailAddress;
            String password;
            String customer_details = null;
            System.out.println("***login in***");
            String cust_array[];
            System.out.println("Enter your email");
            emailAddress = sc.next();
            System.out.println("Enter your password");
            password = sc.next();

            customer_details = LinkDatabase.logIn(emailAddress, password);
            cust_array = customer_details.split("_");

            //choose which customer
            System.out.println("choose if you are 1:buyer,2:renter,3:vendor,4:landlord");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    System.out.println("you chose buyer!");
                    current_customer = new Buyer(cust_array[0], cust_array[1], cust_array[2], cust_array[3]);
                    buyerLogin();
                    break;

                case 2:
                    System.out.println("you chose renter!");
                    current_customer = new Renter(cust_array[0], cust_array[1], cust_array[2], cust_array[3]);
                    renterLogin();
                    break;
                case 3:
                    System.out.println("you chose vendor!");
                    current_customer = new Vendor(cust_array[0], cust_array[1], cust_array[2], cust_array[3]);
                    vendorLogin();

                    break;
                case 4:
                    System.out.println("you chose landlord!");
                    current_customer = new Landlord(cust_array[0], cust_array[1], cust_array[2], cust_array[3]);
                    landlordLogin();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyerLogin(){

        do{
            System.out.println("enter 1 for add suburb, 2 for exit the program");
            int num=sc.nextInt();
            switch(num){
                case 1:addSuburb();
                break;
                case 2:System.exit(0);
        }}while(true);
    }

    public void vendorLogin() {
        System.out.println("enter 1 for uploading a property, 2 for exit the program");
        int num=sc.nextInt();
        switch(num){
            case 1:
                uploadProperty();
            case 2:
                System.exit(0);
        }

    }

    public void landlordLogin() {
        System.out.println("enter 1 for uploading a property, 2 for exit the program");
        int num=sc.nextInt();
        switch(num) {
            case 1:
                uploadProperty();
            case 2:
                System.exit(0);
        }
    }

    public void renterLogin() {
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num) {
            case 1:
                addSuburb();
            case 2:
                System.exit(0);
        }
    }

    // login function

    //this is connect class, this is used to connect to AWS server where the database is built.


    public void addSuburb() {
        System.out.println(suburb_list);
        String keyCheck = sc.next();
       boolean isKeyPresent = suburb_list.containsKey(keyCheck);
          if (isKeyPresent == true) {
        	  System.out.println("suburb added");
              current_customer.addSuburb( keyCheck);
          }
          else {
        	  System.out.println("suburb doesn't exist");
          }
      }

    public void loadSuburb(){
        try{
            System.out.println("Loading suburb list...");
            BufferedReader reader = new BufferedReader(new FileReader("src//suburb.csv"));
            reader.readLine();
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",",2);
                suburb_list.put(item[0],item[1]);
            }
            reader.close();
            System.out.println("Stream Closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // check the format of email input
    public boolean checkEmailFormat(String emailAddress) {
        if(!emailAddress.contains("@") && !emailAddress.contains(".com")) {
            return !emailAddress.contains("@") && !emailAddress.contains(".com");
        }
        else if(!emailAddress.contains("@") && !emailAddress.contains(".org")) {
            return !emailAddress.contains("@") && !emailAddress.contains(".org");
        }
        else if(!emailAddress.contains("@") && !emailAddress.contains(".net")) {
            return !emailAddress.contains("@") && !emailAddress.contains(".net");
        }
        else if(!emailAddress.contains("@") && !emailAddress.contains("..gov")) {
            return !emailAddress.contains("@") && !emailAddress.contains(".gov");
        }
        else if(!emailAddress.contains("@") && !emailAddress.contains(".edu")) {
            return !emailAddress.contains("@") && !emailAddress.contains(".edu");
        }
        return false;

    }

    public void register() {
        String custName = null;
        String emailAddress = null;
        String passWord = null;
        String passWord2 = null;
        System.out.println("enter your information to register");
       
        System.out.println("enter your customer name");
        custName = sc.next();

        do {
            System.out.println("enter your email address and your email address must contain @ and one of these domain names: " +
                    ".com, .org, .net, .gov, .edu" );
            emailAddress = sc.next();
        } while (!emailAddress.contains("@") && !emailAddress.contains(".com"));

        System.out.println("create your password");
        passWord = sc.next();
        System.out.println("confirm your password");
        passWord2 = sc.next();

        while (!passWord.equals(passWord2)) {
            System.out.println("password mismatch ");
            System.out.println("enter your password again");
            passWord = sc.next();
            System.out.println("confirm your password");
            passWord2 = sc.next();
        }
        System.out.println("register successfully");
        System.out.println("here is your account details: ");
        System.out.println("password length: " + (passWord.length()));
        
        LinkDatabase.register(passWord, custName, emailAddress);
    }
    
    public boolean checkID(String id) {
        String pattern = "[P][0-9]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(id);
        boolean b = m.matches();

   	    if(!b)
		   System.out.println("Invalid input!!!");
	    return b;
    }
    // check if Id starts with "P"

    public boolean propertyAlreadyExist(String arr[]) {

        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).getId().equals(arr[0]))
                return false;
        }
        return true;
    }
    // check duplicate ID

    public void uploadProperty() {
        String[] arr;
        boolean valid = false;

        System.out.println("Please enter the property details in this order: "
                + "id, address, suburb code, property type, bedroom number, bathroom number, car space number");

        while (sc.hasNext()) {
            sc.useDelimiter("\n");
            String input = sc.next();
            arr = input.split(",", 7);

            // check ID
            checkID(arr[0]);

            // check if this property already exists in the system
            do {
                valid = propertyAlreadyExist(arr);
                if (valid)
                    System.out.println("Property added! ");
                else {
                    System.out.println("property Id already exists!");
                    System.out.printf("please enter a new Id:");
                    String Id = sc.next();
                    arr[0] = Id;
                }
            } while (!valid);

            Property p;
            try {
                p = new Property(arr[0], arr[1], arr[2], arr[3],
                        Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));

                // check property type
                if (p.checkPropertyType(arr[3])) {
                    p.setPropertyType(arr[3]);
                } else {
                    throw new InvalidPropertyTypeException("The property type entered is invalid!");
                }

                p.setOwnerID(Integer.parseInt(current_customer.getCustomerId()));

                if (current_customer.getClass().getName().equals("customer.Landlord")) {
                    System.out.println("input your your weekly rent/contract length");
                    input = sc.next();
                    arr = input.split("/", 2);
                    p.addRental(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
                } else {
                    System.out.println("input your property minPrice/commissionRate");
                    input = sc.next();
                    arr = input.split("/", 2);
                    p.addForSale(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
                }
                pr.add(p);
            } catch (InvalidPropertyTypeException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        // for testing purpose
    public void addProperty(Property p){
        pr.add(p);
    }

}


