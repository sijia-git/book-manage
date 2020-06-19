package book.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import book.dao.BooklendDao;
import book.modle.Booklend;
/**
 *
 * @author HBB
 *
 */
public class CrudBooklendPanel extends JPanel implements ActionListener{
		private JButton addBtn, deleteBtn, updateBtn, findBtn;
		private JLabel book_idLabel, nameLabel, authorLabel, publishLabel, introductionLabel, stateLabel;
		private JTextField book_idJTextField, nameJTextField, authorJTextField, publishJTextField,introductionJTextField, stateJTextField;
		// 定义文本对象
		private String book_idText;
		private String nameText;
		private String authorText;
		private String publishText;
		private String introductionText;
		private String stateText;
		private BooklendDao booklendDao;

		public CrudBooklendPanel() {
			booklendDao = new BooklendDao();
			addBtn = new JButton("增加图书");
			addBtn.addActionListener(this);// 设置图书增加按钮监听
			addBtn.setActionCommand("addbooklend");
			deleteBtn = new JButton("删除图书");
			deleteBtn.addActionListener(this);// 设置图书删除按钮监听
			deleteBtn.setActionCommand("deletebooklend");
			updateBtn = new JButton("修改图书");
			updateBtn.addActionListener(this);// 设置图书修改按钮监听
			updateBtn.setActionCommand("updatebooklend");
			findBtn = new JButton("查询图书");
			findBtn.addActionListener(this);// 设置图书查询按钮监听
			findBtn.setActionCommand("findbooklend");
			// 实例化标签
			book_idLabel = new JLabel("图书编号");
			nameLabel = new JLabel("图书名称");
			authorLabel = new JLabel("图书作者");
			publishLabel = new JLabel("出版社");
			introductionLabel = new JLabel("图书简介");
			stateLabel = new JLabel("借阅状态");
			// 实例化文本框
			book_idJTextField = new JTextField(20);
			nameJTextField = new JTextField(20);
			authorJTextField = new JTextField(20);
			publishJTextField = new JTextField(20);
			introductionJTextField = new JTextField(20);
			stateJTextField = new JTextField(20);
			this.setLayout(new GridLayout(8, 2, 2, 2));
			// 给增删改查面板添加图书编号 名称 数量 价格标签以及文本框
			this.add(book_idLabel);
			this.add(book_idJTextField);
			this.add(nameLabel);
			this.add(nameJTextField );
			this.add(authorLabel);
			this.add(authorJTextField);
			this.add(publishLabel);
			this.add(publishJTextField);
			this.add(introductionLabel);
			this.add(introductionJTextField);
			this.add(stateLabel);
			this.add(stateJTextField );
			// 给增删改查面板添加图书编号 名称 数量 价格按钮
			this.add(addBtn);
			this.add(deleteBtn);
			this.add(updateBtn);
			this.add(findBtn);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("addbooklend")) {// 添加图书
				addBooklend();
			} else if (e.getActionCommand().equals("deletebooklend")) {// 删除图书
				deleteBooklend();
			} else if (e.getActionCommand().equals("updatebooklend")) {// 修改图书
				updateBooklend();
			} else if (e.getActionCommand().equals("findbooklend")) {// 查询图书
				findBooklend();
			}
		}
		private void findBooklend() {
			book_idText = book_idJTextField.getText().trim().toString();
			Booklend booklend = booklendDao.getBooklendById(book_idText);
			if (book_idText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书编号不能为空");
			} else if (booklend != null) {// 当前输入的图书编号存在
				try {
					nameJTextField.setText(booklend.getName());
					authorJTextField.setText(booklend.getAuthor());
					publishJTextField.setText(booklend.getPublish());
					introductionJTextField.setText(booklend.getIntroduction());
					stateJTextField.setText(booklend.getState());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "图书查询异常  请联系管理员");
				}
			} else {
				JOptionPane.showMessageDialog(this, "图书不存在");
			}

		}

		private void updateBooklend() {
			book_idText = book_idJTextField.getText().trim().toString();
			nameText = nameJTextField.getText().trim().toString();
			authorText = authorJTextField.getText().trim().toString();
			publishText = publishJTextField.getText().trim().toString();
			introductionText = introductionJTextField.getText().trim().toString();
			stateText = stateJTextField.getText().trim().toString();
			if (book_idText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书ID不能为空");
			} else if (nameText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书名称不能为空");
			} else if (authorText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书作者不能为空");
			} else if (publishText.equals("")) {
				JOptionPane.showMessageDialog(this, "出版社不能为空");
			} else if (introductionText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书简介不能为空");
			} else if (stateText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书状态不能为空");
			} else {
				if (booklendDao.getBooklendById(book_idText) == null) {// 图书不存在
					JOptionPane.showMessageDialog(this, "输入正确的图书编号");
				} else {
					try {
						booklendDao.updateBooklend(new Booklend(book_idText, nameText, authorText, publishText,introductionText,stateText));
						JOptionPane.showMessageDialog(this, "图书修改成功");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "输入正确的图书信息");
						e.printStackTrace();
					}
				}
			}

		}

		private void deleteBooklend() {
			book_idText = book_idJTextField.getText().trim().toString();
			if (book_idText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书ID不能为空");
			} else if (booklendDao.getBooklendById(book_idText) != null) {// 当前输入的图书编号是否存在
				try {
					booklendDao.deleteBooklendById(book_idText);
					JOptionPane.showMessageDialog(this, "图书删除成功");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "图书删除异常  请联系管理员");
				}
			} else {
				JOptionPane.showMessageDialog(this, "图书不存在");
			}

		}
		private void addBooklend() {
			book_idText = book_idJTextField.getText().trim().toString();
			nameText = nameJTextField.getText().trim().toString();
			authorText = authorJTextField.getText().trim().toString();
			publishText = publishJTextField.getText().trim().toString();
			introductionText = introductionJTextField.getText().trim().toString();
			stateText = stateJTextField.getText().trim().toString();
			if (book_idText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书ID不能为空");
			} else if (nameText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书名称不能为空");
			} else if (authorText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书作者不能为空");
			} else if (publishText.equals("")) {
				JOptionPane.showMessageDialog(this, "出版社不能为空");
			} else if (introductionText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书简介不能为空");
			} else if (stateText.equals("")) {
				JOptionPane.showMessageDialog(this, "图书状态不能为空");
			} else {
				if (booklendDao.getBooklendById(book_idText)!= null) {// 编号重复
					JOptionPane.showMessageDialog(this, "图书编号重复");
				} else {
					try {
						booklendDao.insertBooklend(new Booklend(book_idText, nameText, authorText, publishText,introductionText,stateText));
						JOptionPane.showMessageDialog(this, "图书增加成功");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "输入正确的图书数量和价格");
						e.printStackTrace();
					}
				}
			}

		}
}
