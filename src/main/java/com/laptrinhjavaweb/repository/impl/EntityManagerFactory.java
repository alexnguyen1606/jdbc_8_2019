package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;


public class EntityManagerFactory {

    private EntityManagerFactory() {
    }

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    static Stack<Connection> pool = new Stack<>();
    private static final Integer MAX_SIZE = 10;
    private static volatile Integer usedConnection = 0;
    private static final Object syncObject = new Object();

    public static synchronized Connection getConnection() {
        if (!pool.empty()) {
            usedConnection++;
            return pool.pop();
        }
        if (!usedConnection.equals(MAX_SIZE)) {
            usedConnection++;
            return init();
        }

        while (usedConnection.equals(MAX_SIZE) && pool.empty()) {
            try {
                syncObject.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        usedConnection++;
        return pool.pop();
    }

    private static Connection init() {
        try {
            Class.forName(resourceBundle.getString("driverName"));
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("SQL exception");
        }
    }

    public static synchronized void release(Connection connection) {
        pool.push(connection);
        usedConnection--;
        syncObject.notifyAll();
    }

}
