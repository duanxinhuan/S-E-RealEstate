package realestate_system;

import employees.BranchAdmin;
import employees.BranchManager;
import employees.Employee;
import employees.PropertyManager;
import employees.SalesConsultant;
import property.Property;
import realEstateException.HouseAlreadyAssignedException;
import realEstateException.WrongIdException;

import java.sql.SQLException;
import java.util.*;


public class RealEmployee {
    private ArrayList<Property> pr ;
    private int option;
    private String input;
    private Employee current_employee;
    private Scanner sc = new Scanner(System.in);
    private SalesConsultant salesConsultant;
    private BranchManager branchManager;
    private BranchAdmin branchAdmin;

    // this is used to initialize property.

    public void loadProperty(){
        try {
            pr=LinkDatabase.loadProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startRealEmployee(){
        LinkDatabase.connectJDBCToAWSEC2();
        loadProperty();
        System.out.println("Loading properties");
        try {
            pr = LinkDatabase.loadProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("what kind of employee are you:"
        +"\n1.Branch Admin\n" + "\n2.Branch Manager\n" + "\n3.Property Manager\n" + "\n4.Sales Consultant\n");
        option = sc.nextInt();

        switch (option){
            case 1:
                branchAdmin();
                break;
            case 2:
                branchManager();
                break;
            case 3:
                propertyManager();
                break;
            case 4:
                salesConsultant();
                break;
            case 5:
                System.out.println("GoodBye!");
                System.exit(0);
        }
    }

    private void salesConsultant() {
        do{
            System.out.println( "1.sell a property" + "\n2.negotiate sales commission" + "\n3.calculate sales commission" +
                    "\n4.employee main menu" + "\n5.main menu" + "\n6.exit");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    salesConsultant.sellProperty();
                    break;
                case 2:
                    salesConsultant.negotiateSale();
                    break;
                case 3:
                    salesConsultant.setCommission();
                case 4:
                    startRealEmployee();
                    break;
                case 5:
                    RealEstateMain.runApp();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }while (option!=6);
    }

    private void branchManager() {
        do{
            System.out.println( "1.approve hours" + "\n2.assign employee" + "\n3.employee main menu" +
                    "\n4.main menu" + "\n5.exit");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    branchManager.approveHours();
                    break;
                case 2:
                    branchManager.assignEmployee();
                    break;
                case 3:
                    startRealEmployee();
                    break;
                case 4:
                    RealEstateMain.runApp();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }while (option!=5);
    }

    private void branchAdmin() {
        do{
            System.out.println( "1.run the payroll" + "\n2.employee main menu" +
                    "\n3.main menu" + "\n4.exit");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    runPayroll();
                    break;
                case 2:
                    startRealEmployee();
                    break;
                case 3:
                    RealEstateMain.runApp();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }while (option!=4);
    }

    private void runPayroll() {
        System.out.println("1.pay landlord" + "\n2.pay employee" +
                "\n3.employee main menu" + "\n4.main menu" + "\n5.exit");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                payLandLord();
                break;
            case 2:
                payEmployee();
                break;
            case 3:
                startRealEmployee();
                break;
            case 4:
                RealEstateMain.runApp();
                break;
            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
        }
    }

    private void payLandLord() {
    	// BranchAdmin branchAdmin = new BranchAdmin("E123", "1234R","kkk"); // this should be done in employee selection menu?
    	// stally this function below needs to point to the rental property that the landlord owns
    	branchAdmin.payLandLord(rental); // this needs to change to a selected rental property
    }

    private void payEmployee() {
    	// BranchAdmin branchAdmin = new BranchAdmin("E123", "1234R","kkk"); // this should be done in employee selection menu?
    	// stally this function needs to select the employee in the database we want to pay,
    	// & not the current employee (which is always the branch admin in this case)
    	branchAdmin.payEmployee(employee); // this needs to change to a selected employee
    }

    private void propertyManager(){
        current_employee = new PropertyManager("E123", "1234R","kkk"); // this should be done in employee selection menu?
        do{
            System.out.println( "1.assign a rental house" +"\n7.exit");
            option = sc.nextInt();
            switch (option){
                case 1:
                    try {
                        assignHouse();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    break;
            }

        }while(option != 7);
    }

    private void assignHouse() throws SQLException {
        LinkDatabase.showUnassigned("rental");
        System.out.println("input the rental id to assign a rental");
        input = sc.next();
        assignRental(input);
    }

    private void assignRental(String rentalId){
        String[] str = rentalId.split("_");
        String propertyId = str[0];
        for (Property property : pr) {
            if (property.getId().equals(propertyId)) {
                try {
                    property.assign(current_employee, rentalId);
                } catch (HouseAlreadyAssignedException e) {
                    System.out.println(e.toString());
                    break;
                }
                System.out.println("Have u negotiate with the customer? if so, input  yes, else press enter");
                input = sc.next();
                if (input.equals("yes"))
                    property.negotiate(rentalId, true);
                else
                    property.negotiate(rentalId, false);

                try {
                    LinkDatabase.assignRental(property.getRental(rentalId), current_employee);
                } catch (SQLException | WrongIdException e) {
                    e.printStackTrace();
                }
                System.out.println("you have successfully assigned the rental!\n" + "Rental id: " + rentalId + "\n" + "property id:" + propertyId);

            }
        }
    }
    public ArrayList<Property> getPr() {
        return pr;
    }

}
