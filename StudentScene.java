package software;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentScene extends JPanel{
	private JButton complete = new JButton("�޸ĸ�����Ϣ");
	private JButton select = new JButton("��ѯ���");
	private JButton returnButton = new JButton("�˳���¼");
	private Font font = new Font("����", Font.PLAIN, 30);
	private JLabel label;
	
	public StudentScene() {
		setLayout(null);
		setBackground(new Color(227, 255, 249));
		add(returnButton);
		returnButton.setSize(300, 80);
		returnButton.setLocation(350, 550);
		returnButton.setFont(font);
		returnButton.setContentAreaFilled(false);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().studentReturn();
			}
		});

		add(complete);
		complete.setSize(300, 80);
		complete.setLocation(350, 130);
		complete.setFont(font);
		complete.setContentAreaFilled(false);
		complete.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().studentToUpdate();
			}
		});
		add(select);
		select.setSize(300, 80);
		select.setLocation(350, 340);
		select.setFont(font);
		select.setContentAreaFilled(false);
		select.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().studentToLookup();
			}
		});
	}

	public void setLabel(String id) {
		label = new JLabel("��ã�" + id + "!");
		add(label);
		label.setSize(300, 50);
		label.setLocation(50, 20);
		label.setFont(font);
	}
}
