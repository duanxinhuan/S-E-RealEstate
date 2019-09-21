package tests;

import application.RealEstate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealEstateTest {
    RealEstate r1 = new RealEstate();
    String[] str1 = {"P123", "u601 77 Cardigan Street Melbourne", "3052", "unit","3", "4","2"};
    String[] str2 = {"P223", "u601 77 Cardigan Street Melbourne", "3052", "unit","3", "4","2"};
    boolean output;
    @BeforeEach
    void setUp() {
        //id, address, suburb code, property type, bedroom number, bathroom number, car space number
        r1.propertyAlreadyExist(str1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void negativeCheckID() {
       output = r1.checkID("001P");
        assertFalse(output, "Invalid input!!!");
    }

    @Test
    void positiveCheckID() {
       output = r1.checkID("P001");
        assertTrue(output);
    }

    @Test
    void negativePropertyAlreadyExist() {
        output = r1.propertyAlreadyExist(str1);
        assertFalse(output);
    }
    @Test
    void positivePropertyAlreadyExist() {
        output = r1.propertyAlreadyExist(str2);
        assertTrue(output);
    }
}