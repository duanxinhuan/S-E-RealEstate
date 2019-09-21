package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RealEstateTest {

	public RealEstate rl = new RealEstate();	
		
	@Test
	void succeedTestCheckID() {
        	
	    
    	Boolean output = rl.checkID("P123");
		
		assertEquals(output,true);
	}
	
	@Test
	void failTestCheckID() {		
		
		Boolean output = rl.checkID(",./,,");
		
		assertEquals(output,false);
	}


//	@Test
//	void succeedTestPropertyAlreadyExist() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void failTestPropertyAlreadyExist() {
//		fail("Not yet implemented");
//	}

}
