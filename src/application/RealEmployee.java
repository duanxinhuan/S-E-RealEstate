package application;

import property.Property;

import java.sql.SQLException;
import java.util.ArrayList;

public class RealEmployee {


    private ArrayList<Property> pr ;
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



}
