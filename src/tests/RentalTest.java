package tests;


import property.*;
import customer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {
	Landlord land1 = new Landlord("1", "2333", "peter", "peter@126.com");
	Property p1 = new Property(null, null, null, "unit", 0, 0, 0);
	Rental r1 = new Rental( 100, 52);
	
	@BeforeEach
	void setUp() {
		r1.setManagementFee(land1);
	}
	
    @Test
    void setManagementFee() {
    	assertEquals(8,r1.getManagementFee());
    }

	@Test
	void printLandlord() {

		assertEquals("customer.Landlord",land1.getClass().getName());

	}
}