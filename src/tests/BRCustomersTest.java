package tests;

import customer.BRCustomers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import realEstateException.InvalidIdException;

import static org.junit.jupiter.api.Assertions.*;

class BRCustomersTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void negativeCheckID() {
        Assertions.assertThrows(InvalidIdException.class,() ->{BRCustomers.checkID("PP001");});
    }
    @Test
    void positiveAddSuburb()  {
        try{
            BRCustomers.checkID("P001");
            }catch (InvalidIdException e){
            fail("this should not throw exception!");
        }
    }
}