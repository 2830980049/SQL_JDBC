package com.edu.jdbc;

import com.edu.utils.JDBC_Utils;
import com.edu.utils.JDBC_Utils_2;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBC_Demo1 {

    @Test
    public void demo1(){
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
         // 1. 加载驱动
            //DriverManager.registerDriver(new Driver()); //会导致注册2次
            Class.forName("com.mysql.cj.jdbc.Driver"); //加载这个类
            // 2. 获得连接
            conn = DriverManager.getConnection("jdbc:mysql://39.106.101.244:3306/susu?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8",
                    "root","3228516");
         // 3. 创建执行SQL语句对象，并且执行SQL
            // 3.1 创建执行SQL对象
                String sql = "select * from user";
                statement = conn.createStatement();
            // 3.2 执行SQL
                rs = statement.executeQuery(sql);
                while (rs.next()){
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    int age = rs.getInt("age");
                    System.out.println(id+" "+username+" "+password+" "+age);
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // 4. 释放资源
            if(rs != null){
                try {
                    rs.close();
                }
                catch (SQLException e){
                    rs = null;
                }
            }
            if (statement != null){
                try {
                    statement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                statement = null;
            }
            if (conn != null){
                try {
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                conn = null; //垃圾回收机制更早回收
            }
        }
    }

    @Test
    public void update_delete_select() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://39.106.101.244:3306/susu?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8",
                "root","3228516");
        String sql = "delete from user where username = '苏苏12'";
        PreparedStatement pres = conn.prepareStatement(sql);
        int s = pres.executeUpdate();
        System.out.println(s);
        conn.setAutoCommit(true);
        conn.close();
        pres.close();
    }

    @Test
    public void demo2(){
        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://39.106.101.244:3306/susu?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8",
                    "root","3228516");
            statement= conn.createStatement();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(conn != null){
                try {
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                conn = null;
            }
            if(statement != null){
                try {
                    statement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                statement = null;
            }
        }
    }

    @Test
    public void demo3(){
        Connection con = null;
        Statement statement = null;
        try{
            con = JDBC_Utils.getConnection();
            statement = con.createStatement();
            String sql = "update user set age = 18 where id = '3'";
            int flag = statement.executeUpdate(sql);
            System.out.println(flag);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Utils.release(statement,con);
        }
    }

    /***
     * JDBC 避免注入漏洞
     */
    @Test
    public void demo5(){
        Connection con = null;
        PreparedStatement  pres = null;
        ResultSet rs = null;
        try {
            con = JDBC_Utils.getConnection();
            String sql = "select username,sex,age from user where username = ? and password = ?";
            // 预处理
            pres = con.prepareStatement(sql);
            // 设置参数
            pres.setString(1,"苏苏");
            pres.setString(2,"123");
            // 执行SQL
            rs = pres.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("username"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Utils.release(pres,con,rs);
        }
    }

    /****
     * JDBC 注入漏洞
     */
    @Test
    public void demo4(){
        Connection con = null;
        Statement statement = null;
        try{
            con = JDBC_Utils.getConnection();
            statement = con.createStatement();
            String sql = "update user set age = 18 where id = '3'";
            int flag = statement.executeUpdate(sql);
            System.out.println(flag);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Utils.release(statement,con);
        }
    }

    /***
     * 配置文件 C3P0 连接池
     */
    @Test
    public void c3p0_1(){
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        try{
            // 创建连接池 标识连接池名称
            con = JDBC_Utils_2.getConnection();

            String sql = "select * from user";
            pres = con.prepareStatement(sql);
            // 3.2 执行SQL
            rs = pres.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                System.out.println(id+" "+username+" "+password+" "+age);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Utils.release(pres,con,rs);
        }
    }


    /***
     * 手动 设置C3P0 连接池
     */
    @Test
    public void c3p0(){
        // 创建连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        try{
            // 设置连接池参数
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://39.106.101.244:3306/susu?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8");
            dataSource.setUser("root");
            dataSource.setPassword("3228516");
            dataSource.setMaxPoolSize(20);
            dataSource.setInitialPoolSize(3);


            con = dataSource.getConnection();
            String sql = "select * from user";
            pres = con.prepareStatement(sql);
            // 3.2 执行SQL
            rs = pres.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                System.out.println(id+" "+username+" "+password+" "+age);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Utils.release(pres,con,rs);
        }
    }

}
