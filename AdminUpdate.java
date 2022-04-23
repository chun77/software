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

public class AdminUpdate extends JPanel{
	private JButton returnButton = new JButton("����");
	private JButton update = new JButton("����");
	private Font font = new Font("����", Font.PLAIN, 30);

	private JLabel title_id;
	private JLabel title_password = new JLabel("���룺");
	private JPasswordField newpassword = new JPasswordField(40);
	private JLabel title_name = new JLabel("������");
	private JTextField newname = new JTextField(25);
	
	public AdminUpdate() {
		setLayout(null);
		setBackground(new Color(227, 255, 249));
		add(returnButton);
		returnButton.setSize(160, 80);
		returnButton.setLocation(300, 550);
		returnButton.setFont(font);
		returnButton.setContentAreaFilled(false);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().updateToAdmin();
			}
		});

		add(update);
		update.setSize(160, 80);
		update.setLocation(540, 550);
		update.setFont(font);
		update.setContentAreaFilled(false);
		update.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (newname.getText().length() == 0 
						|| newpassword.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "������������Ϣ��");
				} else {
					try {
						boolean isSuccess = Controller.getControl().aupdate(newname.getText(),
								 newpassword.getText());
						if (isSuccess) {
							JOptionPane.showMessageDialog(null, "���³ɹ���");
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
						e1.printStackTrace();
					}
				}

			}
		});
	}
	
	public void init(String id, String username, String userPassword) {
		title_id = new JLabel("����֤�ţ�" + id);
		title_id.setLocation(300, 130);
		title_id.setSize(500, 80);
		title_id.setFont(font);
		add(title_id);
		add(title_password);
		title_password.setLocation(300, 190);
		title_password.setSize(200, 100);
		title_password.setFont(font);
		add(newpassword);
		newpassword.setSize(300, 50);
		newpassword.setText(userPassword);
		newpassword.setLocation(400, 210);
		add(title_name);
		title_name.setSize(300, 250);
		title_name.setLocation(300, 310);
		title_name.setFont(font);
		add(newname);
		newname.setSize(300, 50);
		newname.setLocation(400, 410);
		newname.setFont(font);
		newname.setText(username);
	}
}