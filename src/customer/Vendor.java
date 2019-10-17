package customer;

import java.time.LocalDateTime;
import java.util.Scanner;

import property.Offer;
import property.Property;
import util.DateTime;


public class Vendor extends VLCustomers {
    private static long time;
    //    private String[] statusOfOffer;
    private static long ConsiderationTime;
    private double minPrice;
    private LocalDateTime currentDate;
    private String offerDate;
    private Object Vendor;
    private Scanner sc = new Scanner(System.in);
    private static final int coolingPeriod = 3;
    private String offerStatus;
    private Offer offer;

    public Vendor(String customerId, String passWord, String name, String emailAddress) {
        super (customerId, passWord, name, emailAddress);
    }

    public void acceptOffer(DateTime offerStartDate, DateTime currentDate) {
        if(dateInput() < 0) {
            offer.setAcceptStatus();
            System.out.println("This offer has been accepted!");
        }
    }

    public void rejectOffer(DateTime offerStartDate, DateTime currentDate) {
        if(dateInput() < 0){
            offer.setRejectedStatus();
            System.out.println("This offer has been rejected!");
        }
    }

    public void autoRejectOffer(DateTime offerStartDate, DateTime currentDate){
        if(dateInput() > 0){
            offer.setRejectedStatus();
            System.out.println("This offer has been automatically rejected!");
        }
    }

    private int dateInput() {
        DateTime offerStartDate;
        DateTime currentDate;

        System.out.println("enter the date you make offer: ");
        String readDate1 = sc.next();
        String[] arr1 = readDate1.split("/", 3);
        offerStartDate = new DateTime(Integer.parseInt(arr1[0], Integer.parseInt(arr1[1],
                Integer.parseInt(arr1[2]))));

        System.out.println("enter the current date: ");
        String readDate2 = sc.next();
        String[] arr2 = readDate1.split("/", 3);
        currentDate = new DateTime(Integer.parseInt(arr2[0], Integer.parseInt(arr2[1],
                Integer.parseInt(arr2[2]))));

        int numOfDate = getCoolingPeriod() - DateTime.diffDays(currentDate, offerStartDate);
        return numOfDate;
    }

    public static int getCoolingPeriod() {
        return coolingPeriod;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

}
