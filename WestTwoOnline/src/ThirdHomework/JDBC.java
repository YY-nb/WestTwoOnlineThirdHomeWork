package ThirdHomework;

import java.sql.*;

public class JDBC {

    //静态代码块注册驱动
     static {
         try {
             Class.forName("com.mysql.jdbc.Driver");
             System.out.println("驱动成功！");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
     }
     //获取连接
    public static Connection getConnection() throws SQLException {
        Connection connection=null;
        connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&&useSSL=false","root","123qwe321qwe");

        return connection;
    }
    //关闭资源的三个重载方法
    public  static void close(Connection connection){
         if(connection!=null){
             try {
                 connection.close();
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }

    }
    public  static void close(PreparedStatement ps){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    public  static void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }




}
