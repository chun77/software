package software;

import java.sql.SQLException;

public class View {
	public static void main(String[] args) {
		Controller con1 = new Controller();
		try {
			con1.logIn();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
