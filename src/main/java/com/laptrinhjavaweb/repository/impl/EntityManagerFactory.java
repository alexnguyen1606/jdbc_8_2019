package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class EntityManagerFactory {

    private EntityManagerFactory() {
    }

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            Class.forName(resourceBundle.getString("driverName"));
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
