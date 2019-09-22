package tests;

import application.LinkDatabase;
import customer.Landlord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import property.Property;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LinkDatabaseTest {
    Property p = new Property("P105", "U601, 77 cardigan st mel", "3053", "house",
           1,1,1 );
    Landlord l = new Landlord("2","123","wang","wang@126.com");
    @BeforeEach
    void setUp() {
        LinkDatabase.connectJDBCToAWSEC2();
        p.addRental(300.7,44.9);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void connectJDBCToAWSEC2() {
        LinkDatabase.connectJDBCToAWSEC2();
        assertNotEquals(LinkDatabase.getConnection(), null );
    }

    @Test
    void register() {
        LinkDatabase.register("12345678", "shi", "shi@163.com");
    }

    @Test
    void uploadProperty() {
        LinkDatabase.uploadProperty(p,l);

    }
}