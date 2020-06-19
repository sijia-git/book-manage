package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import book.modle.Reader;
import book.utils.DbUtil;

/**
 * @Description 连接数据库工具类
 */


public class ReaderDao {
	/**
	 * 根据读者ID查询读者信息
	 * 
	 * @param id
	 *            
	 * @return reader
	 */
	public Reader getReaderById(String reader_id) {
		Connection connection = DbUtil.getConnection();
		String sql = "select reader_id,name,sex,birth,address,telcode from reader_info where reader_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, reader_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {// 存在读者，封装返回
				Reader reader = new Reader(rs.getInt("reader_id"), rs.getString("name"), 
						rs.getString("sex"),rs.getString("birth"),rs.getString("address"),rs.getString("telcode"));
				DbUtil.close(connection, ps);// 关闭连接
				return reader;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;// 没有读者
	}

	/**
	 * 根据读者ID删除读者
	 * 
	 * @param id
	 *            
	 * @return 是否删除成功
	 */
	public boolean deleteReaderById(String reader_id) {
		Connection connection = DbUtil.getConnection();
		String sql = "delete from reader_info where reader_id=?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, reader_id);
			if (!ps.execute()) {// 删除成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询所有读者信息
	 * 
	 * @return 返回读者列表
	 */
	
	public ArrayList<Reader> getReaderList() {
		ArrayList<Reader> readers = new ArrayList<Reader>();// 创建用于存放读者信息的ArrayList集合
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "select reader_id,name,sex,birth,address,telcode from reader_info";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// 存在读者，封装返回
				Reader reader = new Reader(rs.getInt("reader_id"), rs.getString("name"), 
						rs.getString("sex"),rs.getString("birth"),rs.getString("address"),rs.getString("telcode"));
				readers.add(reader);
			}
			DbUtil.close(connection, ps);// 关闭连接
			return readers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	/**
	 * 插入读者
	 * 
	 * @param reader
	 *          
	 * @return 是否插入成功
	 */
	public boolean insertReader(Reader reader) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "insert into reader_info(reader_id,name,sex,birth,address,telcode)values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, reader.getReader_id());
			ps.setString(2, reader.getName());
			ps.setString(3, reader.getSex());
			ps.setString(4, reader.getBirth());
			ps.setString(5, reader.getAddress());
			ps.setString(6, reader.getTelcode());
			
			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}
	
	/**
	 * 修改读者信息
	 * 
	 * @param reader
	 *            
	 * @return 是否修改完成
	 */
	public boolean updateReader(Reader reader) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "update reader_info set name=?,sex=?,birth=?,address=?,telcode=? where reader_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, reader.getName());
			ps.setString(2, reader.getSex());
			ps.setString(3, reader.getBirth());
			ps.setString(4, reader.getAddress());
			ps.setString(5, reader.getTelcode());
			ps.setInt(6, reader.getReader_id());
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
	

