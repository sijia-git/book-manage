package book.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import book.dao.ReaderDao;
import book.modle.Reader;

/**
 * @Description 修改读者信息面板
 */


public class CrudReaderPanel extends JPanel implements ActionListener {
	// 定义首页按钮、读者列表按钮、 其他功能按钮,增加读者、删除读者、修改读者、查询读者
	private JButton addBtn, deleteBtn, updateBtn, findBtn;
	// 定义标签 底部信息标签：读者ID、 姓名、 性别、 生日、籍贯、电话
	private JLabel idLabel, nameLabel, sexLabel,birthLabel,addressLabel, telcodeLabel;
	// 定义文本框
	private JTextField idJTextField, nameJTextField, sexJTextField, birthJTextField,telcodeJTextField, addressJTextField;
	// 定义文本对象
	private String readerIdText;
	private String readerNameText;
	private String readerSexText;
	private String readerBirthText;
	private String readerAddressText;
	private String readerTelcodeText;
	private Integer idreader;
	// 定义对象ReaderDao
	private ReaderDao readerDao;

	//构造器
	public CrudReaderPanel() {
		readerDao = new ReaderDao();//实例化读者操作对象
		// 实例化增删改查按钮
		addBtn = new JButton("增加读者");
		addBtn.addActionListener(this);// 设置读者增加按钮监听
		addBtn.setActionCommand("addreader");
		deleteBtn = new JButton("删除读者");
		deleteBtn.addActionListener(this);// 设置读者删除按钮监听
		deleteBtn.setActionCommand("deletereader");
		updateBtn = new JButton("修改读者");
		updateBtn.addActionListener(this);// 设置读者修改按钮监听
		updateBtn.setActionCommand("updatereader");
		findBtn = new JButton("查询读者");
		findBtn.addActionListener(this);// 设置图书查询按钮监听
		findBtn.setActionCommand("findreader");
		// 实例化读者标签
		idLabel = new JLabel("读者ID");
		nameLabel = new JLabel("读者姓名");
		sexLabel = new JLabel("读者性别");
		birthLabel = new JLabel("出生日期");
		addressLabel = new JLabel("家庭住址");
		telcodeLabel = new JLabel("联系方式");
		// 实例化文本框
		idJTextField = new JTextField(20);
		nameJTextField = new JTextField(20);
		sexJTextField = new JTextField(20);
		birthJTextField = new JTextField(20);
		addressJTextField = new JTextField(20);
		telcodeJTextField = new JTextField(20);
		this.setLayout(new GridLayout(8, 2, 2, 2));
		
		// 给增删改查面板标签以及文本框
		this.add(idLabel);
		this.add(idJTextField);
		this.add(nameLabel);
		this.add(nameJTextField);
		this.add(sexLabel);
		this.add(sexJTextField);
		this.add(birthLabel);
		this.add(birthJTextField);
		this.add(addressLabel);
		this.add(addressJTextField);
		this.add(telcodeLabel);
		this.add(telcodeJTextField);
		// 给增删改查面板添加按钮
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(updateBtn);
		this.add(findBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("addreader")) {// 添加读者
			addreader();
		} else if (e.getActionCommand().equals("deletereader")) {// 删除读者
			deleteReader();
			
		} else if (e.getActionCommand().equals("updatereader")) {// 修改读者
			updateReader();
		} else if (e.getActionCommand().equals("findreader")) {// 查询读者
			findReader();
		}
	}
	

	/**
	 * 查询读者
	 */
	private void findReader() {
		readerIdText = idJTextField.getText().trim().toString();
		Reader reader = readerDao.getReaderById(readerIdText);
		if (readerIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者ID不能为空");
		} else if (reader != null) {// 当前输入的读者ID存在
			try {
				nameJTextField.setText(reader.getName());// 填充读者名字文本框
				sexJTextField.setText(reader.getSex() );
				birthJTextField.setText(reader.getBirth());
				addressJTextField.setText(reader.getAddress() );
				telcodeJTextField.setText(reader.getTelcode());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "读者查询异常  请联系系统管理员");
			}
		} else {
			JOptionPane.showMessageDialog(this, "该读者不存在");
		}

	}

	/**
	 * 修改读者信息
	 */
	private void updateReader() {
		readerIdText = idJTextField.getText().trim().toString();
		readerNameText = nameJTextField.getText().trim().toString();
		readerSexText = sexJTextField.getText().trim().toString();
		readerBirthText = birthJTextField.getText().trim().toString();
		readerAddressText = addressJTextField.getText().trim().toString();
		readerTelcodeText = telcodeJTextField.getText().trim().toString();
		if (readerIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者ID不能为空");
		} else if (readerNameText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者姓名不能为空");
		} else if (readerSexText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者性别不能为空");
		} else if (readerBirthText.equals("")) {
			JOptionPane.showMessageDialog(this, "出生日期不能为空");
		} else if (readerAddressText.equals("")) {
			JOptionPane.showMessageDialog(this, "家庭住址不能为空");
		} else if (readerTelcodeText.equals("")) {
			JOptionPane.showMessageDialog(this, "联系方式不能为空");
		} else{
			if (readerDao.getReaderById(readerIdText) == null) {// 读者不存在
				JOptionPane.showMessageDialog(this, "输入正确的读者ID");
			}else{
				try{
					idreader = Integer.parseInt(readerIdText);
					readerDao.updateReader(new Reader(idreader,readerNameText,readerSexText,readerBirthText,readerAddressText,readerTelcodeText));
					JOptionPane.showMessageDialog(this, "读者信息修改成功");
				}catch(Exception e){
					JOptionPane.showMessageDialog(this, "请输入正确的信息");
					e.printStackTrace();
					
			}
		}

	}
	
	}

	/**
	 * 删除读者
	 */
	private void deleteReader() {
		readerIdText = idJTextField.getText().trim().toString();
		if (readerIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者ID不能为空");
		} else if (readerDao.getReaderById(readerIdText) != null) {
			try {
				readerDao.deleteReaderById(readerIdText);
				JOptionPane.showMessageDialog(this, "读者删除成功");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "读者删除异常  请联系系统管理员");
			}
		} else {
			JOptionPane.showMessageDialog(this, "读者不存在");
		}

	}

	/**
	 * 增加读者
	 */
	private void addreader() {
		
		readerIdText = idJTextField.getText().trim().toString();
		readerNameText = nameJTextField.getText().trim().toString();
		readerSexText = sexJTextField.getText().trim().toString();
		readerBirthText = birthJTextField.getText().trim().toString();
		readerAddressText = addressJTextField.getText().trim().toString();
		readerTelcodeText = telcodeJTextField.getText().trim().toString();
		if (readerIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者ID不能为空");
		} else if (readerNameText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者姓名不能为空");
		} else if (readerSexText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者性别不能为空");
		} else if (readerBirthText.equals("")) {
			JOptionPane.showMessageDialog(this, "出生日期不能为空");
		} else if (readerAddressText.equals("")) {
			JOptionPane.showMessageDialog(this, "家庭住址不能为空");
		} else if (readerTelcodeText.equals("")) {
			JOptionPane.showMessageDialog(this, "联系方式不能为空");
		} else{
			if(readerDao.getReaderById(readerIdText)!=null){//ID重复
				JOptionPane.showMessageDialog(this, "读者ID重复");
			}else{
				try{
					idreader = Integer.parseInt(readerIdText);
					readerDao.insertReader(new Reader(idreader,readerNameText,readerSexText,readerBirthText,readerAddressText,readerTelcodeText));
					JOptionPane.showMessageDialog(this, "读者添加成功");
				}catch(Exception e){
						JOptionPane.showMessageDialog(this, "输入正确的读者信息");
						e.printStackTrace();
				}
				}
	}
	}
}

