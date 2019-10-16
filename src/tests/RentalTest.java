package tests;


import property.*;
import customer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

public class RentalTest {
	Landlord land1;
	Property p1;
	Rental r1;
	Rental r2;
	Rental r3;
	
	@BeforeEach
	void setUp() {
		land1 = new Landlord("1", "2333", "peter", "peter@126.com");
		p1 = new Property(null, null, null, "unit", 0, 0, 0);
		r1 = new Rental(100, 52);
		r2 = new Rental(0, 52);
		r3 = new Rental(-100, 52);
	}
	
	// Should pass, management fee should be standard
    @Test
    void testSetManagementFee() {
		r1.setManagementFee(land1);
    	assertEquals(8,r1.getManagementFee());
    }
    
    // Should pass, management fee should be negotiated down
    @Test
    void testLandlordNegotiate() {
    	land1.setNegotiate();
    	r1.setManagementFee(land1);
    	assertEquals(7,r1.getManagementFee());
    }
    
    // A weekly rent of zero should fail and print to console the exception thrown in the setManagementFee method
    @Test
    void testExpectedArithmeticExceptionZero() {
     
      Assertions.assertThrows(ArithmeticException.class, () -> {
        r2.setManagementFee(land1);
      });
     
    }
    
    // A weekly rent that is a negative number should fail and print to console the exception thrown in the setManagementFee method
    @Test
    void testExpectedArithmeticExceptionNegative() {
     
      Assertions.assertThrows(ArithmeticException.class, () -> {
        r3.setManagementFee(land1);
      });
     
    }

	@Test
	void printLandlord() {

		assertEquals("customer.Landlord",land1.getClass().getName());

	}
}