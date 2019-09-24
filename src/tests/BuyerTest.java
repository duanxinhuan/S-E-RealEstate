package tests;

import customer.Buyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    Buyer b1 = new Buyer("1a","1b","1b","1c");
    private String[] ABC_list = {"A", "B", "C"};

    @BeforeEach
    void setUp() {
        b1.addSuburb("3053");
    }

    @Test
    void addSuburb() {
        for(int i = 0; i<10; i++){
        b1.addSuburb("3053");}
        assertEquals(11, b1.getNumOfSuburb());

    }

    @Test
    void listToString() {
        String s = Buyer.listToString(ABC_list);
        assertEquals("A_B_C",s);
    }
}