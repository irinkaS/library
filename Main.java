import db.Db;
import keyclasses.Book;

import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

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

        // checking user
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
        System.out.println("Welcome to the Library!");
        System.out.println("To log out you can type ane time end");

        // suggestion to see books
        System.out.println("To see all book type please...");




        // insert working
//        String sql = "INSERT INTO books " + "VALUES (" + 5 + ", '" + "bookN" + "', '" + "Author" + "', " + 1245 + ")";
//        stmt.executeUpdate(sql);


//         select working
        ArrayList<Book> books = new ArrayList<>();
                try{
//            Connection conn = getConnection();
//            Statement stmt = conn.createStatement();
            sql = "select * from books";
            rs = stmt.executeQuery(sql);

            Book book;
            while(rs.next()) {
                book = new Book ();
                book.setBookName(rs.getString("bookName"));
                book.setAuthorName(rs.getString("bookName"));
                book.setIsbn(rs.getInt("bookIsbn"));
                books.add(book);
            }
        } catch(Exception e)
        {
            System.out.println("Not Connected, Error: "+e.getMessage());
        }

        for (Book book: books) {
            System.out.println(book);
        }


    }



//        Db.dbConnection();
//        ResultSet rs = statement.executeQuery( "select * from books");
//        while (rs.next()) {
//            String lastName = rs.getString("bookName");
//            System.out.println("" + "\n");
//        }






}
