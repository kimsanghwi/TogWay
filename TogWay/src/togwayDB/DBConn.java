package togwayDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jsptest";
			String user = "jspid";
			String password = "jsppass";
			
			conn = DriverManager.getConnection(url, user, password);
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		try {
			if(conn != null)
				conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
