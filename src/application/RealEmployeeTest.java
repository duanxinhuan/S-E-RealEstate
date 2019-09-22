package application;

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
        System.out.println(re.getPr().get(0).getId());
    }
}