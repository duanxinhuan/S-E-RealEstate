package application;

import customer.*;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.exit;

public class RealEstate<num> {
    private HashMap<Integer, String> suburb_list = new HashMap<Integer, String>();
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
            
        }}while(choice !=3);


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
                        buyer();
                        break;

                    case 2:
                        System.out.println("you chose renter!");
                        current_customer = new Renter(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                        renter();
                        break;
                    case 3:
                        System.out.println("you chose vendor!");
                        current_customer = new Vendor(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                        leaser();
                        break;
                    case 4:
                        System.out.println("you chose landlord!");
                        current_customer = new Landlord(cust_array[0],cust_array[1], cust_array[2],cust_array[3]);
                        vendor();
                        break;

                }
                }

    public void buyer(){
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num){
            case 1:addSuburb();
            case 2:System.exit(0);
        }
    }

    private void vendor() {
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num){
            case 1:
                addSuburb();
            case 2:
                System.exit(0);
        }

    }

    private void leaser() {
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num) {
            case 1:
                sell();
            case 2:
                System.exit(0);
        }
    }




    public void renter() {
        System.out.println("enter 1 for add suburb, 2 for exit the program");
        int num=sc.nextInt();
        switch(num) {
            case 1:
                rent();
            case 2:
                System.exit(0);
        }
    }

    private void sell() {
    }

    private void rent() {
    }

    // login function

    //this is connect class, this is used to connect to aws server where the database is built.



    private void addSuburb() {
        System.out.println(suburb_list);
        int keyCheck = sc.nextInt();
       boolean isKeyPresent = suburb_list.containsKey(keyCheck);
          if (isKeyPresent == true)
          {System.out.println("suburb added");
              current_customer.addSuburb();}
          else {System.out.println("suburb doesn't exist");}
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
        Scanner scan = new Scanner(System.in);
        System.out.println("enter your customer name");
        custName = scan.next();

        do {
            System.out.println("enter your email address,email address must contain @ and.com");
            emailAddress = scan.next();
        }
        while (!emailAddress.contains("@") || !emailAddress.contains(".com"));
        System.out.println("create your password");
        passWord = scan.next();
        System.out.println("confirm your password");
        passWord2 = scan.next();
        while (!passWord.equals(passWord2)) {
            System.out.println("password mismatch ");
            System.out.println("enter your password again");
            passWord = scan.next();
            System.out.println("confirm your password");
            passWord2 = scan.next();
        }
        System.out.println("register successfully");
        System.out.println("here is your account details: ");
        System.out.println("password length: " + (passWord.length()));
        
        LinkDatabase.register(passWord, custName, emailAddress);
    }

}


