/**
 * @author: Yuan Zhenzhi
 * @className: DbConnection
 * @packageName: software
 * @description: database connect panel
 */
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

public class DbConnection extends JPanel{
	private JLabel title_id = new JLabel("数据库用户名：");
	private JLabel title_port = new JLabel("端口号：");
	private JLabel title_password = new JLabel("数据库密码：");
	private JButton connect = new JButton("连接");
	private JTextField name = new JTextField(25);
	private JTextField port = new JTextField(25);
	private JPasswordField password = new JPasswordField(40);
	private Font font = new Font("宋体", Font.BOLD, 30);

	public DbConnection() {
		setBackground(new Color(227, 255, 249));
		setLayout(null);
		add(title_id);
		title_id.setLocation(150, 100);
		title_id.setSize(500, 100);
		title_id.setFont(font);
		add(title_password);
		title_password.setLocation(150, 280);
		title_password.setSize(500, 60);
		title_password.setFont(font);
		add(title_port);
		title_port.setLocation(150, 460);
		title_port.setSize(500, 60);
		title_port.setFont(font);
		add(name);
		name.setLocation(350, 100);
		name.setSize(400, 100);
		name.setFont(font);
		name.setText("root");   // 为了调试方便自己设得id
		add(password);
		password.setLocation(350, 260);
		password.setSize(400, 100);
		password.setFont(font);
		password.setText("");   // 
		add(port);
		port.setLocation(350,440);
		port.setSize(400,100);
		port.setFont(font);
		port.setText("3306");

		add(connect);
		connect.setLocation(350, 550);
		connect.setSize(160, 80);
		connect.setFont(font);
		connect.setContentAreaFilled(false);
		connect.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (name.getText().length() == 0 ||port.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "请输入用户名与端口号！");
					return;
				}else {
					try {
						Controller.getControl().connect(name.getText(), password.getText(), port.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "连接失败！");
					}
				}
			
			}
		});
	}
}
