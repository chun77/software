package software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	// ����Connection����
	public Connection con;
	// ����������
	private String driver = "com.mysql.cj.jdbc.Driver";
	// URLָ��Ҫ���ʵ����ݿ���
	private String url = "jdbc:mysql://localhost:3306/wordpress";
	// MySQL����ʱ���û���
	private String user = "root";
	// MySQL����ʱ������
	private String dbpassword = "";

	// ��ǰ�û���Ϣ
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
