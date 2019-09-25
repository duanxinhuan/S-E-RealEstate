package tests;

import realestate_system.RealEstate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import property.Property;

import static org.junit.jupiter.api.Assertions.*;

class RealEstateTest {
    RealEstate r1 = new RealEstate();
    String[] str1 = {"P123", "u601 77 Cardigan Street Melbourne", "3052", "unit","3", "4","2"};
    String[] str2 = {"P223", "u601 77 Cardigan Street Melbourne", "3052", "unit","3", "4","2"};
    Property pr = new Property("P123", "u601 77 Cardigan Street Melbourne", "3052", "unit", 3, 4,2);
    boolean output;

    @BeforeEach
    void setUp() {
        //id, address, suburb code, property type, bedroom number, bathroom number, car space number
        r1.addProperty(pr);
    }


    @Test
    void negativePropertyAlreadyExist() {
        output = r1.propertyAlreadyExist(str1);
        assertFalse(output, "Property already exists!");
    }
    @Test
    void positivePropertyAlreadyExist() {
        output = r1.propertyAlreadyExist(str2);
        assertTrue(output);
    }

}