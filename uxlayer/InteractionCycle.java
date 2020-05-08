package uxlayer;

import dao.dbconnection.DbConnection;
import serviceLayer.LogIn;
import serviceLayer.Order;
import serviceLayer.Search;

import java.sql.*;
import java.util.Scanner;

import static dao.dbconnection.DbConnection.*;

public class InteractionCycle {


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        DbConnection.dbConnection();

        LogIn.checkInUser(new LogIn().logIn());

        String query = new String();
        boolean flag = true;
        while (flag) {
            System.out.println("You can choose between search, order and unlog. Please type your choice.");
            query = scanner.nextLine();
            switch (query) {
                case "search":
                    System.out.println("We show only books available for taking right now");
                    System.out.println("To see all books of library type please \"show all\"");
                    System.out.println("To find special book be sure you know it's isbn and type it here, use only numbers");
                    System.out.println("To see all books of a special author you can type his surname");
                    Search.search(scanner.nextLine());
                    break;
                case "order":
                    System.out.println("Write ids of books which you want to take, press enter after each id, when you finish type end");
                    Order.order();
                    break;
                case "unlog":
                    System.out.println("You are unlogged");
                    LogIn.checkInUser(new LogIn().logIn());
            }
        }
        getConnection().close();
    }

}
