package com.example.webmvc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;

    private ConnectionPool() {
        String url = "jdbc:mysql://localhost:3306/web";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "12345");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");
        for (int i = 0; i < 8; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            free.add(connection);
        }
    }

    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        //locki
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void releaseConnection(Connection connection){
        try {
            free.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
