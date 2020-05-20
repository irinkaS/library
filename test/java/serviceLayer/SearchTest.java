package serviceLayer;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class SearchTest {
    @Test
    void search() throws SQLException {
        assertEquals((Search.searchBy("8629402759372")).toString(), "[Simpson' written by Simpson (isbn : 8629402759372) with id 101]");
        assertEquals((Search.searchBy("Springfield")).toString(), "[Simpson' written by Simpson (isbn : 8629402759372) with id 101, Simpson' written by Simpson (isbn : 4729402759372) with id 1001]");
        assertEquals((Search.searchBy("show all")).toString(), "[bookN' written by bookN (isbn : 1234567891234) with id 5, Simpson' written by Simpson (isbn : 8629402759372) with id 101, Simpson' written by Simpson (isbn : 4729402759372) with id 1001]");
    }
}