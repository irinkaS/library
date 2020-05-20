package serviceLayer;

import dao.impl.ClientDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn {
    static Integer clientId;
    ArrayList<String> loginPlusPass;

    public void logIn() throws SQLException {
        System.out.println("Hello! Please log in");
        boolean isLoginAndPassTrue = false;
        while (isLoginAndPassTrue == false) {
            inputLoginAndPass();
            String writtenName = loginPlusPass.get(0);
            String writtenPassword = loginPlusPass.get(1);
            if (checkLoginAndPass(writtenName, writtenPassword)){
                isLoginAndPassTrue = true;
            }
        }
    }

    public ArrayList<String> inputLoginAndPass(){
        System.out.println("Your login:");
        Scanner scanner = new Scanner(System.in);
        String writtenName = scanner.nextLine();
        System.out.println("Your password:");
        String writtenPassword = scanner.nextLine();
        loginPlusPass = new ArrayList<String>();
        loginPlusPass.add(writtenName);
        loginPlusPass.add(writtenPassword);
        return loginPlusPass;
    }


    public boolean checkLoginAndPass (String writtenName, String writtenPassword) throws SQLException {
        ResultSet rs;
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
                return true;
            } else {
                System.out.println("Please try again");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Sorry, we can't check your data now. Write please to support team.");
            return true;
        }
    }

}
