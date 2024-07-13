package org.rvillalta.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_curso?serverTimezone=GMT-6";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my-secret-pw";

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMinIdle(5); // Minimum number of idle connections
        dataSource.setMaxIdle(10); // Maximum number of idle connections
        dataSource.setMaxOpenPreparedStatements(100); // Determines the maximum number of open prepared statements
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}