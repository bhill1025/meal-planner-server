package com.meal_planner.jdbc.dao;

import org.springframework.util.StringUtils;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements Dao<T> {

    private final String DB_TYPE;
    private final String HOST;
    private final String PORT;
    private final String SCHEMA;
    private final String USERNAME;
    private final String PASSWORD;

    private final String CONNECTION_STRING;
    private final String CONNECTION_FORMAT = "jdbc:%s://%s:%s/%s";

    private Connection connection;
    private PreparedStatement statement;

    public AbstractDao() {
        this.DB_TYPE = "mysql";
        this.HOST = "localhost";
        this.PORT = "3306";
        this.SCHEMA = "meal_planner_schema";
        this.USERNAME = "admin";
        this.PASSWORD = "Welcome@123";

        this.CONNECTION_STRING = String.format(CONNECTION_FORMAT, DB_TYPE, HOST, PORT, SCHEMA);
    }

    public AbstractDao(String dbType, String host, String port, String schema, String username, String password) {
        this.DB_TYPE = dbType;
        this.HOST = host;
        this.PORT = port;
        this.SCHEMA = schema;
        this.USERNAME = username;
        this.PASSWORD = password;

        this.CONNECTION_STRING = String.format(CONNECTION_FORMAT, DB_TYPE, HOST, PORT, SCHEMA);
    }

    /**
     * Creates a connection to the database.
     */
    public void createConnection(){
        try {
            if (this.connection != null) {
                closeConnection();
            }
            this.connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection to the database and closes the prepared statement.
     */
    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            if (this.statement != null) {
                this.statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates a simple SQL prepared statement.
     */
    public void createStatement(String sqlString) {
        createStatement(sqlString, new String[0]);
    }

    /**
     * Creates an SQL prepared statement with specified columns.
     */
    public void createStatement(String sqlString, String[] columns) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString, columns);
            int minimum = Math.min(columns.length, StringUtils.countOccurrencesOf(sqlString, "?"));
            for (int i = 0; i < minimum; i++) {
                prepareStatement.setString(i + 1, columns[i]);
            }
            this.statement = prepareStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setString(int index, String string) {
        try {
            this.statement.setString(index, string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBlob(int index, Blob blob) {
        try {
            this.statement.setBlob(index, blob);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery() {
        ResultSet result = null;
        try {
            if (this.statement != null) {
                result = this.statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate() {
        int result = 0;
        try {
            if (this.statement != null) {
                result = this.statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
