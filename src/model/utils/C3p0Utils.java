package model.utils;

import java.sql.*;


import com.mchange.v2.c3p0.*;

public class C3p0Utils {

	//通过标识名来创建相应连接池
    static ComboPooledDataSource dataSource=new ComboPooledDataSource("mysql");
    //从连接池中取用一个连接
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
            
        } catch (Exception e) {
        	System.out.println("连接出错");
            e.printStackTrace();
                      
        }
		return null;
    }    
    //释放连接回连接池
     public static void close(Connection conn,Statement smt,ResultSet rs){  
            if(rs!=null){  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                	System.out.println("数据库连接关闭出错");
                    e.printStackTrace();           
                }  
            }  
            if(smt!=null){  
                try {  
                    smt.close();  
                } catch (SQLException e) {  
                	System.out.println("数据库连接关闭出错");
                    e.printStackTrace();    
                }  
            }  
      
            if(conn!=null){  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                	System.out.println("数据库连接关闭出错");
                    e.printStackTrace();  
                }  
            }  
        }  
}
