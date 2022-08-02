package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
    static final String JDBCDriver = "com.mysql.jdbc.driver";
    static final String DB_url = "jdbc:mysql://localhost/CRSDatabase";

    //Database access credentials
    static final String user = "root";
    static final String pass = "mysql@root";
    Connection conn = null;

    public Connection connect() {

        try {

            //Register driver and create connection
            Class.forName("com.mysql.jdbc.Driver");

            //make/open the connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_url, user, pass);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
