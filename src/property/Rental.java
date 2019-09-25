package property;

import customer.Landlord;
import employees.Employee;
import realEstateException.CanNotRecommendException;
import realEstateException.HouseAlreadyAssignedException;
import realEstateException.UnableToApplyException;

public class Rental {

	private String RentalId;
	private String Status = "W";
	// "W" is waiting for assign; "A" means assigned, and wait for rent; "R" means rental finished;
	private double weeklyRent;
    private double contractLength;
    private double managementFee;
    private String assignedEmployee;
	private boolean negotiate;


    public Rental( double weeklyRent, double contractLength) {
    	this.weeklyRent = weeklyRent;
    	this.contractLength = contractLength;
    }

	public boolean isNegotiate() {
		return negotiate;
	}

	public String getAssignedEmployee() {
		return assignedEmployee;
	}

	public void assign(Employee emp) throws HouseAlreadyAssignedException {
    	if(Status.equals("W")){
		    this.Status = "A";
		    this.assignedEmployee = emp.getEmployeeId();
    	}

    	else
    		throw new HouseAlreadyAssignedException();
	}

	public Rental(String rentalId, String status, double weeklyRent, double contractLength, double managementFee,
				  String assignedEmployee, boolean negotiate) {
		RentalId = rentalId;
		Status = status;
		this.weeklyRent = weeklyRent;
		this.contractLength = contractLength;
		this.managementFee = managementFee;
		this.assignedEmployee = assignedEmployee;
		this.negotiate = negotiate;
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
    	if (isNegotiate()) {
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

	public void negotiate(boolean b) {
    	this.negotiate = b;
	}

	public boolean getNegotiate() {
    	return this.negotiate;
	}

    public String getDetails(String s1) {
    	String s ="Property Id: " + s1 +
				"\nWeekly rent: " + getWeeklyRent()
				+"\nminimum length:" +getContractLength();
    	return s;
    }

	public String generateRecommendation(String s1) throws CanNotRecommendException {
		if(!getStatus().equals("A"))
			throw new CanNotRecommendException();
		return getDetails(s1);
	}

	public void checkApplication(double weeklyRent, double contractLength) throws UnableToApplyException {
    	if(weeklyRent<this.weeklyRent && contractLength<this.contractLength && !Status.equals("A")){
    		throw new UnableToApplyException();
	}
    }
	// change the status when the house is assigned and rented
}
