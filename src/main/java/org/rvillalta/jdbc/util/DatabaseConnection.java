package org.rvillalta.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_curso?serverTimezone=GMT-6";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my-secret-pw";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}