package serviceLayer;

import dao.impl.BookDAOImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTest {

    @Test
    void search() throws SQLException {
        assertEquals(Pattern.compile("\\d{13}").matcher("1234567891234").matches(), true);
        assertEquals(Pattern.compile("\\d{13}").matcher("12345678912345").matches(), false);
        assertEquals(Pattern.compile("\\d{13}").matcher("123456789123g").matches(), false);
    }

}