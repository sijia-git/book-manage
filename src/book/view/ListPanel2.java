package book.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import book.dao.ReaderDao;
import book.modle.Reader;

/**
 * 自定义读者列表面板
 */
public class ListPanel2 extends JPanel {
	// 从数据库中取出信息
	// rowData用来存放行数据
	// columnNames存放列名
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// 构造函数
	public ListPanel2() {
		
		ArrayList<Reader> readers = new ReaderDao().getReaderList();
		columnNames = new Vector();
		// 设置列名
		columnNames.add("读者ID");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("生日");
		columnNames.add("户籍");
		columnNames.add("电话");
		
		rowData = new Vector();
		for (int i = 0; i < readers.size(); i++) {
			//实例化每一行数据
			Vector hang = new Vector();
			hang.add(readers.get(i).getReader_id());
			hang.add(readers.get(i).getName());
			hang.add(readers.get(i).getSex());
			hang.add(readers.get(i).getBirth());
			hang.add(readers.get(i).getAddress());
			hang.add(readers.get(i).getTelcode());
			// 加入到rowData
			rowData.add(hang);
		}
		// 初始化Jtable
		jt = new JTable(rowData, columnNames);
		// 初始化 jsp
		jsp = new JScrollPane(jt);
		jt.setPreferredScrollableViewportSize(new Dimension(1000, 600));//设置JTable的宽度和高度
		jt.setRowHeight(30);//行高
		jt.setRowMargin(10);//行间隔
		jt.selectAll();
		jt.setBackground(Color.PINK);//背景颜色
		jt.setSelectionForeground(Color.BLACK);//字体颜色
		this.add(jsp);
	}
}
