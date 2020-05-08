package dao.impl;

import dao.dbconnection.DbConnection;
import dao.interfaces.JournalDAO;
import entities.Journal;

import java.sql.*;
import java.time.LocalDate;

public class JournalDAOImpl implements JournalDAO {
    private Statement st;
    private Statement st2;
    static Integer journalId = 0;

    public JournalDAOImpl() throws SQLException {
        st = DbConnection.getConnection().createStatement();
        st2 = DbConnection.getConnection().createStatement();
    }

    @Override
    public ResultSet showSuccessfullyOrderedBooks(StringBuilder name) throws SQLException {
        return st.executeQuery("SELECT * FROM books WHERE bookId IN (" + name + ")");
    }

    @Override
    public void addRecordAboutTakingBook(Integer bookId, Integer clientId) throws SQLException {
        ResultSet resultSet = st2.executeQuery("SELECT MAX(journalID) FROM journal");
        if (resultSet.next()) {
            journalId = resultSet.getInt(1);
        } else {
            journalId = 1;
        }
        st2.executeUpdate("INSERT INTO journal " + "VALUES (" + ++journalId + ", " + bookId + ", " + clientId + ", '" + LocalDate.now() + "', " + null + ")");
        st2.executeUpdate("UPDATE books SET gathered = 1 where bookId =" + bookId);
    }

    @Override
    public void addRecordAboutReturningBook(Integer id) throws SQLException {
        st.executeUpdate("UPDATE books SET gathered = 0 where bookId =" + id);
        st.executeUpdate("UPDATE journal SET dateOfreturning = " + LocalDate.now() + " WHERE 'books.id' = " + id);
    }

    @Override
    public void add(Journal journal) throws SQLException {
        st.executeUpdate("INSERT INTO journal VALUES (" + journal.getJournalId() + ", " + journal.getBookId() + ", " + journal.getClientId() + ", "
                + journal.getGatheringDate() + ", " + journal.getReturningDate());
    }

    @Override
    public void delete(Integer i) throws SQLException {
        st.executeUpdate("DELETE FROM journal WHERE journalId = " + i);
    }
}
