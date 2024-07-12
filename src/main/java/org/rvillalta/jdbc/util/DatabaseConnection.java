package org.rvillalta.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://127.0.0.1:3306/java_curso?serverTimezone=GMT-6";
    private static String username = "root";
    private static String password = "my-secret-pw";
    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if(connection == null){
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;

    }
}
