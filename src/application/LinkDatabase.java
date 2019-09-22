package application;

import customer.Customers;
import property.Property;
import property.Rental;

import java.sql.*;

public class LinkDatabase {


    private static Connection connection = null;
    private static PreparedStatement preparedStmt;
    private static String query;


    public LinkDatabase() throws SQLException {
    }

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

    public static String logIn(String emailAddress, String passWord  ) throws SQLException {
        String customer_details = null;
        boolean isEmailExist = true;
        query = "select emailAddress from customer";
        preparedStmt = connection.prepareStatement(query);
        ResultSet rs=preparedStmt.executeQuery();
        while(rs.next()){
            if(emailAddress.equals(rs.getString(1))){
                isEmailExist = false;
                break;
            }
        }
        if(isEmailExist)
            System.out.println("email doesn't exists");

        // login from database

        preparedStmt = connection.prepareStatement("select * from customer where emailAddress = ?");
        preparedStmt.setString(1,emailAddress);
        rs = preparedStmt.executeQuery();
        while(rs.next()){
            if(passWord.equals(rs.getString(2))){
                System.out.println("success！");
                customer_details = rs.getInt(1) + "_" + rs.getString(2) + "_"
                        +rs.getString(3) +"_" + rs.getString(4);
                break;
            }
         System.out.println("Wrong pass world");
        }

        return customer_details;
    }

    public static void uploadProperty(Property p, Customers c) {
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
            preparedStmt.setInt(8, Integer.parseInt(c.getCustomerId()));
            preparedStmt.execute();
            for(int i = 0; i< p.getRentals().size(); i++){
                Rental r = p.getRentals().get(i);
                query = "insert into rental(RentalId, propertyId,propertyStatus,weeklyRent,contractLength)" +
                        "values(?,?,?,?,?)";
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1,r.getRentalId());
                preparedStmt.setString(2,p.getId());
                preparedStmt.setString(3,r.getStatus());
                preparedStmt.setDouble(4,r.getWeeklyRent());
                preparedStmt.setDouble(5,r.getContractLength());
                preparedStmt.execute();
            }

            for(int i = 0; i< p.getRentals().size(); i++){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
}
