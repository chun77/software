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
	private JButton returnButton = new JButton("����");
	private JButton add = new JButton("¼��");
	private Font font = new Font("����", Font.PLAIN, 30);

	private JLabel title_id;
	//�������֤��
	private JLabel title_stuid = new JLabel("���֤�ţ�");
	private JTextField stuid = new JTextField(30);
	//�����������
	private JLabel title_result = new JLabel("����������");
	private JTextField result = new JTextField(20);
	
	
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
					JOptionPane.showMessageDialog(null, "������������Ϣ��");
				} else {
					try {
						boolean isSuccess = Controller.getControl().add(stuid.getText(),
								 result.getText());
						if (isSuccess) {
							JOptionPane.showMessageDialog(null, "¼��ɹ���");
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "¼��ʧ�ܣ�");
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "¼��ʧ�ܣ�");
						e1.printStackTrace();
					}
				}

			}
		});
	}
	
	public void init() {
//		title_id = new JLabel("���֤�ţ�" + id);
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
		result.setText("����");
		result.setLocation(500, 360);


	}
}
