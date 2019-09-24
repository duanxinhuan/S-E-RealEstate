package employees;

import java.util.HashMap;
public class Employee {
	private final String employeeId;
	private String passWord;
	private String name;
	private  Double hourlyRate = 20.5;
	private  Double hours;
	private boolean isPartTime = false;


	public Employee(String employeeId, String passWord, String name) {
		this.employeeId = employeeId;
		this.passWord = passWord;
		this.name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public Double getHourlyRate() {
		return hourlyRate;
	}

	public Double getHours() {
		return hours;
	}

    public void addRental() {
    }
}

