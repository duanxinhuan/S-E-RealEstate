package tests;

import customer.BRCustomers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import realEstateException.SuburbCodeDoesNotExistException;

import static org.junit.jupiter.api.Assertions.*;

class BRCustomersTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void negativeCheckSuburbCode() {

        Assertions.assertThrows(SuburbCodeDoesNotExistException.class,() ->{BRCustomers.checkSuburbCode("33045 ");});
    }

    @Test
    void positiveAddSuburb()  {
        try{
            BRCustomers.checkSuburbCode("3053");
            }catch (SuburbCodeDoesNotExistException e){
            fail("this should not throw exception!");
        }
    }
}