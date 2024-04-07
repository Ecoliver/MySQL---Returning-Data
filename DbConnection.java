package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import projects.entity.Project;
import projects.exception.DbException;


@SuppressWarnings("unused")
public class DbConnection {
	private static String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
	public static Connection getConnection() {
		String url = 
		String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false",
				HOST, PORT, SCHEMA, USER, PASSWORD);
		System.out.println("Connecting with url=" + url);
		
		try {
		     Connection conn = DriverManager.getConnection(url);
		     System.out.println("Successfully obtained connection!");
		return conn;
		} catch (SQLException e) {
			System.out.println("Unable to get connection at" + url);
			throw new DbException(e);
		}
	}





private void startTransaction(Connection conn) {
	// TODO Auto-generated method stub
	
}


private void commitTransaction(Connection conn) {
	// TODO Auto-generated method stub
	
}


private Integer getLastInsertId(Connection conn, String projectTable) {
	// TODO Auto-generated method stub
	return null;
}


private void rollbackTransaction(Connection conn) {
	// TODO Auto-generated method stub
	
}
	
}