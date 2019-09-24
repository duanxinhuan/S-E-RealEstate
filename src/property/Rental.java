package property;

import customer.Landlord;

public class Rental {

	private String RentalId;
	private String Status = "W";
	// "W" is waiting for assign; "A" means assigned, and wait for rent; "R" means rental finished;
	private double weeklyRent;
    private double contractLength;
    private double managementFee;
    private String assignedEmployee;


    public Rental( double weeklyRent, double contractLength) {
    	this.weeklyRent = weeklyRent;
    	this.contractLength = contractLength;
    }

	public String getAssignedEmployee() {
		return assignedEmployee;
	}

	public void setAssignedEmployee(String assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}

	public Rental(String rentalId, String status, double weeklyRent, double contractLength, double managementFee) {
		RentalId = rentalId;
		Status = status;
		this.weeklyRent = weeklyRent;
		this.contractLength = contractLength;
		this.managementFee = managementFee;
	}

	public void setRentalId(String rentalId) {
		this.RentalId = rentalId;
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
    // set management fee.
    
    public double getWeeklyRent() {
    	return this.weeklyRent;
    }
    
    public double getContractLength() {
    	return this.contractLength;
    }
    
    public double getManagementFee() {
    	return this.managementFee;
    }

	public String getRentalId() {
		return RentalId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
    // change the status when the house is assigned and rented
}
