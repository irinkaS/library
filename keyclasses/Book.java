package keyclasses;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static db.Db.statement;

public class Book {
    private String bookName;
    private String authorName;
    private Integer isbn;
    public List<Book> gatheredBooks;
    public static Integer id = 0;

    public Book() {

    }

    public Book(Integer id, String bookName, String authorName, Integer isbn) {
        this.id = id++;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }



    public static void addBook (String bookName, String authorName, Integer isbn) throws SQLException {
        Book book = new Book (id++, bookName, authorName, isbn);
        System.out.println("INSERT INTO books " + "VALUES (" + book.id + ", '" + book.bookName + "', '" + book.authorName + "', " + book.isbn + ")");
        statement.executeUpdate("INSERT INTO books " + "VALUES (" + book.id + ", '" + book.bookName + "', '" + book.authorName + "', " + book.isbn + ")");
    }
// 'Small funny book', 'Antony Joke', '223'


    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", isbn=" + isbn;
    }
}
