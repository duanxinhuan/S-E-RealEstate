package realestate_system;

import customer.*;
import property.Application;
import property.Property;
import realEstateException.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class RealEstate {
    private HashMap<String, String> suburb_list = new HashMap<>();
    private ArrayList<Property> pr = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Application> applicationList;
    Customers current_customer;
    private int choice;



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
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
            }
            else {
                String error = sc.next();
                choice = 0;
                System.out.println("Invalid input!!!");
            }

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

    private void login() {

        String emailAddress;
        String password;
        String customer_details;
        String [] cust_array;

        while(true){
            System.out.println("***login in***");
            System.out.println("Enter your email");
            emailAddress = sc.next();
            System.out.println("Enter your password");
            password = sc.next();
            try {
                customer_details = LinkDatabase.logIn(emailAddress, password);
                break;
            } catch (SQLException | WrongPassWordException | EmailDoesNotExistException e) {
                System.out.println(e.toString());
            }
        }


        cust_array = customer_details.split("_");

        //choose which customer
        System.out.println("choose if you are 1:buyer,2:renter,3:vendor,4:landlord");
        int choice = 0;
        if(sc.hasNextInt()) {
            choice = sc.nextInt();
        }
        else {
            String error = sc.next();
            System.out.println(error + " is invalid input!!!");
        }
        switch (choice) {
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

    private void register() {
        String custName;
        String emailAddress;
        String passWord;
        String passWord2;
        String[] arr;
        boolean valid;
        System.out.println("enter your information to register");

        // check the format of customer name
        do {
            valid = false;
            System.out.println("enter your name");
            sc.useDelimiter("\n");
            custName = sc.next();

            try {
                Customers.checkCustName(custName);
                valid = true;
            } catch (WrongCustomerNameFormatException e) {
                System.out.println(e.toString());
            }
        }
        while (!valid);

        // check the format of email address
        do {
            valid = false;
            System.out.println("enter your email address");
            emailAddress = sc.next();
            try {
                Customers.checkEmailFormat(emailAddress);
                valid = true;
            } catch (WrongEmailFormatException e) {
                System.out.println(e.toString());
            }
        }
        while (!valid);

        // check the consistency of password
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

    private void buyerLogin(){

        do{
            System.out.println("enter 1 for add suburb, 2 for exit the program");
            int num=sc.nextInt();
            switch(num){
                case 1:
                    addSuburb();
                    break;
                case 2:
                    RealEstateMain.runApp();
            }
        }while(true);
    }

    private void vendorLogin() {
        System.out.println("enter 1 for uploading a property, 2 for exit the program");
        int num=sc.nextInt();
        switch(num){
            case 1:
                uploadProperty();
            case 2:
                RealEstateMain.runApp();
        }

    }

    private void landlordLogin() {
        do{
            System.out.println("enter 1 for uploading a property, 2 for exit the program");
            int num=sc.nextInt();
            switch(num) {
                case 1:
                    uploadProperty();
                    break;
                case 2:
                    RealEstateMain.runApp();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + num);
            }
        }while(true);
    }

    private void renterLogin() {
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
                makeRentalApplication();
            case 4:
                System.exit(0);
        }
        }while(true);
    }

    // check if this property ID is in the system
    public boolean checkPropertyID(String propertyID) {

        for (Property property : pr) {
            if (propertyID.equals(property.getId())) {
                return true;
            }
        }
        return false;
    }

    private void makeRentalApplication() {
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
            System.out.println(e.toString());
        }

        Application ap = new Application(renterName,rentalID,occupation,income,contractLength,contractPrice);
        applicationList.add(ap);
    }

    private Property getPropertyID(String propertyID) {
        Property property = null;

        for (Property value : pr) {
            if (value.getId().equals(propertyID))
                property = value;
        }
            return property;
    }



    private void getRecommendations (Customers c) {
        System.out.println("--------- Here is your recommendations :)----------");
        String s = "there is no recommendation for u, try to add more suburb.";
        for (Property property : pr)
            for (int j = 0; j < c.getSuburbCodeList().length; j++) {
                if (property.getSuburbCode().equals(c.getSuburbCodeList()[j])) {
                    s = property.generateRecommendation();
                    System.out.println(s);
                    s = "null";
                }
            }
        if(!s.equals("null"))
            System.out.println(s);
    }
    // login function

    //this is add suburb function for buyer and renter and load suburb function of the realEstate

    private void addSuburb() {
        System.out.println(suburb_list);
        String keyCheck = sc.next();
       boolean isKeyPresent = suburb_list.containsKey(keyCheck);
        if (!isKeyPresent) {
            System.out.println("suburb doesn't exist");
        } else {
            System.out.println("suburb added");
            try {
                current_customer.addSuburb( keyCheck);
                System.out.println(keyCheck +"has been successfully added!");
            } catch (DuplicateSuburbException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadSuburb(){
        try{
            System.out.println("Loading suburb list...");
            BufferedReader reader = new BufferedReader(new FileReader("src//suburb.csv"));
            reader.readLine();
            String line;

            while((line=reader.readLine())!= null){
                String[] item = line.split(",",2);
                suburb_list.put(item[0],item[1]);

            }
            reader.close();
            System.out.println("Stream Closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void uploadProperty() {
        String[] arr;
        boolean valid =false;

        System.out.println("Please enter the property details in this order: "
                + "id, address, suburb code, property type, bedroom number, bathroom number, car space number");

        sc.useDelimiter("\n");
        String input = sc.next();
        arr = input.split(",", 7);
        // check ID
        do{
            try {
                VLCustomers.checkPropertyIDFormat(arr[0]);
                valid = true;
            } catch (InvalidPropertyIdFormatException e) {
               System.out.println(e.toString());
               System.out.println("please re-enter your property Id, it should contain P and followed by digits");
               sc.next();
               arr[0]=input;
            }
        }while(!valid);

        // check if this property already exists in the system
        do{
            valid = propertyAlreadyExist(arr);
            if(valid) {
                System.out.println("New property Id is valid.");
            }
            else{
                System.out.println("property Id already exists!");
                System.out.print("please enter a new Id:");
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
            System.out.println(e.toString());
        }
    }
    //this function is used by landlord and vendor to upload property to the realEstate.

    public boolean propertyAlreadyExist(String[] arr) {
        for (Property property : pr) {
            if (property.getId().equals(arr[0]))
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


