package application;

import java.util.*;
import customer.*;
import property.*;
import realEstateException.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.*;

public class RealEstate {
    private HashMap<String, String> suburb_list = new HashMap<String, String>();
    private ArrayList<Property> pr = new ArrayList<Property>();
    private Scanner sc = new Scanner(System.in);
    Customers current_customer;
    int choice;

    public RealEstate() {

    }

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
        String emailAddress;
        String password;
        String customer_details = null;
        System.out.println("***login in***");
        String cust_array [];
        System.out.println("Enter your email");
        emailAddress = sc.next();
        System.out.println("Enter your password");
        password = sc.next();
        try {
            customer_details = LinkDatabase.logIn(emailAddress, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cust_array = customer_details.split("_");

        //choose which customer
        System.out.println("choose if you are 1:buyer,2:renter,3:vendor,4:landlord");
         int num = sc.nextInt();
             switch (num) {
                 case 1:
                     System.out.println("you chose buyer!");
                     current_customer = new Buyer(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                     buyerLogin();
                     break;

                 case 2:
                     System.out.println("you chose renter!");
                     current_customer = new Renter(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                     renterLogin();
                     break;
                 case 3:
                     System.out.println("you chose vendor!");
                     current_customer = new Vendor(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                     vendorLogin();

                     break;
                 case 4:
                     System.out.println("you chose landlord!");
                     current_customer = new Landlord(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                     landlordLogin();
                     break;

             }
        
    }

    public void register() {
        String custName = null;
        String emailAddress = null;
        String passWord = null;
        String passWord2 = null;
        boolean valid;
        System.out.println("enter your information to register");

        System.out.println("enter your customer name");
        custName = sc.next();


        do {
            valid = false;
            System.out.println("enter your email address,email address must contain @ and.com");
            emailAddress = sc.next();
            try {
                Customers.checkEmailFormat(emailAddress);
                valid = true;
            } catch (WrongEmailFormatException e) {
                e.printStackTrace();
            }
        }
        while (!valid);
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
        do{
            System.out.println("enter 1 for uploading a property, 2 for exit the program");
            int num=sc.nextInt();
            switch(num) {
                case 1:
                    uploadProperty();
                    break;
                case 2:
                    System.exit(0);
                    break;
            }}while(true);
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

    //this is add suburb function for buyer and renter and load suburb function of the realEstate

    public void addSuburb() {
        System.out.println(suburb_list);
        String keyCheck = sc.next();
       boolean isKeyPresent = suburb_list.containsKey(keyCheck);
          if (isKeyPresent == true) {
        	  System.out.println("suburb added");
              try {
                  current_customer.addSuburb( keyCheck);
              } catch (DuplicateSuburbException e) {
                  e.printStackTrace();
              }
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

    public void uploadProperty() {
        String[] arr;
        boolean valid =false;

        System.out.println("Please enter the property details in this order: "
                + "id, address, suburb code, property type, bedroom number, bathroom number, car space number");

        sc.useDelimiter("\n");
        String input = sc.next();
        arr = input.split(",", 7);

        // check ID
        checkID(arr[0]);

        // check if this property already exists in the system
        do{
            valid = propertyAlreadyExist(arr);
            if(valid)
                System.out.println("Property added! ");
            else{
                System.out.println("property Id already exists!");
                System.out.printf("please enter a new Id:");
                String Id = sc.next();
                arr[0] = Id;
            }
        }while(!valid);
        try{
            Property p = new Property(arr[0], arr[1], arr[2], arr[3],
                Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));
            p.setOwnerID(Integer.parseInt(current_customer.getCustomerId()));

           if (current_customer.getClass().getName().equals("customer.Landlord")){
                System.out.println("input your your weekly rent/contract length");
                input = sc.next();
                String[] arr1 = input.split("/", 2);
                p.addRental(Double.parseDouble(arr1[0]), Double.parseDouble(arr1[1]));
            }
           pr.add(p);
           LinkDatabase.uploadProperty(p);
           System.out.println("you have successful uploaded a house and started a rental");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //this function is used by landlord and vendor to upload property to the realEstate.

    public boolean propertyAlreadyExist(String arr[]) {
        PreExistException ex = new PreExistException();

        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).getId().equals(arr[0]))
                return false;
        }
        return true;
    }
    // check duplicate ID

    //these method below are created for test
     public void addProperty(Property p){
        pr.add(p);
    }
}


