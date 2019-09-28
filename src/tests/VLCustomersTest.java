package tests;

import customer.VLCustomers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import realEstateException.InvalidPropertyIdFormatException;

import static org.junit.Assert.assertTrue;


public class VLCustomersTest {

    boolean output;

    @BeforeEach
    void setUp() {

    }

    @Test
    void positiveCheckPropertyIDFormat() {
        try {
            VLCustomers.checkPropertyIDFormat("P101");
        } catch (InvalidPropertyIdFormatException e) {
            System.out.println(e.toString());
        }
    }


    @Test
    void negativeCheckPropertyIDFormat() {
        Assertions.assertThrows(InvalidPropertyIdFormatException.class, () -> {
            VLCustomers.checkPropertyIDFormat("dsdasd");
        });
    }
}
