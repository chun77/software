package software;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View {

	private static View view = new View();
	
	public static View getView() {
		return view;
	}
	
//����
	private JFrame loginView;
	private JPanel loginPanel = new Login();
	
	private JFrame registerView;
	private Register register;
	
//student
	private JFrame studentView;
	private StudentScene studentPanel = new StudentScene();
	
	private JFrame studentUpdate;
	private StudentUpdate update;
	
	private JFrame studentLookup;
	private StudentLookup lookup;

//admin
	
	private JFrame adminView;
	private AdminScene adminPanel = new AdminScene();
	
	private JFrame adminUpdate;
	private AdminUpdate aupdate;
	
	private JFrame adminLookup;
	private AdminLookup alookup;
	
	private JFrame adminAdd;
	private AdminAdd add;
	
	

	private View() {
		loginView = new JFrame("����ɸ��ϵͳ��¼");
		loginView.setSize(1000, 800);
		loginView.setLocation(300, 50);
		loginView.add(loginPanel);
		loginView.setDefaultCloseOperation(3);

	}

//get
//����
	public JFrame getRegisterView() {
		return registerView;
	}
	
	public JFrame getLoginView() {
		return loginView;
	}

//student
	public JFrame getStudentView() {
		return studentView;
	}

	public StudentScene getStudentPanel() {
		return studentPanel;
	}
	
	public JFrame getStudentLookup() {
		return studentLookup;
	}
	
	public StudentLookup getLookup() {
		return lookup;
	}
	
	public JFrame getStudentUpdate() {
		return studentUpdate;
	}
	
	public StudentUpdate getUpdate() {
		return update;
	}

//admin
	public JFrame getAdminView() {
		return adminView;
	}
	
	public JFrame getAdminLookup() {
		return adminLookup;
	}
	
	public AdminLookup getaLookup() {
		return alookup;
	}

	public AdminScene getAdminPanel() {
		return adminPanel;
	}
	

	public JFrame getAdminUpdate() {
		return adminUpdate;
	}
	
	public AdminUpdate getAUpdate() {
		return aupdate;
	}
	
	public JFrame getAdminAdd() {
		return adminAdd;
	}
	
	public AdminAdd getAdd() {
		return add;
	}
//set
//����
	public void setRegisterView() {
		registerView = new JFrame("ע����Ϣ");
		register = new Register();
		registerView.setSize(1000, 800);
		registerView.add(register);
		registerView.setDefaultCloseOperation(3);
	}
//student
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
	


//admin
	public void setAdmin() {
		// TODO Auto-generated method stub
		adminView = new JFrame("����Աѡ��");
		adminView.setSize(1000, 800);
		adminView.add(adminPanel);
		adminView.setDefaultCloseOperation(3);
	}
	
	public void setAdminUpdate() {
		adminUpdate = new JFrame("����Ա�޸���Ϣ");
		adminUpdate.setSize(1000,800);
		aupdate = new AdminUpdate();
		adminUpdate.add(aupdate);
		adminUpdate.setDefaultCloseOperation(3);
	}
	
	public void setAdminLookup() {
		adminLookup = new JFrame("��ѯ������");
		adminLookup.setSize(1000,800);
		alookup = new AdminLookup();
		adminLookup.add(alookup);
		adminLookup.setDefaultCloseOperation(3);
	}
	
	public void setAdminAdd() {
		adminAdd = new JFrame("¼�������");
		adminAdd.setSize(1000,800);
		add = new AdminAdd();
		adminAdd.add(add);
		adminAdd.setDefaultCloseOperation(3);
	}
	
	
	
}
