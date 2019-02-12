package togwayDB;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

public class DBManager {

		
		public static Connection getconnection()
		{
			Connection conn = null;
		
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp.env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/jsptest");
			
			conn = ds.getConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
		
		
	}
		
		public static void close(Connection conn, Statement stmt, ResultSet rs)
		{
			try
			{
				rs.close();
				stmt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public static void close(Connection conn, Statement stmt)
		{
			try
			{
				stmt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		
		
	
}
	

