package employees;

import property.ForSale;

public class SalesConsultant extends Employee {

	private double finalPrice;
	private boolean negotiation = true;
	private final double beforeNegCommissionRate = 0.05;
	private final double afterNegCommissionRate = 0.02;
	private double commission;

	public SalesConsultant(String employeeId, String employeeStatus, String password ) {
		super(employeeId, employeeStatus, password );
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

	public  void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void calculateCommission() {

	}
}
