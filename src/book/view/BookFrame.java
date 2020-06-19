package book.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * @Description 图书管理界面类
 * @Description AWT+Swing用户界面编程
 */

//定义窗体类BookFrame继承JFrame，实现ActionListener接口
public class BookFrame extends JFrame implements ActionListener {
	 
	// 定义顶部菜单栏
	private JMenuBar menuBar;
	// 定义顶部菜单：首页图书管理、租赁管理、读者管理、关于系统四个菜单
	private JMenu menuManage, menuAbout,menuReader,menuLend;
	// 定义菜单项：图书(读者）列表 、更新、退出以及关于
	private JMenuItem itemListr, itemListl,itemListlb,itemReader,itemLend,itemLendbook, itemAbout, itemExitlb,itemExitr,itemExitl;
	//定义容器
	private Container container;
	//定义卡片布局
	private CardLayout cardlayout;
	//定义首页、图书更新、图书列表三个面板
	private IndexPanel indexPanel;
	private CrudBooklendPanel crudBooklendPanel;
	private ListPanel3 listPanel3;
	//定义读者借阅列表、读者借阅更新三个面板
	private CrudLendPanel crudLendPanel;
	private ListPanel4 listPanel4;
	//定义读者更新、读者列表两个面板
	private CrudReaderPanel crudReaderPanel;
	private ListPanel2 listPanel2;
	
	/**
	 * 初始化窗体
	 */

	public void init() {
		// 调用用于初始化内容窗体的方法
		initFrame();
		this.setTitle("HBB的图书管理系统");//窗体名字
		this.setSize(1200, 700);//窗体大小
		// 设置图标
		this.setIconImage((new ImageIcon("images/logo2.jpg")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置可关闭进程
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;// 获得屏幕宽度
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;// 获得屏幕高度
		this.setLocation((width - this.getWidth()) / 2,
			     (height - this.getHeight()) / 2);// 居中显示组件位置
		this.setVisible(true);// 设置窗体可见
		this.setResizable(false);// 可改变窗体大小
	}

	/**
	 * 初始化窗体内容
	 */
	public void initFrame() {
		// 实例化菜单栏
		menuBar = new JMenuBar();
		menuManage = new JMenu("图书管理");
		menuLend = new JMenu("借阅管理");
		menuReader = new JMenu("读者管理");
		menuAbout = new JMenu("关于系统");
		
		// 实例化图书管理菜单项：图书列表、图书更新、退出，并添加事件监听
		itemLendbook = new JMenuItem("图书管理");
		itemLendbook.addActionListener(this);// 设置事件监听器
		itemLendbook.setActionCommand("itemlendbook");
		
		itemListlb = new JMenuItem("图书列表");
		itemListlb.addActionListener(this);// 设置监听
		itemListlb.setActionCommand("listlendbook");
		
		itemExitlb = new JMenuItem("退出");
		itemExitlb.addActionListener(this);// 设置监听
		itemExitlb.setActionCommand("exit");
		
		// 实例化租赁管理菜单项：图书列表、租赁列表、退出，并添加事件监听
		itemLend = new JMenuItem("借阅信息更新");
		itemLend.addActionListener(this);// 设置事件监听器
		itemLend.setActionCommand("itemlend");
		
		itemListl = new JMenuItem("借阅列表");
		itemListl.addActionListener(this);// 设置监听
		itemListl.setActionCommand("listlend");
		
		itemExitl = new JMenuItem("退出");
		itemExitl.addActionListener(this);// 设置监听
		itemExitl.setActionCommand("exit");
		
		// 实例化读者管理菜单项：读者列表、读者更新、退出，并添加事件监听
		itemReader = new JMenuItem("读者更新");
		itemReader.addActionListener(this);// 设置监听
		itemReader.setActionCommand("itemreader");
		
		itemListr = new JMenuItem("读者列表");
		itemListr.addActionListener(this);// 设置监听
		itemListr.setActionCommand("listreader");
		
		itemExitr = new JMenuItem("退出");
		itemExitr.addActionListener(this);// 设置监听
		itemExitr.setActionCommand("exit");
		// 实例化关于系统菜单项：
		itemAbout = new JMenuItem("关于");
		itemAbout.addActionListener(this);// 设置监听
		itemAbout.setActionCommand("about");
		// 添加图书管理菜单项
		menuManage.add(itemListlb);
		menuManage.add(itemLendbook);
		menuManage.add(itemExitlb);
		// 添加租赁管理菜单项
		menuLend.add(itemListl);
		menuLend.add(itemLend);
		menuLend.add(itemExitl);
		// 添加读者管理菜单项
		menuReader.add(itemListr);
		menuReader.add(itemReader);
		menuReader.add(itemExitr);
		// 添加关于系统菜单项
		menuAbout.add(itemAbout);
		// 添加菜单栏
		menuBar.add(menuManage);
		menuBar.add(menuLend);
		menuBar.add(menuReader);
		menuBar.add(menuAbout);
		// 把菜单栏添加到窗体
		this.setJMenuBar(menuBar);
		// 实例化增删改查面板
		indexPanel = new IndexPanel();
		// 实例化卡片布局
		cardlayout = new CardLayout();
		// 窗口容器中添加組件（使用边界布局）
		container = getContentPane();
		container.setLayout(cardlayout);
		// 将首页面板放进内容面板
		container.add(indexPanel, "indexPanel");
		
	}

	/*
	 * 监听按钮的事件方法
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 事件监听
		if (e.getActionCommand().equals("about")) {// 关于
			JOptionPane.showMessageDialog(BookFrame.this, "这是HBB的图书管理系统，有问题请联系系统管理员：3181317134");
		
		} else if (e.getActionCommand().equals("exit")) {// 退出
			System.exit(0);
		} else if (e.getActionCommand().equals("listlendbook")) {
			listPanel3 = new ListPanel3();
			container.add(listPanel3, "listPanel3");
			cardlayout.show(container, "listPanel3");
		} else if (e.getActionCommand().equals("listlend")) {
			listPanel4 = new ListPanel4();
			container.add(listPanel4, "listPanel4");
			cardlayout.show(container, "listPanel4");
		} else if (e.getActionCommand().equals("itemlend")) {
			crudLendPanel = new CrudLendPanel();
			container.add(crudLendPanel,"crudLendPanel");
			cardlayout.show(container, "crudLendPanel");
		} else if (e.getActionCommand().equals("itemlendbook")) {
			crudBooklendPanel = new CrudBooklendPanel();
			container.add(crudBooklendPanel,"crudBooklendPanel");
			cardlayout.show(container, "crudBooklendPanel");
		} else if (e.getActionCommand().equals("listreader")) {
			//实例化读者列表面板，将其放进容器 使用卡片布局展示
			listPanel2 = new ListPanel2();
			container.add(listPanel2, "listPanel2");
			cardlayout.show(container, "listPanel2");
		} else if (e.getActionCommand().equals("itemreader")) {
			//实例化读者增删改查面板，将其放进容器 使用卡片布局展示
			crudReaderPanel = new CrudReaderPanel();
			container.add(crudReaderPanel,"crudReaderPanel");
			cardlayout.show(container, "crudReaderPanel");
		}
	}
}
