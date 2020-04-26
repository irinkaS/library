package keyclasses;

import java.sql.SQLException;

import static db.Db.statement;

public class Client {
    private Integer clientId;
    private String clientName;
    private String password;

    public Client(Integer clientId, String clientName, String password) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.password = password;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void returnBook (Integer id) throws SQLException {
        String sql = "UPDATE books SET gathered = 0 where bookId =" + id;
        String sql2 = "UPDATE journal SET dateOfreturning LocalDate.now() where bookId =" + id;
        statement.executeUpdate (sql);
        statement.executeUpdate (sql2);
        }

    public static void addClient (Integer clientId, String clientName, String clientPassword) throws SQLException {
        Client client = new Client (clientId, clientName, clientPassword);
        System.out.println("INSERT INTO clients " + "VALUES (" + client.clientId + ", '" + client.clientName + "', '" + client.password + ")");
        statement.executeUpdate("INSERT INTO books " + "VALUES (" + client.clientId + ", '" + client.clientName + "', '" + client.password+ ")");
    }

    public static void deleteClient (Integer clientId) throws SQLException {
        statement.executeUpdate("DELETE FROM clients where clientId =" + clientId);
    }
}

