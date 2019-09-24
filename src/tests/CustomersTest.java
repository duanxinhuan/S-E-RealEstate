package tests;

import customer.Customers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import realEstateException.DuplicateSuburbException;
import realEstateException.WrongEmailFormatException;

import static org.junit.jupiter.api.Assertions.*;

class CustomersTest {

    @Test
    void listToString() {
        String s[] = {"A","B","C"};
        String output = Customers.listToString(s);
        assertEquals("A_B_C", output);
    }

    @Test
    void positiveCheckEmailFormat() {
        try {
            Customers.checkEmailFormat("s3713321@student.rmit.edu.au");
        } catch (WrongEmailFormatException e) {
            fail("shouldn't throw exception");
        }
    }

    @Test
    void negativeCheckEmailFormat() {
        WrongEmailFormatException thrown =
        Assertions.assertThrows(WrongEmailFormatException.class,()
                ->Customers.checkEmailFormat("fff@lsamcksa"));
        assertEquals("Invalid email format", thrown.getMessage());

    }
}