package com.example.wechat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBGameProcessUtil {
    private static final Properties props = new Properties();

    static {
        try {
            props.load(DBUserInformationUtil.class.getClassLoader().getResourceAsStream("db_game_process.properties"));
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new RuntimeException("数据库驱动加载失败", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, java.sql.PreparedStatement stmt) {
        close(stmt);
        close(conn);
    }

    public static void close(Connection conn, java.sql.PreparedStatement stmt, java.sql.ResultSet rs) {
        close(rs);
        close(stmt);
        close(conn);
    }

    public static void close(java.sql.PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(java.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

