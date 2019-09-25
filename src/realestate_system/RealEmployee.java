package realestate_system;

import employees.Employee;
import employees.PropertyManager;
import property.Property;
import realEstateException.HouseAlreadyAssignedException;
import realEstateException.WrongIdException;

import java.sql.SQLException;
import java.util.*;


public class RealEmployee {


    private ArrayList<Property> pr ;
    int option;
    String input;
    Employee current_employee;
    Scanner sc = new Scanner(System.in);
    // this is used to initialize property.


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
        +"\n1.BranchAdmin\n" + "\n2.BranchManager\n" + "\n3.PropertyManager\n" + "\n4.SalesConsultant\n");
        option = sc.nextInt();
        switch (option){
            case 3:
                propertyManager();
                break;
        }
    }


    public void loadProperty(){
        try {
            pr=LinkDatabase.loadProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void propertyManager(){
        current_employee = new PropertyManager("S123", "1234R","kkk");
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

    public void assignRental(String rentalId){
        String[] str = rentalId.split("_");
        String propertyId = str[0];
        for(int i=0; i<pr.size();i++){
            if(pr.get(i).getId().equals(propertyId)){
                try {
                    pr.get(i).assign(current_employee,rentalId);
                } catch (HouseAlreadyAssignedException e) {
                    System.out.println(e.toString());
                    break;
                }
                System.out.println("Have u negotiate with the customer? if so, input  yes, else press enter");
                input = sc.next();
                if(input.equals("yes"))
                    pr.get(i).negotiate(rentalId,true);
                else
                    pr.get(i).negotiate(rentalId,false);


                try {
                    LinkDatabase.assignRental(pr.get(i).getRental(rentalId),current_employee);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (WrongIdException e) {
                    e.printStackTrace();
                }
                System.out.println("you have successfully assigned the rental!\n" + "Rental id: " +rentalId +"\n" + "property id:" + propertyId);

            }
        }
    }
    public ArrayList<Property> getPr() {
        return pr;
    }

}
