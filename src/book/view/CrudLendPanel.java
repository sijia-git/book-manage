        package book.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import book.dao.LendDao;
import book.modle.Lend;

public class CrudLendPanel extends JPanel implements ActionListener {
	private JButton addBtn, deleteBtn, updateBtn, findBtn;
	private JLabel sernumLabel, book_idLabel, reader_idLabel, lend_dateLabel,back_dateLabel,nulldateLabel;
	private JTextField sernumJTextField, book_idJTextField, reader_idJTextField,lend_dateJTextField,back_dateJTextField,nulldateJTextField;
	// 定义文本对象
	private String sernumText;
	private String book_idText;
	private String reader_idText;
	private String lend_dateText;
	private String back_dateText;
	private LendDao lendDao;

	//构造器
	public CrudLendPanel() {
		lendDao = new LendDao();//实例化借阅操作对象
		// 实例化增删改查按钮
		addBtn = new JButton("增加借阅记录");
		addBtn.addActionListener(this);
		addBtn.setActionCommand("addlend");
		deleteBtn = new JButton("删除借阅记录");
		deleteBtn.addActionListener(this);
		deleteBtn.setActionCommand("deletelend");
		updateBtn = new JButton("修改借阅记录");
		updateBtn.addActionListener(this);
		updateBtn.setActionCommand("updatelend");
		findBtn = new JButton("查询借阅记录");
		findBtn.addActionListener(this);
		findBtn.setActionCommand("findlend");
		// 实例化标签
		sernumLabel = new JLabel("借阅编号");
		book_idLabel = new JLabel("图书ID");
		reader_idLabel = new JLabel("读者ID");
		lend_dateLabel = new JLabel("借书日期");
		back_dateLabel = new JLabel("还书日期");
		nulldateLabel = new JLabel("帮助");
		// 实例化文本框
		sernumJTextField = new JTextField(20);
		book_idJTextField = new JTextField(20);
		reader_idJTextField = new JTextField(20);
		lend_dateJTextField = new JTextField(20);
		back_dateJTextField = new JTextField(20);
		nulldateJTextField = new JTextField(20);
		this.setLayout(new GridLayout(8, 2, 2, 2));
		// 给增删改查面板添加标签以及文本框
		this.add(sernumLabel);
		this.add(sernumJTextField);
		this.add(book_idLabel);
		this.add(book_idJTextField);
		this.add(reader_idLabel);	
		this.add(reader_idJTextField);
		this.add(lend_dateLabel);
		this.add(lend_dateJTextField);
		this.add(back_dateLabel);
		this.add(back_dateJTextField);
		this.add(nulldateLabel);
		this.add(nulldateJTextField);
		// 给增删改查面板添加按钮
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(updateBtn);
		this.add(findBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("addlend")) {
			addLend();
		} else if (e.getActionCommand().equals("deletelend")) {
			deleteLend();
		} else if (e.getActionCommand().equals("updatelend")) {
			updateLend();
		} else if (e.getActionCommand().equals("findlend")) {
			findLend();
		}
	}

	/**
	 * 根据借阅编号查询借阅信息
	 */
	private void findLend() {
		sernumText = sernumJTextField.getText().trim().toString();
		Lend lend = LendDao.getLendBySernum(sernumText);
		if (sernumText.equals("")) {
			JOptionPane.showMessageDialog(this, "借阅编号不能为空");
		} else if (lend != null) {// 当前输入的借阅编号存在
			try {
				book_idJTextField.setText(lend.getBook_id());
				reader_idJTextField.setText(lend.getReader_id()+ "");
				lend_dateJTextField.setText(lend.getLend_date() + "");
				back_dateJTextField.setText(lend.getBack_date()+ "");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "借阅信息查询异常  请联系系统管理员");
			}
		} else {
			JOptionPane.showMessageDialog(this, "该借阅不存在");
		}

	}

	/**
	 * 修改借阅信息
	 */
	private void updateLend() {
		sernumText = sernumJTextField.getText().trim().toString();
		book_idText = book_idJTextField.getText().trim().toString();
		reader_idText = reader_idJTextField.getText().trim().toString();
		lend_dateText = lend_dateJTextField.getText().trim().toString();
		back_dateText = back_dateJTextField.getText().trim().toString();
		if (sernumText.equals("")) {
			JOptionPane.showMessageDialog(this, "借阅编号不能为空");
		} else if (book_idText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书ID不能为空");
		} else if (reader_idText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者ID不能为空");
		} else if (lend_dateText.equals("")) {
			JOptionPane.showMessageDialog(this, "借书日期不能为空");
		} else if (back_dateText.equals("")) {
			JOptionPane.showMessageDialog(this, "还书日期不能为空");
		} else {
			if (lendDao.getLendBySernum(sernumText) == null) {// 借阅编号不存在
				JOptionPane.showMessageDialog(this, "输入正确的借阅编号");
			} else {
				try {
					lendDao.updateLend(new Lend(sernumText,book_idText, reader_idText, lend_dateText,back_dateText));
					JOptionPane.showMessageDialog(this, "借阅信息修改成功");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "输入正确的借阅信息");
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 根据借阅编号删除借阅信息
	 */
	private void deleteLend() {
		sernumText = sernumJTextField.getText().trim().toString();
		if (sernumText.equals("")) {
			JOptionPane.showMessageDialog(this, "借阅编号不能为空");
		} else if (lendDao.getLendBySernum(sernumText) != null) {
			try {
				lendDao.deleteLendBySernum(sernumText);
				JOptionPane.showMessageDialog(this, "借阅信息删除成功");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "借阅删除异常  请联系系统管理员");
			}
		} else {
			JOptionPane.showMessageDialog(this, "借阅信息不存在");
		}

	}

	/**
	 * 增加借阅信息
	 */
	private void addLend() {
		sernumText = sernumJTextField.getText().trim().toString();
		book_idText = book_idJTextField.getText().trim().toString();
		reader_idText = reader_idJTextField.getText().trim().toString();
		lend_dateText = lend_dateJTextField.getText().trim().toString();
		back_dateText = back_dateJTextField.getText().trim().toString();
		if (sernumText.equals("")) {
			JOptionPane.showMessageDialog(this, "借阅编号不能为空");
		} else if (book_idText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书ID不能为空");
		} else if (reader_idText.equals("")) {
			JOptionPane.showMessageDialog(this, "读者ID不能为空");
		} else if (lend_dateText.equals("")) {
			JOptionPane.showMessageDialog(this, "借书日期不能为空");
		} else if (back_dateText.equals("")) {
			JOptionPane.showMessageDialog(this, "还书日期不能为空");
		} else {
			if (lendDao.getLendBySernum(sernumText) != null) {// 编号重复
				JOptionPane.showMessageDialog(this, "借阅编号重复");
			} else {
				try {
					lendDao.insertLend(new Lend(sernumText,book_idText, reader_idText, lend_dateText,back_dateText));
					JOptionPane.showMessageDialog(this, "借阅信息增加成功");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "请输入正确的借阅信息");
					e.printStackTrace();
				}
			}
		}

	}
}

