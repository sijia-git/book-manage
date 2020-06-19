package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import book.modle.User;
import book.utils.DbUtil;

/**
 * @Description 连接数据库工具类
 */
public class UserDao {
	/**
	 * 根据用户账号查询用户
	 * 
	 * @param accout
	 *           
	 * @return user
	 */
	public User getUserByAccout(String accout) {
		Connection connection = DbUtil.getConnection();
		String sql = "select accout,pass from t_user where accout=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, accout);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {// 存在用户，封装用户返回
				User user = new User(rs.getString("accout"), rs.getString("pass"));
				DbUtil.close(connection, ps);// 关闭连接
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 插入用户
	 * 
	 * @param account
	 *          
	 * @return 是否插入成功
	 */
	
	public boolean insertUser(User user) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "insert into t_user(accout,pass)values(?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getAccout());
			ps.setString(2, user.getPass());
			if (!ps.execute()) {// 成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}

	/**
	 * 删除用户
	 * 
	 * @param account
	 *          
	 * @return 是否删除成功
	 */
	public boolean deleteUserByAccout(String account) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "delete from t_user where accout=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			if (!ps.execute()) {// 成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 修改用户
	 * 
	 * @param account
	 *          
	 * @return 是否删除成功
	 */
	public boolean updateUserByAccout(User user) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "update t_user set pass=? where accout=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getPass());
			ps.setString(2, user.getAccout());
			if (!ps.execute()) {// 成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
