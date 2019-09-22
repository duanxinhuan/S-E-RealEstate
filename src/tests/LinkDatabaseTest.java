package tests;

import application.LinkDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LinkDatabaseTest {
    @BeforeEach
    void setUp() {
        LinkDatabase.connectJDBCToAWSEC2();
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
}