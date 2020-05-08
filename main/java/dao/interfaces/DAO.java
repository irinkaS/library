package dao.interfaces;

import java.sql.*;

public interface DAO<T> {

    void add(T t) throws SQLException;

    void delete(Integer i) throws SQLException;

    static void closing(Statement st, Connection conn) throws SQLException {
        if (st != null) st.close();
        if (conn != null) conn.close();
    }

    static void closing(ResultSet rs, Statement st, Connection conn) throws SQLException {
        if (rs != null) rs.close();
        if (st != null) st.close();
        if (conn != null) conn.close();
    }
}