package employees;

import property.ForSale;

public class SalesConsultant extends Employee {

	private double finalPrice;
	private boolean negotiation = true;
	private final double beforeNegCommissionRate = 0.05;
	private final double afterNegCommissionRate = 0.02;
	private double commission;
	private double salary;

	public SalesConsultant(String employeeId, String employeeStatus, String password, double salary) {
		super(employeeId, employeeStatus, password);
		this.salary = salary;
	}

	public void sellProperty() {

	}

	public boolean negotiateSale() {
		if(negotiation) {
			return true;
		}
		return false;
	}

	public double setCommission() {
		if (negotiateSale()) {
			this.commission = afterNegCommissionRate * getFinalPrice();
		}
		else{
			this.commission = beforeNegCommissionRate * getFinalPrice();
		}
		return commission;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	public double getSalary() {
		return salary;
	}

	public void calculateCommission() {

	}
	
	
}
