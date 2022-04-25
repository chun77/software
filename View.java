/**
 * @author: Yuan Zhenzhi,Wang Zichun
 * @className: View
 * @packageName: software
 * @description: MVC-View
 */
package software;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View {

	private static View view = new View();
	
	public static View getView() {
		return view;
	}
	
	private JFrame connectView;
	private DbConnection connect = new DbConnection();
	//公用
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
		connectView = new JFrame("数据库连接");
		connectView.setSize(1000, 800);
		connectView.setLocation(300, 50);
		connectView.add(connect);
		connectView.setDefaultCloseOperation(3);

	}

	public JFrame getConnectView() {
		return connectView;
	}

	//get
	//公用
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
	//公用
	
	public void setLoginView() {
		loginView = new JFrame("核酸筛查系统登录");
		loginView.setSize(1000, 800);
		loginView.setLocation(300, 50);
		loginView.add(loginPanel);
		loginView.setDefaultCloseOperation(3);
	}
	
	public void setRegisterView() {
		registerView = new JFrame("注册信息");
		register = new Register();
		registerView.setSize(1000, 800);
		registerView.add(register);
		registerView.setDefaultCloseOperation(3);
	}
//student
	public void setStudent() {
		// TODO Auto-generated method stub
		studentView = new JFrame("学生选择");
		studentView.setSize(1000, 800);
		studentView.add(studentPanel);
		studentView.setDefaultCloseOperation(3);
	}
	
	public void setStudentUpdate() {
		studentUpdate = new JFrame("学生修改信息");
		studentUpdate.setSize(1000,800);
		update = new StudentUpdate();
		studentUpdate.add(update);
		studentUpdate.setDefaultCloseOperation(3);
	}
	
	public void setStudentLookup() {
		studentLookup = new JFrame("查询核酸结果");
		studentLookup.setSize(1000,800);
		lookup = new StudentLookup();
		studentLookup.add(lookup);
		studentLookup.setDefaultCloseOperation(3);
	}
	


//admin
	public void setAdmin() {
		// TODO Auto-generated method stub
		adminView = new JFrame("管理员选择");
		adminView.setSize(1000, 800);
		adminView.add(adminPanel);
		adminView.setDefaultCloseOperation(3);
	}
	
	public void setAdminUpdate() {
		adminUpdate = new JFrame("管理员修改信息");
		adminUpdate.setSize(1000,800);
		aupdate = new AdminUpdate();
		adminUpdate.add(aupdate);
		adminUpdate.setDefaultCloseOperation(3);
	}
	
	public void setAdminLookup() {
		adminLookup = new JFrame("查询核酸结果");
		adminLookup.setSize(1000,800);
		alookup = new AdminLookup();
		adminLookup.add(alookup);
		adminLookup.setDefaultCloseOperation(3);
	}
	
	public void setAdminAdd() {
		adminAdd = new JFrame("录入核酸结果");
		adminAdd.setSize(1000,800);
		add = new AdminAdd();
		adminAdd.add(add);
		adminAdd.setDefaultCloseOperation(3);
	}
	
	
	
}
