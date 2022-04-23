package software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class Controller {
	// 声明Connection对象
	public Connection con;
	// 驱动程序名
	private String driver = "com.mysql.cj.jdbc.Driver";
	// URL指向要访问的数据库名
	private String url = "jdbc:mysql://localhost:3316/software";
	// MySQL配置时的用户名
	private String user = "root";
	// MySQL配置时的密码
	private String dbpassword = "";

	// 当前用户信息
	private String id; 
	private String username;
	private String userPassword;
	
	private boolean isStudent;  // is user:true is admin:false
	
	public Controller() {
		id = null;
	}

	public boolean logIn(String uid,String password, boolean userIsStu) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
//		System.out.println("success");
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();

		String sql;
		if(userIsStu) {
			// user login
			sql = "select * from students";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				if(rs.getString("stu_id").equals(uid)&&rs.getString("stu_password").equals(password)) {
					id=uid;
					username=rs.getString("stu_name");
					userPassword=rs.getString("stu_password");
					isStudent=true;
					
					View.getView().getLoginView().setVisible(false);
					View.getView().setStudent();
					View.getView().getStudentView().setLocation(View.getView().getLoginView().getX(),
							View.getView().getLoginView().getY());
					View.getView().getStudentPanel().setLabel(username);
					View.getView().getStudentView().setVisible(true);
					return true;
				}
			}
		}else {
			// admin login
			sql = "select * from admins";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				if(rs.getString("admin_id").equals(uid)&&rs.getString("admin_password").equals(password)) {
					id=uid;
					username=rs.getString("admin_name");
					userPassword=rs.getString("admin_password");
					isStudent=true;
					
					View.getView().getLoginView().setVisible(false);
					View.getView().setAdmin();
					View.getView().getAdminView().setLocation(View.getView().getLoginView().getX(),
							View.getView().getLoginView().getY());
					View.getView().getAdminPanel().setLabel(username);
					View.getView().getAdminView().setVisible(true);
					return true;
				}
			}
		}
		return true;
	}
	
	public void toRegister() {
		// login to register
		View.getView().getLoginView().setVisible(false);
		View.getView().setRegisterView();
		View.getView().getRegisterView().setLocation(View.getView().getLoginView().getX(),
				View.getView().getLoginView().getY());
		View.getView().getRegisterView().setVisible(true);
	}
	
	public boolean register(String id,String name,String password) 
			throws ClassNotFoundException, SQLException {
		// user register
		// TODO:判断身份证号合法性
		if(!checkId(id)) {
			return false;
		}
		
		String sql;
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();
		sql = "select * from students";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			if (rs.getString("stu_id").equals(id)) {
				rs.close();
				con.close();
				return false;
			}
		}
		sql = "INSERT INTO Students (stu_id,stu_name,stu_password) VALUES (" + id + ",'" + name + "','" + password
				+ "')";
		statement.executeUpdate(sql);
		rs.close();
		con.close();
		return true;
	}
	
	private static Controller control = new Controller();

	public static Controller getControl() {
		// TODO Auto-generated method stub
		return control;
	}
	

	
	public static void main(String[] args) {
		View.getView().getLoginView().setVisible(true);
	}
//界面跳转
//student

	public void studentReturn() {
		// student scene return to login
		View.getView().getStudentView().setVisible(false);
		View.getView().getLoginView().setLocation(View.getView().getStudentView().getX(),
				View.getView().getStudentView().getY());
		View.getView().getLoginView().setVisible(true);
	}

	public void studentToUpdate() {
		// student scene to update info scene
		View.getView().getStudentView().setVisible(false);
		View.getView().setStudentUpdate();
		View.getView().getUpdate().init(id, username, userPassword);
		View.getView().getStudentUpdate().setLocation(View.getView().getStudentView().getX(),
				View.getView().getStudentView().getY());
		View.getView().getStudentUpdate().setVisible(true);
	}

	public void studentToLookup() {
		// student scene to select the result
		View.getView().getStudentView().setVisible(false);
		View.getView().setStudentLookup();
		View.getView().getStudentLookup().setLocation(View.getView().getStudentView().getX(),
				View.getView().getStudentView().getY());
		View.getView().getStudentLookup().setVisible(true);
	}

	public void updateToStudent() {
		// update return to student scene
		View.getView().getStudentUpdate().setVisible(false);
		View.getView().getStudentView().setLocation(View.getView().getStudentUpdate().getX(),
				View.getView().getStudentUpdate().getY());
		View.getView().getStudentView().setVisible(true);
	}

	public boolean update(String newName, String newpassword) 
			throws ClassNotFoundException, SQLException{
		String sql = "update students set stu_name='" + newName + "'" + ","  + "stu_password='" + newpassword
				+ "' where stu_id='" + id +"'";
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();
		statement.executeUpdate(sql);
		con.close();
		return true;
	}

	public void lookupToStudent() {
		// lookup scene back to student scene
		View.getView().getStudentLookup().setVisible(false);
		View.getView().getStudentView().setLocation(View.getView().getStudentLookup().getX(),
				View.getView().getStudentLookup().getY());
		View.getView().getStudentView().setVisible(true);
	}
	
//admin
	public void adminReturn() {
		// admin scene return to login
		View.getView().getAdminView().setVisible(false);
		View.getView().getLoginView().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getLoginView().setVisible(true);
	}

	public void adminToUpdate() {
		// admin scene to update info scene
		View.getView().getAdminView().setVisible(false);
		View.getView().setAdminUpdate();
		View.getView().getAUpdate().init(id, username, userPassword);
		View.getView().getAdminUpdate().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getAdminUpdate().setVisible(true);
	}

	public void adminToLookup() {
		// admin scene to select the result
		View.getView().getAdminView().setVisible(false);
		View.getView().setAdminLookup();
		View.getView().getAdminLookup().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getAdminLookup().setVisible(true);
	}
	
	public void adminToAdd() {
		// admin scene to add record
		View.getView().getAdminView().setVisible(false);
		View.getView().setAdminAdd();
		View.getView().getAdd().init();;
		View.getView().getAdminAdd().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getAdminAdd().setVisible(true);
	}

	public void updateToAdmin() {
		// update return to admin scene
		View.getView().getAdminUpdate().setVisible(false);
		View.getView().getAdminView().setLocation(View.getView().getAdminUpdate().getX(),
				View.getView().getAdminUpdate().getY());
		View.getView().getAdminView().setVisible(true);
	}

	public boolean aupdate(String newName, String newpassword) 
			throws ClassNotFoundException, SQLException{
		String sql = "update admins set admin_name='" + newName + "'" + ","  + "admin_password='" + newpassword
				+ "' where admin_id='" + id +"'";
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();
		statement.executeUpdate(sql);
		con.close();
		return true;
	}

	public void lookupToAdmin() {
		// lookup scene back to admin scene
		View.getView().getAdminLookup().setVisible(false);
		View.getView().getAdminView().setLocation(View.getView().getAdminLookup().getX(),
				View.getView().getAdminLookup().getY());
		View.getView().getAdminView().setVisible(true);
	}
	
	public void addToAdmin() {
		// add scene back to admin scene
		View.getView().getAdminAdd().setVisible(false);
		View.getView().getAdminView().setLocation(View.getView().getAdminAdd().getX(),
				View.getView().getAdminAdd().getY());
		View.getView().getAdminView().setVisible(true);
	}
	
	public boolean add(String stuId, String result) throws ClassNotFoundException, SQLException{
		// add a record
		//why '?
		String sql = "insert into records (stu_id,admin_id,result) VALUE (" + stuId + ",'"  + id + "','"
				+result+"')";
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();
		statement.executeUpdate(sql);
		con.close();
		return true;
	}

//admin和student公用 admin也是查某个学生的检测结果
	public void selectProject() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();
		String sql = "select * from records where stu_id='"+id+"'";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String stu_id = rs.getString("stu_id");
			String admin_id = rs.getString("admin_id");
			String result = rs.getString("result");
			String time = rs.getString("time");
			Vector<String> v = new Vector<String>();
			v.add(stu_id);
			v.add(admin_id);
			v.add(result);
			v.add(time);
			if(isStudent) {
				View.getView().getLookup().dtm.addRow(v);
			}else {
				View.getView().getLookup().dtm.addRow(v);
			}
		}
	}

	public void registerReturn() {
		// register return to login
		View.getView().getRegisterView().setVisible(false);
		View.getView().getLoginView().setLocation(View.getView().getRegisterView().getX(),
				View.getView().getRegisterView().getY());
		View.getView().getLoginView().setVisible(true);
	}
	
	public boolean checkId(String id){
		 int sum = 0;
		 int[] w = new int[17];
		 char[] checkbit = new char[12];
		 //加权因子
		 w[0] = 7;
		 w[1] = 9;
		 w[2] = 10;
		 w[3] = 5;
		 w[4] = 8;
		 w[5] = 4;
		 w[6] = 2;
		 w[7] = 1;
		 w[8] = 6;
		 w[9] = 3;
		 w[10] = 7;
		 w[11] = 9;
		 w[12] = 10;
		 w[13] = 5;
		 w[14] = 8;
		 w[15] = 4;
		 w[16] = 2;
		 //校验码
		 checkbit[0] = '1';
		 checkbit[1] = '0';
		 checkbit[2] = 'X';
		 checkbit[3] = '9';
		 checkbit[4] = '8';
		 checkbit[5] = '7';
		 checkbit[6] = '6';
		 checkbit[7] = '5';
		 checkbit[8] = '4';
		 checkbit[9] = '3';
		 checkbit[10] = '2';
		 checkbit[11] = '\0';
		 if(id.length() != 18){
			 return false;
		 }
		 char[] number = id.toCharArray();
		 for(int i=0; i<17; i++){
			 if(number[i] < '9' || number[i] > '0'){
//				 error("身份证不合法，前17位为数字")
				 return false;
			 }
		 }
		 for( int j=0; j<17; j++){
			 sum = sum + (number[j]-48)*w[j];
		 }
		 int a = sum % 11;
		 if(number[17] != checkbit[a]){
//			 error("身份证不合法");
			 return false;
		 }
		 return true;
	 }

	public void selectSpecificProject(String stuid) throws SQLException, ClassNotFoundException{
		// TODO Auto-generated method stub
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();
		String sql = "select * from records where stu_id='"+stuid+"'";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String stu_id = rs.getString("stu_id");
			String admin_id = rs.getString("admin_id");
			String result = rs.getString("result");
			String time = rs.getString("time");
			Vector<String> v = new Vector<String>();
			v.add(stu_id);
			v.add(admin_id);
			v.add(result);
			v.add(time);
			if(isStudent) {
				View.getView().getaLookup().dtm.addRow(v);
			}else {
				View.getView().getaLookup().dtm.addRow(v);
			}
		}
		
	}


}
