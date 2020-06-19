package book.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description JDBC编程
 * 
 */
public class DbUtil {
	private static final String USER = "root";//数据库用户名
	private static final String UPWD = "XSFHHPP";//数据库密码
	private static final String URL = "jdbc:mysql://localhost:3306/hbb_book?useUnicode=true&characterEncoding=utf8";//数据库URL
	private static final String DRIVER = "com.mysql.jdbc.Driver";//MySQL驱动类所对应的字符串
	
	static {
		try {
			//1.用Class类的forName静态方法来加载数据库驱动
			Class.forName(DRIVER);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();//命令行打印异常信息在程序中出错的位置和原因
		}
	}
	public static Connection getConnection(){
		try {
			//2.通过DriverManager获取数据库连接
			return DriverManager.getConnection(URL, USER, UPWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
  //关闭连接和创建的工具接口资源
	public static void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//关闭连接、创建的工具接口、返回的结果集资源
	public static void close(Connection connection, Statement statement, ResultSet rs) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

