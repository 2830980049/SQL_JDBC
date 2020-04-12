package com.edu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBC_Utils {
    public static final String driverClass;
    public static final String url;
    public static final String username;
    public static final String passowrd;
    static {

        // 加载属性文件
        Properties prop = new Properties();
        // 如何获得属性文件输入流
        // 通常情况下使用类的加载器进行获取
        InputStream is = JDBC_Utils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverClass = prop.getProperty("driverClass");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        passowrd = prop.getProperty("passowrd");
    }

    /***
     * 注册驱动
     */
    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(driverClass);
    }
    /**
     * 获得连接
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        loadDriver();
        Connection conn = DriverManager.getConnection(url, username,passowrd);
        return conn;
    }

    /***
     * 资源释放
     */
    public static void release(Statement statement,Connection conn){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public static void release(Statement statement, Connection conn, ResultSet rs){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }
}
