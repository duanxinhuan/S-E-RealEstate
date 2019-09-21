package tests;


import property.*;
import customer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {
	Landlord l1 = new Landlord(null, null, null, null);
	Property p1 = new Property(null, null, null, null, 0, 0, 0);
	Rental r1 = new Rental(p1, 100, 52);
	
	@BeforeEach
	void setUp() {
		r1.setManagementFee(l1);
	}
	
    @Test
    void setManagementFee() {
    	assertEquals(8,r1.getManagementFee());
    }
}