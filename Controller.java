/**
 * @author: Yuan Zhenzhi, Wang Zichun
 * @className: Controller
 * @packageName: software
 * @description: MVC-Control
 */
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
	private String url = "jdbc:mysql://localhost:3306/software";
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
	
	/*java singleton pattern*/
	private static Controller control = new Controller();

	public static Controller getControl() {
		return control;
	}
	
	/**
	 * @description init db information and connect database
	 * @param dbid 
	 * @param dbpwd
	 * @param dbport
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean connect(String dbid,String dbpwd,String dbport) throws ClassNotFoundException, SQLException {
		url="jdbc:mysql://localhost:"+dbport+"/software";
		user=dbid;
		dbpassword=dbpwd;
		Class.forName(driver);
//		System.out.println("success");
		con = DriverManager.getConnection(url, user, dbpassword);
		connectToLogin();
		return true;
	}
	
	/**
	 * @description user login
	 * @param uid
	 * @param password
	 * @param userIsStu
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
	
	/**
	 * @description register an account
	 * @param id
	 * @param name
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean register(String id,String name,String password) 
			throws ClassNotFoundException, SQLException {
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
	
	/**
	 * @description: student update infomation
	 * @param newName
	 * @param newpassword
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	
	/**
	 * @description admin update information
	 * @param newName
	 * @param newpassword
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	
	/**
	 * @description add a record
	 * @param stuId
	 * @param result
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean add(String stuId, String result) throws ClassNotFoundException, SQLException{
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
	
	/**
	 * check id is legal
	 * @param id
	 * @return
	 */
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
				 return false;
			 }
		 }
		 for( int j=0; j<17; j++){
			 sum = sum + (number[j]-48)*w[j];
		 }
		 int a = sum % 11;
		 if(number[17] != checkbit[a]){
			 return false;
		 }
		 return true;
	 }

	public void selectSpecificProject(String stuid) throws SQLException, ClassNotFoundException{
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
	
	/**
	 * @description admin和student公用 admin也是查某个学生的检测结果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
	
	public static void main(String[] args) {
		View.getView().getConnectView().setVisible(true);
	}
	
	/*---------------Interface changes--------------*/
	/**
	 * @description login frame to register frame
	 */
	public void toRegister() {
		View.getView().getLoginView().setVisible(false);
		View.getView().setRegisterView();
		View.getView().getRegisterView().setLocation(View.getView().getLoginView().getX(),
				View.getView().getLoginView().getY());
		View.getView().getRegisterView().setVisible(true);
	}
	
	/**
	 * @description login frame to register frame
	 */
	public void connectToLogin() {
		View.getView().getConnectView().setVisible(false);
		View.getView().setLoginView();
		View.getView().getLoginView().setLocation(View.getView().getConnectView().getX(),
				View.getView().getConnectView().getY());
		View.getView().getLoginView().setVisible(true);
	}

	/*-----------------student interface-----------------*/

	/**
	 * @description student scene return to login
	 */
	public void studentReturn() {
		View.getView().getStudentView().setVisible(false);
		View.getView().getLoginView().setLocation(View.getView().getStudentView().getX(),
				View.getView().getStudentView().getY());
		View.getView().getLoginView().setVisible(true);
	}

	/**
	 * @description student scene to update info scene
	 */
	public void studentToUpdate() {
		View.getView().getStudentView().setVisible(false);
		View.getView().setStudentUpdate();
		View.getView().getUpdate().init(id, username, userPassword);
		View.getView().getStudentUpdate().setLocation(View.getView().getStudentView().getX(),
				View.getView().getStudentView().getY());
		View.getView().getStudentUpdate().setVisible(true);
	}

	/**
	 * @description student scene to select the result
	 */
	public void studentToLookup() {
		View.getView().getStudentView().setVisible(false);
		View.getView().setStudentLookup();
		View.getView().getStudentLookup().setLocation(View.getView().getStudentView().getX(),
				View.getView().getStudentView().getY());
		View.getView().getStudentLookup().setVisible(true);
	}

	/**
	 * @description update return to student scene
	 */	
	public void updateToStudent() {
		View.getView().getStudentUpdate().setVisible(false);
		View.getView().getStudentView().setLocation(View.getView().getStudentUpdate().getX(),
				View.getView().getStudentUpdate().getY());
		View.getView().getStudentView().setVisible(true);
	}


	/**
	 * @description lookup scene back to student scene
	 */	
	public void lookupToStudent() {
		View.getView().getStudentLookup().setVisible(false);
		View.getView().getStudentView().setLocation(View.getView().getStudentLookup().getX(),
				View.getView().getStudentLookup().getY());
		View.getView().getStudentView().setVisible(true);
	}
	
	/*-----------------admin interface-----------------*/
	/**
	 * @description admin scene return to login
	 */		
	public void adminReturn() {
		View.getView().getAdminView().setVisible(false);
		View.getView().getLoginView().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getLoginView().setVisible(true);
	}

	/**
	 * @description admin scene to update info scene
	 */	
	public void adminToUpdate() {
		View.getView().getAdminView().setVisible(false);
		View.getView().setAdminUpdate();
		View.getView().getAUpdate().init(id, username, userPassword);
		View.getView().getAdminUpdate().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getAdminUpdate().setVisible(true);
	}

	/**
	 * @description admin scene to select the result
	 */		
	public void adminToLookup() {
		View.getView().getAdminView().setVisible(false);
		View.getView().setAdminLookup();
		View.getView().getAdminLookup().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getAdminLookup().setVisible(true);
	}
	
	/**
	 * @description admin scene to add record
	 */	
	public void adminToAdd() {
		View.getView().getAdminView().setVisible(false);
		View.getView().setAdminAdd();
		View.getView().getAdd().init();;
		View.getView().getAdminAdd().setLocation(View.getView().getAdminView().getX(),
				View.getView().getAdminView().getY());
		View.getView().getAdminAdd().setVisible(true);
	}

	/**
	 * @description update return to admin scene
	 */	
	public void updateToAdmin() {
		View.getView().getAdminUpdate().setVisible(false);
		View.getView().getAdminView().setLocation(View.getView().getAdminUpdate().getX(),
				View.getView().getAdminUpdate().getY());
		View.getView().getAdminView().setVisible(true);
	}

	/**
	 * @description lookup scene back to admin scene
	 */	
	public void lookupToAdmin() {
		View.getView().getAdminLookup().setVisible(false);
		View.getView().getAdminView().setLocation(View.getView().getAdminLookup().getX(),
				View.getView().getAdminLookup().getY());
		View.getView().getAdminView().setVisible(true);
	}
	
	/**
	 * @description add scene back to admin scene
	 */
	public void addToAdmin() {
		View.getView().getAdminAdd().setVisible(false);
		View.getView().getAdminView().setLocation(View.getView().getAdminAdd().getX(),
				View.getView().getAdminAdd().getY());
		View.getView().getAdminView().setVisible(true);
	}
	
	/**
	 * @description register return to login
	 */
	public void registerReturn() {
		View.getView().getRegisterView().setVisible(false);
		View.getView().getLoginView().setLocation(View.getView().getRegisterView().getX(),
				View.getView().getRegisterView().getY());
		View.getView().getLoginView().setVisible(true);
	}

}
