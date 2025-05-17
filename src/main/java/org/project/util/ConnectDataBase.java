package org.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/project_task";
    private static final String USER = "root";
    private static final String PASSWORD = "Novasenha,12";

    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL,USER,PASSWORD);

    }

}
