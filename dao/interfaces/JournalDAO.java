package dao.interfaces;

import entities.Journal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public interface JournalDAO extends DAO<Journal> {
    public ResultSet showSuccessfullyOrderedBooks(StringBuilder name) throws SQLException;
    public void addRecordAboutTakingBook(Integer bookId, Integer clientId) throws SQLException;
    public void addRecordAboutReturningBook(Integer id) throws SQLException;


}
