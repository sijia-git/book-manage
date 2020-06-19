package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.modle.Lend;
import book.utils.DbUtil;

public class LendDao {
	public ArrayList<Lend> getLendList() {
		ArrayList<Lend> lends = new ArrayList<Lend>();// 创建用于存放借阅信息的ArrayList集合
		Connection connection = DbUtil.getConnection();// 获得数据库连接
		String sql = "select sernum,book_id,reader_id,lend_date,back_date from lend_list";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lend lend = new Lend(rs.getString("sernum"), rs.getString("book_id"),rs.getString("reader_id"), rs.getString("lend_date"),rs.getString("back_date"));
				lends.add(lend);
			}
			DbUtil.close(connection, ps);// 关闭连接
			return lends;//返回借阅列表
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;// 没有借阅
	}
	
	public static Lend getLendBySernum(String sernum) {
		Connection connection = DbUtil.getConnection();//获取数据库连接
		String sql = "select sernum,book_id,reader_id,lend_date,back_date from lend_list where sernum=?";//执行SQL语句
		try {
			PreparedStatement ps = connection.prepareStatement(sql);//创建prepareStatement对象
			ps.setString(1, sernum);
			ResultSet rs = ps.executeQuery();//执行查询语句，并返回查询结果 rs 对象
			if (rs.next()) {// 存在图书，封装返回
				Lend lend = new Lend(rs.getString("sernum"), rs.getString("book_id"),rs.getString("reader_id"), rs.getString("lend_date"),rs.getString("back_date"));
				DbUtil.close(connection, ps);// 关闭连接
				return lend;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean updateLend(Lend lend) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "update lend_list set book_id=?,reader_id=?,lend_date=?,back_date=? where sernum=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, lend.getBook_id());
			ps.setString(2, lend.getReader_id());
			ps.setString(3, lend.getLend_date());
			ps.setString(4, lend.getBack_date());
			ps.setString(5, lend.getSernum());
			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}

	public boolean insertLend(Lend lend) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "insert into lend_list(sernum,book_id,reader_id,lend_date,back_date)values(?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, lend.getSernum());
			ps.setString(2, lend.getBook_id());
			ps.setString(3, lend.getReader_id());
			ps.setString(4, lend.getLend_date());
			ps.setString(5, lend.getBack_date());
			
			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}

	public boolean deleteLendBySernum(String sernum) {
		Connection connection = DbUtil.getConnection();
		String sql = "delete from lend_list where sernum=?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, sernum);
			if (!ps.execute()) {// 若无查询结果，则删除成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



}
