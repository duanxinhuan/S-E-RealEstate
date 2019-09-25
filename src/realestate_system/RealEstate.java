package realestate_system;

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
    private ArrayList<Application> applicationList;
    Customers current_customer;
    int choice;


    public void startRealEstate(){
        LinkDatabase.connectJDBCToAWSEC2();
        loadSuburb();
        System.out.println("Loading properties");
        try {
            pr = LinkDatabase.loadProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            String cust_array [];
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
        catch (SQLException ex0) { }
        catch (NullPointerException ex1){ System.out.println(); }
        catch (EmailDoesNotExistException ex2) { ex2.printStackTrace(); }
        catch (WrongPassWordException ex3) { System.out.println("wrong password!"); }
    }

    public void register() {
        String custName = null;
        String emailAddress = null;
        String passWord = null;
        String passWord2 = null;
        boolean valid;
        System.out.println("enter your information to register");


//        do {
//            valid = false;
            System.out.println("enter your customer name");
            custName = sc.next();
//            try{
//                Customers.checkCustName(custName);
//            }
//        }
//        while (!valid)

        do {
            valid = false;
            System.out.println("enter your email address");
            emailAddress = sc.next();
            try {
                Customers.checkEmailFormat(emailAddress);
                valid = true;
            } catch (WrongEmailFormatException e) {
                e.printStackTrace();
            }
        }
        while (!valid);

        do{
            valid = false;
            System.out.println("create your password");
            passWord = sc.next();
            System.out.println("confirm your password");
            passWord2 = sc.next();
            try {
                Customers.confirmPassword(passWord,passWord2);
                valid = true;
            } catch (PasswordMissMatchException e) {
                System.out.println(e.toString());
            }
        }while(!valid);


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
            }
        }while(true);
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
            }
        }while(true);
    }

    public void renterLogin() {
        int num;
        do{
            System.out.println("enter 1 add suburb, 2 get recommendations, 3 make rental application, 4 exit the program");
            num=sc.nextInt();
        switch(num) {
            case 1:
                addSuburb();
                break;
            case 2:
                getRecommendations(current_customer);
                break;
            case 3:
                makeRentalApplication(current_customer);
            case 4:
                System.exit(0);
        }
        }while(true);
    }

    public boolean checkPropertyID(String propertyID) {

        for(int i=0; i<pr.size(); i++ ) {
            if(propertyID.equals(pr.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public void makeRentalApplication(Customers c) {
        System.out.println("Please enter renter name:");
        String renterName = sc.next();

        System.out.println("Please enter your rental ID:");
        String rentalID = sc.next();

        String[] arr = rentalID.split("_");
        String propertyID = arr[0];

        checkPropertyID(propertyID);

        System.out.println("Please enter your occupation:");
        String occupation = sc.next();

        System.out.println("Please enter your income:");
        double income = sc.nextDouble();

        System.out.println("Please enter your contract length and contract price:" );
        double contractLength = sc.nextDouble();
        double contractPrice = sc.nextDouble();
        try {
            getPropertyID(propertyID).getRental(rentalID).checkApplication(contractPrice,contractLength);
        } catch (UnableToApplyException e) {
            System.out.println("unable to apply: your contract length and price should be greater than the min price and length!");
            System.out.println("unable to apply: the status of property should be assigned!");
        } catch (WrongIdException e) {
            e.printStackTrace();
        }

        Application ap = new Application(renterName,rentalID,occupation,income,contractLength,contractPrice);
        applicationList.add(ap);
    }

    public Property getPropertyID(String propertyID) {
        Property property = null;

        for(int i=0; i<pr.size(); i++) {
            if(pr.get(i).getId().equals(propertyID))
                property = pr.get(i);
        }
            return property;
    }



    private void getRecommendations (Customers c) {
        System.out.println("--------- Here is your recommendations :)----------");
        String s = "there is no recommendation for u, try to add more suburb.";
        for(int i =0; i<pr.size();i++){
            for(int j = 0; j<c.getSuburbCodeList().length; j++){
                if (pr.get(i).getSuburbCode().equals(c.getSuburbCodeList()[j])){
                        s = pr.get(i).generateRecommendation();
                        System.out.println(s);
                        s = "null";
                    }
                }
            }
        if(!s.equals("null"))
            System.out.println(s);
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
                  System.out.println(keyCheck +"has been successfully added!");
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
        do{
            valid = false;
        try {
            BRCustomers.checkID(arr[0]);
            valid=true;
        } catch (InvalidIdException e) {
           System.out.println(e.toString());
           System.out.println("please re enter your propertyId, it should contain P and followed by digits");
           sc.next();
           arr[0]=input;
        }}while(!valid);
        // check if this property already exists in the system
        do{
            valid = propertyAlreadyExist(arr);
            if(valid) {}

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
           System.out.println("Property added! ");

           System.out.println("you have successful uploaded a house and started a rental");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format! Please type without whitespace.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //this function is used by landlord and vendor to upload property to the realEstate.

    public boolean propertyAlreadyExist(String arr[]) {
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


