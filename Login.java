package software;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JPanel{
	private JLabel title_id = new JLabel("用户名：");
	private JLabel title_password = new JLabel("密码：");
	private JButton logIn = new JButton("登录");
	private JTextField name = new JTextField(25);
	private JPasswordField password = new JPasswordField(40);
	private Font font = new Font("宋体", Font.BOLD, 30);
	private JRadioButton student = new JRadioButton("学生", true);
	private JRadioButton admin = new JRadioButton("管理员");
	private ButtonGroup bp = new ButtonGroup();
	private JButton register = new JButton("注册");

	public Login() {
		setBackground(new Color(227, 255, 249));
		setLayout(null);
		add(title_id);
		title_id.setLocation(150, 100);
		title_id.setSize(500, 100);
		title_id.setFont(font);
		add(title_password);
		title_password.setLocation(150, 330);
		title_password.setSize(500, 60);
		title_password.setFont(font);
		add(name);
		name.setLocation(300, 100);
		name.setSize(400, 100);
		name.setFont(font);
		name.setText("210103200204062722");   // 为了调试方便自己设得id
		add(password);
		password.setLocation(300, 300);
		password.setSize(400, 100);
		password.setFont(font);
		password.setText("123456");   // 为了调试方便自己设的密码
		add(student);
		student.setSize(200, 100);
		student.setLocation(300, 420);
		student.setFont(new Font("宋体", Font.BOLD, 23));
		student.setContentAreaFilled(false);
		add(admin);
		admin.setSize(200, 100);
		admin.setLocation(500, 420);
		admin.setFont(new Font("宋体", Font.BOLD, 23));
		admin.setContentAreaFilled(false);
		bp.add(admin);
		bp.add(student);

		add(register);
		register.setSize(160, 80);
		register.setLocation(540, 550);
		register.setContentAreaFilled(false);
		register.setFont(font);
		register.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().toRegister();
			}
		});

		add(logIn);
		logIn.setLocation(300, 550);
		logIn.setSize(160, 80);
		logIn.setFont(font);
		logIn.setContentAreaFilled(false);
		logIn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					if (name.getText().length() == 0 || password.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "请输入用户名与密码！");
						return;
					}
					boolean isSuccess = Controller.getControl().logIn(name.getText(), password.getText(),
							student.isSelected());
					if (!isSuccess) {
						JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					}
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
