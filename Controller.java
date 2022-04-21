package software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	// 声明Connection对象
	public Connection con;
	// 驱动程序名
	private String driver = "com.mysql.cj.jdbc.Driver";
	// URL指向要访问的数据库名
	private String url = "jdbc:mysql://localhost:3306/wordpress";
	// MySQL配置时的用户名
	private String user = "root";
	// MySQL配置时的密码
	private String dbpassword = "";

	// 当前用户信息
	private String id;
	private String username;
	private String userPassword;
	
	public boolean logIn() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		System.out.println("success");
		con = DriverManager.getConnection(url, user, dbpassword);
		Statement statement = con.createStatement();

		String sql;
		return true;
	}
	


}
