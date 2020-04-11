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
}
