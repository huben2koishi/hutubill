package com.huben.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author koishi
 */
public class DBUtil {
    private static String ip = "127.0.0.1";
    public static int port = 3306;
    public static String database = "hutubill";
    public static String username = "root";
    public static String password = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s", ip, port, database);
        return DriverManager.getConnection(url, username, password);
    }
}
