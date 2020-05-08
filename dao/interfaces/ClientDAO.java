package dao.interfaces;

import entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClientDAO extends DAO<Client> {
    ResultSet searchClientByName(String name) throws SQLException;
    void addClient(Integer clientId, String clientName, String clientPassword) throws SQLException;
}