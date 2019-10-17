package customer;


import employees.SalesConsultant;
import property.Offer;
import property.Property;
import util.DateTime;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Buyer extends BRCustomers {

    private final double Down_Payment_Percentage = 0.1;
    private ArrayList<Offer> offerList;
    private Property pr;
    private Scanner sc = new Scanner(System.in);
    private Offer offer;

    public Buyer(String customerId, String passWord, String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public double getDown_Payment_Percentage() {
        return Down_Payment_Percentage;
    }

    // buyer can make an offer
    public void makeOffer() {
        System.out.println("Please enter your name:");
        String buyerName = sc.next();

        System.out.println("Please enter the price you wish to offer:");
        double initialOfferPrice = sc.nextDouble();

        System.out.println("Please enter your offer Id:");
        String offerId = sc.next();

        String[] arr1 = offerId.split("_");
        String propertyId = arr1[0];
        checkPropertyID(propertyId);

        System.out.println("Please enter the date you make this offer:");
        String readDate = sc.next();
        String[] arr2 = readDate.split("/", 3);
        DateTime offerMakeDate = new DateTime(Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]),
                Integer.parseInt(arr2[2]));
        checkOfferMakeDate(readDate);

        String offerStatus = offer.setWaitingResponseStatus();

        Offer offer = new Offer(buyerName, initialOfferPrice, offerId, offerMakeDate, offerStatus);
        offerList.add(offer);
    }

    private boolean checkPropertyID(String propertyId) {
        ArrayList<Property> pr = new ArrayList<>();

        for (Property property : pr) {
            if (propertyId.equals(property.getId())) {
                return true;
            }
        }
        return false;
    }

    // use regex to check the validity of the date of offer being made
    private void checkOfferMakeDate(String date) {
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean b = matcher.matches();

        if(!b) {
            System.out.println("Invalid input!");
        }
    }

    // buyer can withdraw an offer
    public void withdrawOffer() {
        System.out.println("Please enter the offer Id you wish to withdraw:");
        String offerId = sc.next();

        System.out.println("Please enter the date you make this offer:");
        String readDate1 = sc.next();
        String[] arr1 = readDate1.split("/", 3);
        DateTime offerMakeDate = new DateTime(Integer.parseInt(arr1[0]), Integer.parseInt(arr1[1]),
                Integer.parseInt(arr1[2]));

        System.out.println("Please enter the date you wish to withdraw:");
        String readDate2 = sc.next();
        String[] arr2 = readDate2.split("/", 3);
        DateTime withdrawOfferDate = new DateTime(Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]),
                Integer.parseInt(arr2[2]));

        int dateDifference = DateTime.diffDays(offerMakeDate,withdrawOfferDate);

        for(int i=0; i<offerList.size(); i++) {
            if(offerList.get(i).getOfferId().equals(offerId)) {
                if(dateDifference < Vendor.getCoolingPeriod()) {
                    offerList.remove(i);
                }else {
                    System.out.println("Unable to withdraw.");
                }
            }else {
                System.out.println("This offer id does not exist.");
            }
        }
    }

    // buyer can make down payment
    public void makeDownPayment() {
        ArrayList<Offer> of = new ArrayList<>();

        System.out.println("Please enter the offer Id:");
        String offerId = sc.next();

        System.out.println("Please re-enter the price you wish to pay:");
        double finalPrice = sc.nextDouble();

        for(int i=0; i<of.size(); i++) {
            if(of.get(i).getOfferId().equals(offerId) && of.get(i).getOfferStatus().equals("accepted")) {
                double downPayement = SalesConsultant.setFinalPrice(finalPrice) * getDown_Payment_Percentage();
            }
            else
                System.out.println("Offer Id does not exist!");
        }

    }

}
