package org.project.util;

import org.project.model.Task;

import java.sql.*;

public class DButil {


    public static ResultSet createconexion(String SQL) throws SQLException {
        Connection conn = ConnectDataBase.getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(SQL);


    }
    public static PreparedStatement addDataBase(String SQL) throws SQLException {
        Connection conn = ConnectDataBase.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        return ps;

    }
}


