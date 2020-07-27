package model.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelDataExecute {
	private Connection con;
	private Statement statement;
	private ResultSet result;
	
	/**
	 * 构造函数，连接数据库，进行基本的配置操作
	 */
	public ModelDataExecute(){
		con=C3p0Utils.getConnection();
		try {
			statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);//建立Statement对象,可滚动，可更新
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("创建statement失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 基础的查询操作，需要构建sql语句
	 * @param sql 查询语句
	 * @return ResultSet结果集
	 */
	public ResultSet select(String sql){
		try {
			return  statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("查询出错");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 执行insert,delete,update SQL语句,如果影响行数不为0，则执行成功
	 * @param modifySql 增删改 sql语句
	 * @return 是否修改成功
	 */
	public boolean modify(String modifySql){
		try {
			int line=statement.executeUpdate(modifySql);
			if(line!=0) return true;
			else return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("修改错误");
			return false;
		}
	}
	
	/**
	 * 执行insert,delete,update SQL语句,如果影响行数不为0，则执行成功
	 * @param modifySql 增删改 sql语句
	 * @return 修改行数
	 */
	public int modifyByLine(String modifySql){
		try {
			int line=statement.executeUpdate(modifySql);
			return line;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("修改错误");
			return 0;
		}
	}
	/**
	 * 返回插入的id值
	 * @param modifySql
	 * @return
	 */
	public int modifyByAutoId(String modifySql){
		try {
			statement.executeUpdate(modifySql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys(); 
			if ( rs.next() ) { 
			    int key = rs.getInt(1); 
			    return key;
			} 
			else return 0;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("修改错误");
			return 0;
		}
	}
	/**
	 * 处理完成后关闭连接，查询select必须要关闭连接
	 */
	public void close(){
		C3p0Utils.close(con, statement, result);
	}
	
}
