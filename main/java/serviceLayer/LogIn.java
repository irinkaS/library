package serviceLayer;

import dao.impl.ClientDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn {
    static Integer clientId;

    public ArrayList<String> logIn() {
        System.out.println("Hello! Please log in");
        System.out.println("Your login:");
        Scanner scanner = new Scanner(System.in);
        String writtenName = scanner.nextLine();
        System.out.println("Your password:");
        String writtenPassword = scanner.nextLine();
        ArrayList<String> namePlusPass = new ArrayList<String>();
        namePlusPass.add(writtenName);
        namePlusPass.add(writtenPassword);
        return namePlusPass;
    }

    public static boolean checkInUser(ArrayList<String> namePlusPass) throws SQLException {
        String writtenName = namePlusPass.get(0);
        String writtenPassword = namePlusPass.get(1);
        ResultSet rs;

        while (true) {
            try {
                ClientDAOImpl clientDAO = new ClientDAOImpl();
                rs = clientDAO.searchClientByName(writtenName);
                int counter = 0;
                String pass = null;
                while (rs.next()) {
                    counter++;
                    pass = rs.getString("clientPassword");
                    clientId = rs.getInt("clientId");
                }
                if (counter == 1 && writtenPassword.equals(pass)) {
                    System.out.println("You are loged in succesfully");
                    System.out.println("Welcome to the Library of Modern Literature!");
                    return false;
                } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please try again");
                    System.out.println("Your login:");
                    writtenName = scanner.nextLine();
                    System.out.println("Your password:");
                    writtenPassword = scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Sorry, we can't check your data now. Write please to support team.");
                return false;
            }
        }
    }

}
