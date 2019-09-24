package tests;

import realestate_system.LinkDatabase;
import realestate_system.RealEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealEmployeeTest {
    RealEmployee re = new RealEmployee();

    @BeforeEach
    void setUp() {
        LinkDatabase.connectJDBCToAWSEC2();
    }
    @Test
    void loadProperty() {
        re.loadProperty();
        assertNotNull(re.getPr());
        assertEquals("P105",re.getPr().get(0).getId());
    }
}