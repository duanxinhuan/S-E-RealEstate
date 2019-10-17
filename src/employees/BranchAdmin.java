package employees;

import customer.Landlord;

public class BranchAdmin extends Employee {
	
	private double employeePayment;
	
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

	public void payLandLord(Landlord landlord) {
		
	}
}

