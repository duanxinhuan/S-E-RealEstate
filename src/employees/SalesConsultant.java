package employees;

import property.ForSale;

public class SalesConsultant extends Employee {

	private double finalPrice;


	public SalesConsultant(String employeeId, String employeeStatus,String password ) {
		super(employeeId, employeeStatus,password );
	}

	public void sellProperty() {

	}

	public void negotiateSale() {

	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void calculateCommission() {

	}
}
