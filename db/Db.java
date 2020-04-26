package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
    Connection conn;
public static Statement statement;
    public static void dbConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = getConnection()){
                System.out.println("Connection to Store DB succesfull!");
                statement = conn.createStatement();
            }
        }
        catch(Exception ex){
            System.out.println("Sorry, we can't connect now. Write please to support team");
            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "library";

        return DriverManager.getConnection(url, username, password);
    }
}
