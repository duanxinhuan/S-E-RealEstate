package property;

import customer.Landlord;

public class Rental {

	private String Status = "W";
	// "W" is waiting for assign; "A" means assigned, and wait for rent; "R" means rental finished;
	private double weeklyRent;
    private double contractLength;
    private double managementFee;


    public Rental( double weeklyRent, double contractLength) {

    	this.weeklyRent = weeklyRent;
    	this.contractLength = contractLength;
    }

    public void setManagementFee(Landlord landlord) {
    	if (landlord.getNumOfRent() >= 2) {
    		this.managementFee = ((7.0/100.00) * weeklyRent);
    	} else {
    		// the standard rental fee is 8%
    		this.managementFee = ((8.0/100.00) * weeklyRent);
    	}
    	if (landlord.getNegotiate() == true) {
        	this.managementFee -= (1.0/100.0);
    	}
    }
    
    public double getWeeklyRent() {
    	return this.weeklyRent;
    }
    
    public double getContractLength() {
    	return this.contractLength;
    }
    
    public double getManagementFee() {
    	return this.managementFee;
    }
    
}
