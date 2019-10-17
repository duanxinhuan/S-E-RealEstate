package employees;

import property.ForSale;

public class SalesConsultant extends Employee {

	private static double finalPrice;


	public SalesConsultant(String employeeId, String employeeStatus, String password ) {
		super(employeeId, employeeStatus, password );
	}

	public void sellProperty() {

	}

	public void negotiateSale() {

	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public static double setFinalPrice(double finalPrice) {
		return finalPrice;
	}

	public void calculateCommission() {

	}
}
