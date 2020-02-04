package logic.designclasses;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoHelper {
	// Informazioni database
	private static String user = "root";
	private static String pass = "becrafter";
	private static String dbURL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String driverClassName = "org.mariadb.jdbc.Driver";
	
	private static String ordersFolderPath = "C:\\Becrafter\\persistence\\orders";
	private static String recipesFolderPath = "C:\\Becrafter\\persistence\\recipes";
	private static String folderSep = "\\";
	
	private DaoHelper() {
		
	}
	
	
	public static String getUser() { 
		return user;
	}
	
	public static String getPass() {
		return pass;
	}
	
	public static String getDB() {
		return dbURL;
	}
	
	public static String getDriver() {
		return driverClassName;
	}
	
	
	public static String getOrdersFolderPath() {
		return ordersFolderPath;
	}
	
	public static String getRecipesFolderPath() {
		return recipesFolderPath;
	}
	
	public static String getFolderSeparator() {
		return folderSep;
	}
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driverClassName);
		return DriverManager.getConnection(dbURL, user, pass);
	}
	
	public static Statement getStatement(Connection conn, StatementMode mode) throws SQLException {
		if(mode == StatementMode.READ) {
			return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} else {
			return conn.createStatement();
		}
	}
	
	
	public static void close(Connection conn, Statement stmt, List<ResultSet> rsList) {
		if(rsList != null) {
			for (ResultSet rs : rsList) {
				close(rs);
			}
		}
		close(stmt);
        close(conn);
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		List<ResultSet> rsList = new ArrayList<>();
		
		if(rs != null) {
			rsList.add(rs);
		}
		
		close(conn, stmt, rsList);
	}
	
	public static void close(Connection conn, Statement stmt) {		
		close(conn, stmt, new ArrayList<ResultSet>());
	}
	
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
	        } catch (SQLException se) {
	        	Logger.getGlobal().log(Level.WARNING, "ResultSet closure error");
	        }
		}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();  
	        } catch (SQLException se) {
	        	Logger.getGlobal().log(Level.WARNING, "Statement closure error");
	        }
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
        	try {
        		conn.close();
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Connection closure error");
            }
        }
	}
	
	public static void close(InputStream inputStream) {
		if(inputStream != null) {
			try {
				inputStream.close();
	        } catch (IOException ioe) {
	        	Logger.getGlobal().log(Level.WARNING, "InputStream closure error");
	        }
		}
	}
	
	public static void close(OutputStream outputStream) {
		if(outputStream != null) {
			try {
				outputStream.close();
	        } catch (IOException ioe) {
	        	Logger.getGlobal().log(Level.WARNING, "OutputStream closure error");
	        }
		}
	}

	public static void close(Reader reader) {
		if(reader != null) {
			try {
				reader.close();
	        } catch (IOException ioe) {
	        	Logger.getGlobal().log(Level.WARNING, "Reader closure error");
	        }
		}
	}

	public static void close(Writer writer) {
		if(writer != null) {
			try {
				writer.close();
	        } catch (IOException ioe) {
	        	Logger.getGlobal().log(Level.WARNING, "Writer closure error");
	        }
		}
	}
	
	
	public enum StatementMode {
		READ,
		WRITE;
	}

}
