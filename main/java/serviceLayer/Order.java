package serviceLayer;

import dao.impl.JournalDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Order {
    public static void order() throws SQLException {
        String chosedId;
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (!(chosedId = scanner.nextLine()).equals("end")) {
            if (Pattern.compile("\\d+").matcher(chosedId).matches()) {
                if (stringBuilder.length() != 0)
                    stringBuilder.append(", ");
                stringBuilder.append(chosedId);
            }
        }

        //checking availability and ordering
        if (stringBuilder.length() == 0) {
            System.out.println("Unfortunately these books are unavailable.");
        } else {
            JournalDAOImpl journalDAO = new JournalDAOImpl();
            System.out.println("You successfully ordered books ");
            ResultSet rs = journalDAO.showSuccessfullyOrderedBooks(stringBuilder);
            while (rs.next()) {
                journalDAO.addRecordAboutTakingBook(rs.getInt("bookId"), LogIn.clientId);
                System.out.println(rs.getString("bookName") + " written by " + rs.getString("bookAuthor"));
            }
        }

    }
}
