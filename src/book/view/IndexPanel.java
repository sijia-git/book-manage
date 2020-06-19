package book.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @Description 系统欢迎界面

 */

public class IndexPanel extends JPanel {
	private JLabel imgLabel;//图片标签
	public IndexPanel() {
		//首页标签设置图片
		imgLabel = new JLabel(new ImageIcon("images/index5.jpg"));
		this.add(imgLabel);
	}
}

