import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBC_Demo1 {

    @Test
    public void demo1(){
        try{
         // 1. 加载驱动
            DriverManager.registerDriver(new Driver());
         // 2. 获得连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://39.106.101.244:3306/susu?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8",
                    "root","3228516");
         // 3. 创建执行SQL语句对象，并且执行SQL
            // 3.1 创建执行SQL对象
                String sql = "select * from user";
                Statement statement = conn.createStatement();
            // 3.2 执行SQL
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    int age = resultSet.getInt("age");
                    System.out.println(id+" "+username+" "+password+" "+age);
                }
         // 4. 释放资源
            resultSet.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
