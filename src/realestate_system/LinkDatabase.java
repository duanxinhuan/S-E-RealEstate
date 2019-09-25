package realestate_system;

import property.ForSale;
import property.Property;
import property.Rental;
import realEstateException.WrongPassWordException;
import realEstateException.EmailDoesNotExistException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LinkDatabase {


    private static Connection connection = null;
    private static PreparedStatement preparedStmt;
    private static String query;
    ResultSet rs;





    public static Connection getConnection() {
        return connection;
    }

    public static void connectJDBCToAWSEC2() {

        System.out.println("----MySQL JDBC Connection Testing -------");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");


        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + "database-2.clfr5ittcqg1.ap-southeast-2.rds.amazonaws.com"
                            + ":" + 3306 + "/" + "realestate", "duan", "duan953280");
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        if (connection != null) {
            System.out.println("SUCCESS!!!!");
        } else {
            System.out.println("FAILURE! Failed to make connection!");
        }

    }

    public static void register(String passWord,String custName, String emailAddress ){
        try{
            query = " insert into customer (passWord, custName, emailAddress)"
                    + " values (?, ?, ?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, passWord);
            preparedStmt.setString (2, custName);
            preparedStmt.setString (3, emailAddress);
            preparedStmt.execute();
            // insert lines into database
            preparedStmt = connection.prepareStatement(
                    "select * from customer where  emailAddress =  ?");
            preparedStmt.setString(1,emailAddress);
            ResultSet rs=preparedStmt.executeQuery();
            while(rs.next()){
                System.out.printf("%-28s", "Customer Id: ");
                System.out.printf(rs.getInt(1)+ "\n");
                System.out.printf("%-28s", "Customer Name: ");
                System.out.printf(rs.getString(3)+ "\n");
                System.out.printf("%-28s", "Email address ");
                System.out.printf(rs.getString(4)+ "\n");
            }
        }catch(Exception e){ System.out.println(e);}

    }

    public static String logIn(String emailAddress, String passWord  ) throws SQLException, WrongPassWordException, EmailDoesNotExistException {
        String customer_details = null;
        ResultSet rs;

        // login from database

        preparedStmt = connection.prepareStatement("select * from customer where emailAddress = ?");
        preparedStmt.setString(1,emailAddress);
        rs = preparedStmt.executeQuery();


        while(rs.next()){
            if(rs.getString(2).equals(null) )
                throw new EmailDoesNotExistException();
            if(passWord.equals(rs.getString(2))){
                System.out.println("success！");
                customer_details = rs.getInt(1) + "_" + rs.getString(2) + "_"
                        +rs.getString(3) +"_" + rs.getString(4);
                break;
            }
            throw new WrongPassWordException();
        }

        return customer_details;
    }

    public static void uploadProperty(Property p) {
        try {
            query = "insert into property(propertyID, address, suburbCode,propertyType,bedrommNumber," +
                    " bathroomNumber,carspaceNumber,CustomerId)" + "values(?, ?, ?, ?, ?, ?, ?,?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, p.getId());
            preparedStmt.setString(2, p.getAddress());
            preparedStmt.setString(3, p.getSuburbCode());
            preparedStmt.setString(4, p.getPropertyType());
            preparedStmt.setInt(5, p.getNumOfBedroom());
            preparedStmt.setInt(6, p.getNumOfBath());
            preparedStmt.setInt(7, p.getNumOfCarSpace());
            preparedStmt.setInt(8, p.getOwnerID());
            preparedStmt.execute();
            for(int i = 0; i< p.getRentals().size(); i++){
                Rental r = p.getRentals().get(i);
                query = "insert into rental(RentalId, propertyId,propertyStatus,weeklyRent,contractLength,employeeId)" +
                        "values(?,?,?,?,?,?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1,r.getRentalId());
                preparedStmt.setString(2,p.getId());
                preparedStmt.setString(3,r.getStatus());
                preparedStmt.setDouble(4,r.getWeeklyRent());
                preparedStmt.setDouble(5,r.getContractLength());
                preparedStmt.setString(6,r.getAssignedEmployee());
                preparedStmt.execute();
            }

            for(int i = 0; i< p.getForSales().size(); i++){
                ForSale f = p.getForSales().get(i);
                query = "insert into rental(RentalId, propertyId,propertyStatus,weeklyRent,contractLength,employeeId)" +
                        "values(?,?,?,?,?,?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1,f.getSaleId());
                preparedStmt.setString(2,p.getId());
                preparedStmt.setString(3,f.getStatus());
                preparedStmt.setDouble(4,f.getMinPrice());
                preparedStmt.setDouble(5,f.getCommissionRate());
                preparedStmt.setString(6,f.getAssignedEmployee());
                preparedStmt.execute();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Property> loadProperty() throws SQLException {
        ArrayList<Property> pr = new ArrayList<>();
        Property p = null;
        query = "select * from property";
        preparedStmt = connection.prepareStatement(query);
        ResultSet rs=preparedStmt.executeQuery();
        while(rs.next()){
            String propertyId = rs.getString(1);
            p = new Property(propertyId,rs.getString(2),rs.getString(3),rs.getString(4)
            , rs.getInt(5),rs.getInt(6),rs.getInt(7));
            p.setOwnerID(rs.getInt(8));
            query = "select * from rental where propertyId = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, propertyId);
            ResultSet r1 = preparedStmt.executeQuery();
            while(r1.next()){
                Rental rental = new Rental(r1.getString(1),r1.getString(3),r1.getDouble(4)
                , r1.getDouble(5), r1.getDouble(6),r1.getString(7));
                p.addRental(rental);
            }
            query = "select * from rental where propertyId = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, propertyId);
            r1 = preparedStmt.executeQuery();
            while(r1.next()){
                ForSale forsale = new ForSale(r1.getString(1),r1.getString(2),r1.getDouble(3)
                        , r1.getDouble(4),r1.getString(5));
            }

            pr.add(p);

        }
        return pr;
    }

    public static void showUnassigned(String s) throws SQLException {
        String details =null;
        if(s.equals("rental")){
            query = "select RentalId, propertyId, weeklyRent,contractLength, from rental";
            preparedStmt=connection.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            System.out.println("--------unassigned rentals--------");
            while(rs.next()){
                int i =1;
                System.out.println(i + "." + "rentalId:" + rs.getString(1)
                +"|propertyId:" + rs.getString(2)
                +"|weeklyRent:" + rs.getDouble(3)
                +"|contractLength:" + rs.getDouble(4));
                i++;
            }
        }
    }
}
