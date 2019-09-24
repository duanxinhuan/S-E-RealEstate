package realestate_system;

import employees.Employee;
import property.Property;

import java.sql.SQLException;
import java.util.*;


public class RealEmployee {


    private ArrayList<Property> pr ;
    int option;
    Employee current_employee;
    Scanner sc = new Scanner(System.in);
    // this is used to initialize property.

    public RealEmployee() {
    }

    public void startRealEmployee(){
        LinkDatabase.connectJDBCToAWSEC2();
        System.out.println("Loading properties");
        try {
            pr = LinkDatabase.loadProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("what kind of employee is you:"
        +"\n1.BranchAdmin" + "\n2.BranchManager" + "\n3.PropertyManager" + "\nSalesConsultant");
        switch (option){
            case 3:
                propertyManager();
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
        do{
            System.out.println( "1.assign a rental house" +"\n7.exit");
            option = sc.nextInt();
            switch (option){
                case 1:
                    assignHouse();
                    break;
                case 2:
            }

        }while(option != 7);
    }

    private void assignHouse() {
    }


    public ArrayList<Property> getPr() {
        return pr;
    }

}
