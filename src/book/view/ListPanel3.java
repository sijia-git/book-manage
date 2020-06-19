package book.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import book.dao.BooklendDao;
import book.modle.Booklend;

/**
 * 自定义图书租赁列表面板
 */
public class ListPanel3 extends JPanel {
	// 从数据库中取出信息
	// rowData用来存放行数据
	// columnNames存放列名
	Vector rowData, columnNames;
	JTable jt = null;//创建二维单元表JTable,将数据以表格形式描述
	JScrollPane jsp = null;

	// 构造函数
	public ListPanel3() {
		ArrayList<Booklend> booklends = new BooklendDao().getBookList();
		columnNames = new Vector();//创建表头
		columnNames.add("图书编号");
		columnNames.add("图书名称");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("简介");
		columnNames.add("借阅状态");
		rowData = new Vector();
		for (int i = 0; i < booklends.size(); i++) {
			//实例化每一行数据
			Vector hang = new Vector();
			hang.add(booklends.get(i).getBook_id());
			hang.add(booklends.get(i).getName());
			hang.add(booklends.get(i).getAuthor());
			hang.add(booklends.get(i).getPublish());
			hang.add(booklends.get(i).getIntroduction());
			hang.add(booklends.get(i).getState());
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
	//	jt.setFillsViewportHeight(true);
		this.add(jsp);
	}
	
}

