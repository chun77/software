package software;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View {

	private static View view = new View();
	
	public static View getView() {
		return view;
	}
	
	private JFrame loginView;
	private JPanel loginPanel = new Login();
	
	private JFrame registerView;
	private Register register;
	
	private JFrame studentView;
	private StudentScene studentPanel = new StudentScene();
	
	private JFrame studentUpdate;
	private StudentUpdate update;
	
	private JFrame studentLookup;
	private StudentLookup lookup;

	private View() {
		loginView = new JFrame("����ɸ��ϵͳ��¼");
		loginView.setSize(1000, 800);
		loginView.setLocation(300, 50);
		loginView.add(loginPanel);
		loginView.setDefaultCloseOperation(3);

	}
	
	public JFrame getRegisterView() {
		return registerView;
	}

	public JFrame getStudentLookup() {
		return studentLookup;
	}
	public StudentLookup getLookup() {
		return lookup;
	}

	public JFrame getLoginView() {
		return loginView;
	}

	public JFrame getStudentView() {
		return studentView;
	}

	public StudentScene getStudentPanel() {
		return studentPanel;
	}

	public void setStudent() {
		// TODO Auto-generated method stub
		studentView = new JFrame("ѧ��ѡ��");
		studentView.setSize(1000, 800);
		studentView.add(studentPanel);
		studentView.setDefaultCloseOperation(3);
	}
	
	public void setStudentUpdate() {
		studentUpdate = new JFrame("ѧ���޸���Ϣ");
		studentUpdate.setSize(1000,800);
		update = new StudentUpdate();
		studentUpdate.add(update);
		studentUpdate.setDefaultCloseOperation(3);
	}
	
	public void setStudentLookup() {
		studentLookup = new JFrame("��ѯ������");
		studentLookup.setSize(1000,800);
		lookup = new StudentLookup();
		studentLookup.add(lookup);
		studentLookup.setDefaultCloseOperation(3);
	}
	
	public void setRegisterView() {
		registerView = new JFrame("ע����Ϣ");
		register = new Register();
		registerView.setSize(1000, 800);
		registerView.add(register);
		registerView.setDefaultCloseOperation(3);
	}

	public StudentUpdate getUpdate() {
		return update;
	}

	public JFrame getStudentUpdate() {
		return studentUpdate;
	}
}
