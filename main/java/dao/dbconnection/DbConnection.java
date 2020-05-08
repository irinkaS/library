package dao.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection dbConnection() {
        try (Connection connection = getConnection()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection to Store DB succesfull!");
        } catch (Exception ex) {
            System.out.println("Sorry, we can't connect now. Write please to support team");
            System.out.println(ex);
        }
        return null;
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "library";
        return DriverManager.getConnection(url, username, password);
    }
}
