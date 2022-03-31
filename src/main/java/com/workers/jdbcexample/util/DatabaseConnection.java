package com.workers.jdbcexample.util;


import java.sql.*;

public class DatabaseConnection {

    private DatabaseConnection() {
    }

    private static Connection con = null;

    static final String url = "jdbc:mysql://localhost:3306/org";
    static final String user = "root";
    static final String pass = "1234";

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("Connected to database server "
                        + con.getMetaData().getDatabaseProductName()
                        + " version: "
                        + con.getMetaData().getDatabaseProductVersion());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            System.out.println("Closing connection...");
            try {
                con.close();
                System.out.println("Connection Closed");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}