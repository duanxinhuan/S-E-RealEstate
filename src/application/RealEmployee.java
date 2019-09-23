package application;

import property.Property;

import java.sql.SQLException;
import java.util.*;


public class RealEmployee {


    private ArrayList<Property> pr ;
    int option;
    private ArrayList<String> assignedRentalList;
    Scanner sc = new Scanner(System.in);
    // this is used to initialize property.

    public RealEmployee() {
    }

    public ArrayList<Property> getPr() {
        return pr;
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


}
