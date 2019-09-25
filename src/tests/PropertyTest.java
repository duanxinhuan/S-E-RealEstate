package tests;

import employees.PropertyManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import property.Property;
import property.Rental;
import realEstateException.CanNotRecommendException;
import realEstateException.HouseAlreadyAssignedException;
import realEstateException.WrongIdException;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    Property p = new Property("P100", "U601 77 cardigan st", "3053", "unit",
            3, 2, 1);
    Rental r1 = new Rental("P100_R01", "W", 400.0, 36.5,
            100, "E132", false);
    Rental r2 = new Rental("P100_R02", "A", 400.0, 36.5,
            100, "E132", false);
    PropertyManager pm = new PropertyManager("s123", "222", "steve");

    @BeforeEach
    void setUp() {
        p.addRental(r1);
        p.addRental(r2);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void positiveGetRental() {
        try {
            p.getRental("P100_R01");
        } catch (WrongIdException e) {
            fail("this should't throw exception");
        }
    }

    @Test
    void negativeGetRental() {
        Assertions.assertThrows(WrongIdException.class, () -> {
            p.getRental("P109_R3");
        });
    }

    @Test
    void negativeAssign() {
        Assertions.assertThrows(HouseAlreadyAssignedException.class, () -> {
            r2.assign(pm);
        });
    }

    @Test
    void positiveAssign() {
        try {
            r1.assign(pm);
        } catch (HouseAlreadyAssignedException e) {
            fail("this should't throw exception");
        }
    }

    @Test
    void negativeGenerateRecommendation() {
        Assertions.assertThrows(CanNotRecommendException.class, () -> {
            r1.generateRecommendation("s1");
        });
    }

    @Test
    void positiveGenerateRecommendation() {
        try {
            r2.generateRecommendation("s2");
        } catch (CanNotRecommendException e) {
            fail("this should't throw exception");
        }
    }
}




