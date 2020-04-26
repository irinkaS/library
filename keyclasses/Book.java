package keyclasses;

import java.sql.SQLException;
import java.util.List;

import static db.Db.statement;

public class Book {
    private String bookName;
    private String bookAuthor;
    private String isbn;
    public List<Book> gatheredBooks;
    private Integer bookId;
//    public static Integer id = 0;

     public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Book() {

    }

    public Book(Integer id, String bookName, String bookAuthor, String isbn) {
        this.bookId = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }



    public static void addBook (Integer bookId, String bookName, String authorName, String isbn) throws SQLException {
        Book book = new Book (bookId, bookName, authorName, isbn);
        System.out.println("INSERT INTO books " + "VALUES (" + book.bookId + ", '" + book.bookName + "', '" + book.bookAuthor + "', " + book.isbn + ")");
        statement.executeUpdate("INSERT INTO books " + "VALUES (" + book.bookId + ", '" + book.bookName + "', '" + book.bookAuthor + "', " + book.isbn + ")");
    }

    public static void deleteBook (Integer bookId) throws SQLException {
        statement.executeUpdate("DELETE FROM books where bookId =" + bookId);
    }


// 'Small funny book', 'Antony Joke', '223'


    @Override
    public String toString() {
        return bookName + '\'' +
                " written by " + bookAuthor + " (" +
                "isbn : " + isbn + ") with id " + bookId;
    }
}
