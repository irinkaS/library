package serviceLayer;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogInTest {

    @Test
    void checkInUser() throws SQLException {
        String realName = "Irina";
        String realPass = "123456";

        String fakeName = "Vasya";
        String fakePass = "123457";

        ArrayList<String> namePlusPass = new ArrayList<>();
        namePlusPass.add(realName);
        namePlusPass.add(realPass);

        assertEquals(LogIn.checkInUser(namePlusPass), false);


    }
}