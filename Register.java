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
	private JButton returnButton = new JButton("����");
	private Font font = new Font("����", Font.BOLD, 30);
	private JLabel title_id = new JLabel("�û�����");
	private JLabel title_password = new JLabel("���룺");
	private JLabel title_name = new JLabel("������");
	private JTextField id = new JTextField(25);
	private JPasswordField password = new JPasswordField(40);
	private JTextField name = new JTextField(25);
	private JButton register = new JButton("ע��");

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
				// ����ı���
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
					JOptionPane.showMessageDialog(null, "������������Ϣ��");
				}
				try {
					boolean isSuccess = Controller.getControl().register(id.getText(), name.getText(), password.getText());
					if (!isSuccess) {
						JOptionPane.showMessageDialog(null, "���֤�Ų��Ϸ���");
					} else {
						JOptionPane.showMessageDialog(null, "ע��ɹ���");
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
