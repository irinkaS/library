package serviceLayer;

import dao.impl.BookDAOImpl;
import entities.Book;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Search {


    public static void search(String usersChoice) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            BookDAOImpl bookDAO = new BookDAOImpl();
            ResultSet rs;
            if (usersChoice.equals("show all"))
                rs = bookDAO.showAllBooks();
            else if (Pattern.compile("\\d{13}").matcher(usersChoice).matches())
                rs = bookDAO.searchBookByIsbn(usersChoice);
            else
                rs = bookDAO.searchBookByAuthor(usersChoice);

            Book book;
            while (rs.next()) {
                book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setBookAuthor(rs.getString("bookName"));
                book.setIsbn(rs.getString("bookIsbn"));
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("Not Connected, Error: " + e.getMessage());
        }

        // showing results
        if (books.size() == 0) {
            System.out.println("Nothing found.");
        }
        System.out.println("Now you can order any of these books:");
        for (Book book : books) {
            System.out.println(book);

        }

    }
}
