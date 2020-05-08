package serviceLayer;

import dao.impl.JournalDAOImpl;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void order() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(101);
        if (stringBuilder.length() == 0) {
            System.out.println("Unfortunately these books are unavailable.");
        } else {
            JournalDAOImpl journalDAO = new JournalDAOImpl();
            System.out.println("You successfully ordered books ");
            ResultSet rs = journalDAO.showSuccessfullyOrderedBooks(stringBuilder);
            while (rs.next()) {
                journalDAO.addRecordAboutTakingBook(rs.getInt("bookId"), LogIn.clientId);
                assertEquals(rs.getString("bookName") + " written by " + rs.getString("bookAuthor"), "Simpson written by Springfield");
            }
        }
    }
}