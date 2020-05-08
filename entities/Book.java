package entities;

import dao.interfaces.BookDAO;

import java.util.List;

public class Book extends Entity {
    private String bookName;
    private String bookAuthor;
    private String isbn;
    private Integer bookId;

    public Book() {
    }

    public Book(Integer id, String bookName, String bookAuthor, String isbn) {
        this.bookId = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return bookName + '\'' +
                " written by " + bookAuthor + " (" +
                "isbn : " + isbn + ") with id " + bookId;
    }
}
