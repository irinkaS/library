package serviceLayer;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LogInTest {

    @Test
    void checkLoginAndPass() throws SQLException {
        LogIn logIn = new LogIn();

        String realName = "Irina";
        String realPass = "4040";

        String fakeName = "Vasya";
        String fakePass = "123457";

        assertEquals(logIn.checkLoginAndPass(realName, realPass), true);
        assertEquals(logIn.checkLoginAndPass(fakeName, fakePass), false);
    }
}