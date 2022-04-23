package software;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminLookup extends JPanel{
	private JButton returnButton =new JButton("����");
	private Font font=new Font("����",Font.PLAIN,30);
	public DefaultTableModel dtm=new DefaultTableModel();
	private String[] columnname= {"id","�����","���ʱ��","�����"};
	private JTable allTable;
	private JButton select=new JButton("��ѯ");
	private JScrollPane dropdown1;
	private JLabel title_stuid=new JLabel("���֤�ţ�");
	private JTextField stuid= new JTextField(30);
	
	
	public AdminLookup() {
		setLayout(null);
		setBackground(new Color(227,255,249));
		add(title_stuid);
		title_stuid.setSize(300, 250);
		title_stuid.setLocation(95, 0);
		title_stuid.setFont(font);
		add(stuid);
		stuid.setSize(300, 50);
		stuid.setLocation(230, 80);
		stuid.setFont(font);
		
		add(returnButton);
		returnButton.setSize(160, 80);
		returnButton.setLocation(420,500);
		returnButton.setFont(font);
		returnButton.setContentAreaFilled(false);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controller.getControl().lookupToAdmin();
			}
		});
		allTable=new JTable(dtm);
		dtm.setColumnIdentifiers(columnname);
		dropdown1=new JScrollPane(allTable);
		dropdown1.setSize(600,220);
		dropdown1.setLocation(200,150);
		add(dropdown1);
		allTable.setVisible(true);
		add(select);
		select.setSize(100,50);
		select.setLocation(700,80);
		select.setFont(font);
		select.setContentAreaFilled(false);
		select.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					String stu_id=stuid.getText();
					int rc=dtm.getRowCount();
	        		for(int i=0;i<rc;i++){
	        			dtm.removeRow(0);
	        		}
					Controller.getControl().selectSpecificProject(stu_id);
					allTable=new JTable(dtm);

				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
