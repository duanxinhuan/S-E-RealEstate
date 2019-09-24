package tests;

import customer.Buyer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import realEstateException.DuplicateSuburbException;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    Buyer b1 = new Buyer("1a","1b","1b","1c");
    private String[] ABC_list = {"A", "B", "C"};

    @BeforeEach
    void setUp() throws DuplicateSuburbException {
        b1.addSuburb("3052");
    }

    @Test
    void negativeAddSuburb(){
        Assertions.assertThrows(DuplicateSuburbException.class,() ->{b1.addSuburb("3052");});
    }

    @Test
    void positiveAddSuburb()  {
        try{
        b1.addSuburb(("3053"));
        b1.addSuburb("3054");
        for(int i =0; i<10; i++){
            b1.addSuburb(String.valueOf(3000+i));
        }}catch (DuplicateSuburbException e){
            fail("this should not throw exception!");
        }
        assertEquals(13, b1.getNumOfSuburb());
        assertEquals("3009", b1.getSuburbCodeList()[12]);
    }

}