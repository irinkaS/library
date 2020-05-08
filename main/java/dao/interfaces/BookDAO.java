package dao.interfaces;

import entities.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookDAO extends DAO<Book> {
    void addBook(Integer bookId, String bookName, String authorName, String isbn) throws SQLException;
    ResultSet showAllBooks() throws SQLException;
    ResultSet searchBookByAuthor(String author) throws SQLException;
    ResultSet searchBookByIsbn(String isbn) throws SQLException;

}
