import db.Db;
import keyclasses.Book;
import keyclasses.Client;

import java.sql.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

import static db.Db.*;

public class Main {
    static Integer journalId = 0;
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
                sql = "select * from clients where clientName = '" + writtenName + "'";
                rs = stmt.executeQuery(sql);
                int counter = 0;
                String pass = null;
                while (rs.next()){
                    counter++;
                    pass = rs.getString("clientPassword");
                }
                if (counter == 1 && writtenPassword.equals(pass)) {
                        System.out.println("You are loged in succesfully");
//                        clientId = rs.getInt("clientId");
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
                book.setBookId(rs.getInt("bookId"));
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

        //choosing books
        System.out.println("Write ids of books which you want to take, press enter after each id, when you finish type end");
        String chosedId;
        ArrayList<String> chosedBooks = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        while (!(chosedId = scanner.nextLine()).equals("end")){
//            chosedBooks.add(chosedId);
            if (Pattern.compile("\\d+").matcher(chosedId).matches()) {
                if (stringBuilder.length() != 0)
                    stringBuilder.append(", ");
                stringBuilder.append(chosedId);
            }


        }

        //checking availability and ordering
        ArrayList<Book> orderedBooks = new ArrayList<>();
        if (stringBuilder.length() == 0){
            System.out.println("Unfortunately these books are unavailable.");
        }else {


            //adding info into Journal

            Integer clientId = -1;
            sql = "SELECT * FROM clients WHERE clientName = '" + writtenName + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                clientId = rs.getInt("clientId");
            }

            Statement stmt2 = conn.createStatement();
            String name;
            String author;
            sql = "SELECT * FROM books WHERE bookId IN (" + stringBuilder + ")";
            rs = stmt2.executeQuery(sql);
            System.out.println("You successfully ordered: ");
            while (rs.next()){
                name = rs.getString("bookName");
                author = rs.getString("bookAuthor");
                sql = "INSERT INTO journal " + "VALUES (" + ++journalId + ", " + rs.getInt("bookId") + ", " + clientId + ", '" + LocalDate.now() + "', " + null + ")";
                String sql2 = "UPDATE books SET gathered = 1 where bookId =" + rs.getInt("bookId");
                stmt.executeUpdate (sql);
                stmt.executeUpdate (sql2);
                System.out.println(name + " written by " + author);
            }
            }
        getConnection().close();
        }

}
