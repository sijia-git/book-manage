package book.view;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import book.dao.UserDao;
import book.modle.User;

/**
 * @Description 登陆主界面
 */
public class UserFrame extends JFrame implements ActionListener {
	// 定义变量
	private JLabel jLabelAccout, jLabelpass;// 账号 密码标签
	private JButton loginBtn, registBtn,cancelBtn,updateBtn;// 登陆、注册、注销、修改按钮
	private JTextField jFieldAccout, jFieldpass;// 账号 密码文本框
	private UserDao userDao = new UserDao();// 定义用户操作对象
	
	
	// 初始化窗体

	public void init() {   
		setVisible(true);
		this.add(new JLabel(new ImageIcon("images/index2.jpg")));
		FlowLayout f = new FlowLayout();// 流式佈局
		f.setAlignment(FlowLayout.CENTER);// 设置组件的对齐方式，默认为居中显示组件
		f.setHgap(40);// 设置组件的水平间距
		f.setVgap(10);// 设置组件的垂直间距
		
		jLabelAccout = new JLabel("账号");
		add(jLabelAccout);
		jFieldAccout = new JTextField(18);// 输入框 参数代表长度
		add(jFieldAccout);
		jLabelpass = new JLabel("密码");
		add(jLabelpass);
		jFieldpass = new JTextField(18);// 输入框 参数代表长度
		add(jFieldpass);
		loginBtn = new JButton("登陆系统");// 直接在创建时候给定参数
		loginBtn.addActionListener(this);// 设置事件监听
		loginBtn.setActionCommand("login");// 设置监听识别命令
		add(loginBtn);
		registBtn = new JButton();
		registBtn.setText("注册账户");
		registBtn.addActionListener(this);// 设置事件监听
		registBtn.setActionCommand("regist");// 设置监听识别命令
		add(registBtn);
		cancelBtn = new JButton();
		cancelBtn.setText("注销账户");
		cancelBtn.addActionListener(this);// 设置事件监听
		cancelBtn.setActionCommand("cancelled");// 设置监听识别命令	
		add(cancelBtn);
		updateBtn = new JButton();
		updateBtn.setText("修改账户");
		updateBtn.addActionListener(this);// 设置事件监听
		updateBtn.setActionCommand("update");// 设置监听识别命令	
		add(updateBtn);
		setLayout(f);// 设置组件容器采用流布局管理器
		// 初始化窗口
		
		this.setTitle("HBB的图书管理系统-登陆");// 窗口标题
		this.setSize(640, 500);// 窗体大小
		this.setVisible(true);// 设置窗体可见
	
		this.setIconImage((new ImageIcon("images/logo2.jpg")).getImage());// 设置图标
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置可关闭进程
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;// 获得屏幕宽度
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;// 获得屏幕高度
		this.setLocation((width - this.getWidth()) / 2,
			     (height - this.getHeight()) / 2);// 居中显示组件位置
		this.setResizable(true);// 可改变窗体大小
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accout = jFieldAccout.getText().trim().toString();// 获取账户，去掉后面得空格
		String pass = jFieldpass.getText().trim().toString();// 获取密码，去掉后面得空格
		if (e.getActionCommand().equals("login")) {// 登陆
			User user = userDao.getUserByAccout(accout); // 查询
			if (user != null) {// 用户存在,验证密码和输入得是否相等
				if (user.getPass().equals(pass)) {
					this.dispose();//关闭当前窗口
					//this.setVisible(false);// 关闭当前窗口
					new BookFrame().init();// 打开图书主窗体
				} else {
					JOptionPane.showMessageDialog(null, "密码错误", "信息", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {// 用户不存在
				JOptionPane.showMessageDialog(null, "用户不存在", "信息", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getActionCommand().equals("regist")) {
			if (accout.equals("") || pass.equals("")) {
				JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "信息", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// 账户是否重复
				if (userDao.getUserByAccout(accout) != null) {
					JOptionPane.showMessageDialog(null, "账户重复", "信息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					User user = new User(accout, pass);
					boolean flag = userDao.insertUser(user);
					if (flag) {
						JOptionPane.showMessageDialog(null, "注册成功，请登陆", "信息", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "注册异常", "信息", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}else if (e.getActionCommand().equals("cancelled")) {
		if (accout.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "信息", JOptionPane.INFORMATION_MESSAGE);
		} else {
			// 账户是否重复
			if (userDao.getUserByAccout(accout) != null) {
				JOptionPane.showMessageDialog(null, "账户重复", "信息", JOptionPane.INFORMATION_MESSAGE);
			} else {
				User user = new User(accout, pass);
				boolean flag = userDao.deleteUserByAccout(accout);
				if (flag) {
					JOptionPane.showMessageDialog(null, "注销用户成功", "信息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "注销异常", "信息", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
		}else if (e.getActionCommand().equals("update")) {
		if (accout.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "信息", JOptionPane.INFORMATION_MESSAGE);
		} else {
				User user = new User(accout, pass);
				boolean flag = userDao.deleteUserByAccout(accout);
				if (flag) {
					JOptionPane.showMessageDialog(null, "密码修改成功", "信息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "密码修改异常", "信息", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
		}
	}

