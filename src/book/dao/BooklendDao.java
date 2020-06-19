package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.modle.Booklend;
import book.utils.DbUtil;

public class BooklendDao {
	public ArrayList<Booklend> getBookList() {
		ArrayList<Booklend> booklends = new ArrayList<Booklend>();// 创建用于存放图书的ArrayList集合
		Connection connection = DbUtil.getConnection();// 获得数据库连接
		String sql = "select book_id,name,author,publish,introduction,state from book_info";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Booklend booklend = new Booklend(rs.getString("book_id"), rs.getString("name"),rs.getString("author"), rs.getString("publish"),rs.getString("introduction"), rs.getString("state"));
				booklends.add(booklend);
			}
			DbUtil.close(connection, ps);// 关闭连接
			return booklends;//返回图书列表
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;// 没有图书
	}

	public Booklend getBooklendById(String book_id) {
		Connection connection = DbUtil.getConnection();//获取数据库连接
		String sql = "select book_id,name,author,publish,introduction,state from book_info where book_id=?";//执行SQL语句
		try {
			PreparedStatement ps = connection.prepareStatement(sql);//创建prepareStatement对象
			ps.setString(1, book_id);
			ResultSet rs = ps.executeQuery();//执行查询语句，并返回查询结果 rs 对象
			if (rs.next()) {// 存在图书，封装返回
				Booklend booklend = new Booklend(rs.getString("book_id"), rs.getString("name"),rs.getString("author"), rs.getString("publish"),rs.getString("introduction"), rs.getString("state") );
				DbUtil.close(connection, ps);// 关闭连接
				return booklend;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;// 没有图书
	}
	
	public boolean deleteBooklendById(String book_id) {
		Connection connection = DbUtil.getConnection();
		String sql = "delete from book_info where book_id=?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, book_id);
			if (!ps.execute()) {// 若无查询结果，则删除成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertBooklend(Booklend booklend) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "insert into book_info(book_id,name,author,publish,introduction,state)values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, booklend.getBook_id());
			ps.setString(2, booklend.getName());
			ps.setString(3, booklend.getAuthor());
			ps.setString(4, booklend.getPublish());
			ps.setString(5, booklend.getIntroduction());
			ps.setString(6, booklend.getState());
			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}

	public boolean updateBooklend(Booklend booklend) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "update book_info set name=?,author=?,publish=? ,introduction=?,state=? where book_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, booklend.getName());
			ps.setString(2, booklend.getAuthor());
			ps.setString(3, booklend.getPublish());
			ps.setString(4, booklend.getIntroduction());
			ps.setString(5, booklend.getState());
			ps.setString(6, booklend.getBook_id());
			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}












}
