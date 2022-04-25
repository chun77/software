/**
 * @author: Yuan Zhenzhi
 * @className: Register
 * @packageName: software
 * @description: user register panel
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JPanel{
	private JButton returnButton = new JButton("返回");
	private Font font = new Font("宋体", Font.BOLD, 30);
	private JLabel title_id = new JLabel("用户名：");
	private JLabel title_password = new JLabel("密码：");
	private JLabel title_name = new JLabel("姓名：");
	private JTextField id = new JTextField(25);
	private JPasswordField password = new JPasswordField(40);
	private JTextField name = new JTextField(25);
	private JButton register = new JButton("注册");

	public Register() {
		setBackground(new Color(227, 255, 249));
		setLayout(null);
		add(title_id);
		title_id.setLocation(150, 100);
		title_id.setSize(500, 100);
		title_id.setFont(font);
		add(title_password);
		title_password.setLocation(150, 220);
		title_password.setSize(500, 60);
		title_password.setFont(font);
		add(title_name);
		title_name.setLocation(150, 340);
		title_name.setSize(500, 60);
		title_name.setFont(font);
		add(id);
		id.setLocation(300, 100);
		id.setSize(400, 100);
		id.setFont(font);
		add(password);
		password.setLocation(300, 220);
		password.setSize(400, 100);
		password.setFont(font);
		add(name);
		name.setLocation(300, 340);
		name.setSize(400, 100);
		name.setFont(font);
		add(returnButton);
		returnButton.setSize(160, 80);
		returnButton.setLocation(300, 500);
		returnButton.setFont(font);
		returnButton.setContentAreaFilled(false);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// 清空文本框
				name.setText("");
				password.setText("");
				id.setText("");
				Controller.getControl().registerReturn();
			}
		});

		add(register);
		register.setSize(160, 80);
		register.setLocation(540, 500);
		register.setFont(font);
		register.setContentAreaFilled(false);
		register.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (id.getText().length() == 0 || name.getText().length() == 0 || password.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入完整信息！");
				}
				try {
					boolean isSuccess = Controller.getControl().register(id.getText(), name.getText(), password.getText());
					if (!isSuccess) {
						JOptionPane.showMessageDialog(null, "身份证号不合法！");
					} else {
						JOptionPane.showMessageDialog(null, "注册成功！");
						Controller.getControl().registerReturn();
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
