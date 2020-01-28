package logic.designclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoHelper {
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	
	
	private DaoHelper() {
		
	}
	
	
	public static String getUser() { 
		return USER;
	}
	
	public static String getPass() {
		return PASS;
	}
	
	public static String getDB() {
		return DB_URL;
	}
	
	public static String getDriver() {
		return DRIVER_CLASS_NAME;
	}
	
	
	public static void loadDriver() throws ClassNotFoundException {
		Class.forName(DRIVER_CLASS_NAME);
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
	public static Statement getStatement(Connection conn, StatementMode mode) throws SQLException {
		if(mode == StatementMode.READ) {
			return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} else {
			return conn.createStatement();
		}
	}
	
	
	public enum StatementMode {
		READ,
		WRITE;
	}
}
