/**
 * @author: Wang Zichun
 * @className: AdminAdd
 * @packageName: software
 * @description: 管理员录入核酸结果界面Panel
 */
package software;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminAdd extends JPanel{
	private JButton returnButton = new JButton("返回");
	private JButton add = new JButton("录入");
	private Font font = new Font("黑体", Font.PLAIN, 30);

	private JLabel title_id;
	//输入身份证号
	private JLabel title_stuid = new JLabel("身份证号：");
	private JTextField stuid = new JTextField(30);
	//输入核酸检测结果
	private JLabel title_result = new JLabel("核酸检测结果：");
	private JTextField result = new JTextField(20);
	
	/**
	 * @description: 构造函数
	 */	
	public AdminAdd() {
		setLayout(null);
		setBackground(new Color(227, 255, 249));
		add(returnButton);
		returnButton.setSize(160, 80);
		returnButton.setLocation(300, 550);
		returnButton.setFont(font);
		returnButton.setContentAreaFilled(false);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().addToAdmin();
			}
		});

		add(add);
		add.setSize(160, 80);
		add.setLocation(540, 550);
		add.setFont(font);
		add.setContentAreaFilled(false);
		add.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (stuid.getText().length() == 0 
						|| result.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入完整信息！");
				} else {
					try {
						boolean isSuccess = Controller.getControl().add(stuid.getText(),
								 result.getText());
						if (isSuccess) {
							JOptionPane.showMessageDialog(null, "录入成功！");
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "录入失败！");
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "录入失败！");
						e1.printStackTrace();
					}
				}

			}
		});
	}
	
	public void init() {
//		title_id = new JLabel("身份证号：" + id);
//		title_id.setLocation(300, 130);
//		title_id.setSize(500, 80);
//		title_id.setFont(font);
//		add(title_id);
		add(title_stuid);
		title_stuid.setSize(300, 250);
		title_stuid.setLocation(300, 190);
		title_stuid.setFont(font);
		add(stuid);
		stuid.setSize(300, 50);
		stuid.setLocation(450, 290);
		stuid.setFont(font);
		add(title_result);
		title_result.setLocation(300, 333);
		title_result.setSize(300, 100);
		title_result.setFont(font);
		add(result);
		result.setSize(300, 50);
		result.setFont(font);
		result.setText("阴性");
		result.setLocation(500, 360);
	}
}
