package employees;

import property.Rental;

public class BranchAdmin extends Employee {
	
	private double employeePayment;
	private double landlordPayment;
	
	public BranchAdmin(String employeeId, String passWord, String name ) {
		super(employeeId,passWord,name);
		
	}

	public void payEmployee(Employee employee) {
		if (employee.getClass().equals("class employees.SalesConsultant")) {
			
			// Pay a Sales Consultant based on salary & commision.
			SalesConsultant salesCon = (SalesConsultant) employee; // casting employee
			employeePayment = salesCon.getSalary() + salesCon.setCommission(); //but need to add commision.
			System.out.println("The Sales Consultant has been payed " + employeePayment + " this week!");
			
		} else {
			// Pay an other employee based on hourly rate
			employeePayment = employee.getHourlyRate() * employee.getHours();
			System.out.println("The Employee has been payed " + employeePayment + " this week!");
		}
	}

	public void payLandLord(Rental rental) {
		// Pay the landlord associated with the rental id of this rental object parameter
		landlordPayment = rental.getWeeklyRent() - rental.getManagementFee();
		System.out.println("The Lanlord has been payed " + landlordPayment + " this week!");
	}
}

