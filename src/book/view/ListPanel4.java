package book.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import book.dao.BooklendDao;
import book.dao.LendDao;
import book.modle.Lend;

/**
 * 自定义图书租赁信息列表面板
 */
public class ListPanel4 extends JPanel {
	// 从数据库中取出信息
	// rowData用来存放行数据
	// columnNames存放列名
	Vector rowData, columnNames;
	JTable jt = null;//创建二维单元表JTable,将数据以表格形式描述
	JScrollPane jsp = null;

	// 构造函数
	public ListPanel4() {
		ArrayList<Lend> lends = new LendDao().getLendList();
		columnNames = new Vector();//创建表头
		columnNames.add("服务编号");
		columnNames.add("图书ID");
		columnNames.add("读者ID");
		columnNames.add("借书时间");
		columnNames.add("还书时间");
		rowData = new Vector();
		for (int i = 0; i < lends.size(); i++) {
			//实例化每一行数据
			Vector hang = new Vector();
			hang.add(lends.get(i).getSernum());
			hang.add(lends.get(i).getBook_id());
			hang.add(lends.get(i).getReader_id());
			hang.add(lends.get(i).getLend_date());
			hang.add(lends.get(i).getBack_date());
			// 加入到rowData
			rowData.add(hang);
		}
		// 初始化Jtable
		jt = new JTable(rowData, columnNames);
		// 初始化 jsp
		jsp = new JScrollPane(jt);
		jt.setPreferredScrollableViewportSize(new Dimension(1100, 600));//设置JTable的宽度和高度
		jt.setRowHeight(30);//行高
		jt.setRowMargin(10);//行间隔
		jt.selectAll();
		jt.setBackground(Color.PINK);//背景颜色
		jt.setSelectionForeground(Color.BLACK);//字体颜色
	//	jt.setFillsViewportHeight(true);
		this.add(jsp);
	}
	
}

