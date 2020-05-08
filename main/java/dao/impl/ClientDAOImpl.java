package dao.impl;

import dao.dbconnection.DbConnection;
import dao.interfaces.ClientDAO;
import entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ClientDAOImpl implements ClientDAO {
    private Statement st;

    public ClientDAOImpl() throws SQLException {
        st = DbConnection.getConnection().createStatement();
    }

    public ResultSet searchClientByName(String name) throws SQLException {
        return st.executeQuery("select * from clients where clientName = '" + name + "'");
    }

    public void addClient(Integer clientId, String clientName, String clientPassword) throws SQLException {
        st.executeUpdate("INSERT INTO clients " + "VALUES (" + clientId + ", '" + clientName + "', '" + clientPassword + ")");
    }

    @Override
    public void add(Client client) throws SQLException {
        st.executeUpdate("INSERT INTO clients " + "VALUES (" + client.getClientId() + ", '" + client.getClientName() + "', '" + client.getPassword());

    }

    @Override
    public void delete(Integer clientId) throws SQLException {
        st.executeUpdate("DELETE FROM clients where clientId =" + clientId);
    }
}
