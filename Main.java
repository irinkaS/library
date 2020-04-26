import db.Db;
import keyclasses.Book;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static db.Db.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String sql;
        ResultSet rs;

        // connecting to db
        Db.dbConnection();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        // asking for log in
        System.out.println("Hello! Please log in");
        System.out.println("Your login:");
        Scanner scanner = new Scanner(System.in);
        String writtenName = scanner.nextLine();
        System.out.println("Your password:");
        String writtenPassword = scanner.nextLine();

        // checking the user
        while (true) {
            try {
                sql = "select clientPassword from clients where clientName = '" + writtenName + "'";
                rs = stmt.executeQuery(sql);
                int counter = 0;
                String pass = null;
                while (rs.next()){
                    counter++;
                    pass = rs.getString("clientPassword");
                }
                if (counter == 1 && writtenPassword.equals(pass)) {
                        System.out.println("You are loged in succesfully");
                        break;
                } else {
                    System.out.println("Please try again");
                    System.out.println("Your login:");
                    writtenName = scanner.nextLine();
                    System.out.println("Your password:");
                    writtenPassword = scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Sorry, we can't check your data now. Write please to support team.");
                return;

            }
        }

        // welcome message and info about log out
        System.out.println("Welcome to the Library of Modern Literature!");
        System.out.println("To log out you need to just type \"end\" any time");

        // suggestion to see books
        System.out.println("We show only books available for taking right now");
        System.out.println("To see all books of library type please \"show all\"");
        System.out.println("To find special book be sure you know it's isbn and type it here, use only numbers");
        System.out.println("To see all books of a special author you can type his surname");
        String usersChoice = scanner.nextLine();

        // checking what the user wants to find
        if (usersChoice.equals("show all"))
            sql = "SELECT * FROM books WHERE gathered = 0 ORDER BY bookName";
        else if (Pattern.compile("\\d{13}").matcher(usersChoice).matches())
            sql = "SELECT * FROM books WHERE bookIsbn = '" + usersChoice + "' AND gathered = 0 ORDER BY bookName";
        else sql = "SELECT * FROM books WHERE bookAuthor = '" + usersChoice + "' AND gathered = 0 ORDER BY bookName";

//      making query
        ArrayList<Book> books = new ArrayList<>();
        try{
            rs = stmt.executeQuery(sql);

            Book book;
            while(rs.next()) {
                book = new Book ();
                book.setBookName(rs.getString("bookName"));
                book.setBookAuthor(rs.getString("bookName"));
                book.setIsbn(rs.getString("bookIsbn"));
                books.add(book);
            }
        } catch(Exception e)
        {
            System.out.println("Not Connected, Error: "+e.getMessage());
        }

        // showing results
        for (Book book: books) {
            System.out.println(book);
        }

        // insert working
//        String sql = "INSERT INTO books " + "VALUES (" + 5 + ", '" + "bookN" + "', '" + "Author" + "', " + 1245 + ")";
//        stmt.executeUpdate(sql);

    }
}
