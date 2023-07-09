package com.example.webmvc.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 8;

    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;

    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> givenAwayConnections;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ConnectionPool() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database.properties", e);
        }
        freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(prop.getProperty("url"), prop);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.FATAL, "Couldn't create connection to database" + e);
                throw new ExceptionInInitializerError(e.getMessage());
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (!isCreated.get()) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = (ProxyConnection) freeConnections.take();
            logger.log(Level.DEBUG, "Gave connection" + proxyConnection);
            givenAwayConnections.offer(proxyConnection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (!connection.getClass().equals(ProxyConnection.class)) {
            logger.log(Level.ERROR, "Attempt to release a non-proxy connection ");
            throw new IllegalArgumentException();
        }
        givenAwayConnections.remove(connection);
        try {
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException in method releaseConnection " + e.getMessage());
        }
    }

    public static void deregister() throws SQLException {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Error deregistering driver" + e.getMessage());
            }
        });
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection proxyConnection = (ProxyConnection) freeConnections.take();
                proxyConnection.isReallyClose();
            } catch (InterruptedException e) {
                logger.error("Failed destroy pool" + e.getMessage());
            }
        }
    }
}
