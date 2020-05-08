package dao.impl;

import dao.dbconnection.DbConnection;
import dao.interfaces.BookDAO;
import entities.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAOImpl implements BookDAO {
    private Statement st;

    public BookDAOImpl() throws SQLException {
        st = DbConnection.getConnection().createStatement();
    }

    @Override
    public void addBook(Integer bookId, String bookName, String bookAuthor, String isbn) throws SQLException {
        st.executeUpdate("INSERT INTO books " + "VALUES (" + bookId + ", '" + bookName + "', '" + bookAuthor + "', " + isbn + ")");
    }

    @Override
    public ResultSet showAllBooks() throws SQLException {
        return st.executeQuery("SELECT * FROM books WHERE gathered = 0 ORDER BY bookName");
    }

    @Override
    public ResultSet searchBookByAuthor(String author) throws SQLException {
        return st.executeQuery("SELECT * FROM books WHERE bookAuthor = '" + author + "' AND gathered = 0 ORDER BY bookName");
    }

    @Override
    public ResultSet searchBookByIsbn(String isbn) throws SQLException {
        return st.executeQuery("SELECT * FROM books WHERE bookIsbn = '" + isbn + "' AND gathered = 0 ORDER BY bookName");
    }

    @Override
    public void add(Book book) throws SQLException {
        st.executeUpdate("INSERT INTO books " + "VALUES (" + book.getBookId() + ", '" + book.getBookName() + "', '" + book.getBookAuthor() + "', " + book.getIsbn() + ")");
    }

    @Override
    public void delete(Integer bookId) throws SQLException {
        st.executeUpdate("DELETE FROM books where bookId =" + bookId);
    }
}
