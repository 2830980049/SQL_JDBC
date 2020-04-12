import org.junit.jupiter.api.Test;

import java.sql.*;


public class tests {
    @Test
    public void test1() {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://39.106.101.244:3306/susu?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8",
                    "root","3228516");
            String sql = "update  user set age = 20 where id = '3'";
            statement = conn.createStatement();
            int flag = statement.executeUpdate(sql);
            System.out.println(flag);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(conn != null){
                try{
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                conn = null;
            }

            if(statement != null) {
                try{
                    statement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                statement = null;
            }

            if (rs != null){
                try{
                    rs.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                rs = null;
            }
        }
    }

}
