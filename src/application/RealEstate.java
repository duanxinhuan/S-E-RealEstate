package application;

import java.util.*;

import customer.*;
import property.Property;

import realEstateException.PreExistException;
//import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.util.Iterator;
import java.util.Scanner;

//import static java.lang.System.exit;

public class RealEstate {
    private HashMap<Integer, String> suburb_list = new HashMap<Integer, String>();
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

    private void login() {
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
                     landlordLogin();
                     break;
                 case 4:
                     System.out.println("you chose landlord!");
                     current_customer = new Landlord(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                     vendorLogin();
                     break;

             }
        
    }

    public void buyerLogin(){
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num){
            case 1:addSuburb();
            case 2:System.exit(0);
        }
    }

    private void vendorLogin() {
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num){
            case 1:
                addSuburb();
            case 2:
                System.exit(0);
        }

    }

    private void landlordLogin() {
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num) {
            case 1:
                addSuburb();
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



    private void addSuburb() {
        System.out.println(suburb_list);
        String keyCheck = sc.next();
       boolean isKeyPresent = suburb_list.containsKey(keyCheck);
          if (isKeyPresent == true) {
        	  System.out.println("suburb added");
              current_customer.addSuburb(keyCheck);
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
                suburb_list.put(Integer.parseInt(item[0]),item[1]);

            }
            reader.close();
            System.out.println("Stream Closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            System.out.println("enter your email address,email address must contain @ and.com");
            emailAddress = sc.next();
        }
        while (!emailAddress.contains("@") || !emailAddress.contains(".com"));
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
    	
    	if(id.matches("[^a-zA-Z0-9]"))
    		System.out.println("Invalid input!!!");
    	return id.matches("[^a-zA-Z0-9]");
    }
    
    public void propertyAlreadyExist(String arr[]) {
    	PreExistException ex = new PreExistException();
    	
    	try {
    		for(int i=0; i<pr.size(); i++) {
	        	if(pr.get(i).getId().equals(arr[0])) {
	        		throw ex;
	        	}
	        }
        	pr.add(new Property(arr[0],arr[1],arr[2],arr[3],Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6])));

    	}
    	catch(Exception e) {
    		System.out.println(ex.toString());
    	}	 
    }
    
    
    public void uploadProperty() {  	
    	String[] arr;
    	
    	System.out.println("Please enter the property details in this order: "
    			+ "id, address, suburb code, property type, bedroom number, bathroom number, car space number");
    	
    	while(sc.hasNext()) {
	    	sc.useDelimiter("\n");
	    	String input = sc.next();
	    	arr = input.split(",", 7);
	    	
	    	// check ID
	    	checkID(arr[0]);
	    	
	    	// check if this property already exists in the system
	    	propertyAlreadyExist(arr);
    	}   
    }
}


