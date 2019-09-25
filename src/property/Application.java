package property;

import customer.Renter;
import java.util.*;

public class Application implements Applicant {

    private String applicationName;
    private String rentalID;
    private String occupation;
    private double income;
    private double contractLength;
    private double contractPrice;
    private String status;


    public Application(String applicationName, String rentalID, String occupation, double income, double contractLength, double contractPrice) {
            this.applicationName = applicationName;
            this.rentalID = rentalID;
            this.occupation = occupation;
            this.income = income;
            this.contractLength = contractLength;
            this.contractPrice = contractPrice;
    }

    public String getApplicationNameName() {
        return applicationName;
    }

    public void setApplicationName(Renter name) {
        this.setApplicationName(name);
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(Rental rentalID) {
        this.setRentalID(rentalID);
    }

    public String getOccupation() {
        return occupation;
    }

    public double getIncome() {
        return income;
    }

    public double getContractLength() {
        return contractLength;
    }

    public double getContractPrice() {
        return contractPrice;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void reject() {

    }

    @Override
    public void accept() {

    }

}
