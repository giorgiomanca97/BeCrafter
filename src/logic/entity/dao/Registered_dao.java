package logic.entity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.entity.BillingInfo;
import logic.entity.Registered;

public class Registered_dao {
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	
	// Informazioni tabella ordini
	private static String TABLE_NAME = "registered";
	private static String COL_EMAIL = "email";
	private static String COL_PASSWORD = "password";
	private static String COL_FIRSTNAME = "firstName";
	private static String COL_LASTNAME = "lastName";
	private static String COL_ADDRESS = "address";
	private static String COL_CITY = "city";
	private static String COL_COUNTRY = "country";
	private static String COL_POSTALCODE = "postalCode";
	private static String COL_PHONE = "phone";
	private static String COL_CARD = "card";
	
	
	private Registered_dao() {
		
	}
	
	
	private static ArrayList<Registered> getRegistered(String query){
		ArrayList<Registered> result = new ArrayList<Registered>();
		
		Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
        	Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	do {
            		String email = rs.getString(COL_EMAIL);
            		String password = rs.getString(COL_PASSWORD);
            		            		
            		BillingInfo billingInfo = new BillingInfo();
            		billingInfo.setFirstName(rs.getString(COL_FIRSTNAME));
            		billingInfo.setFirstName(rs.getString(COL_LASTNAME));
            		billingInfo.setFirstName(rs.getString(COL_ADDRESS));
            		billingInfo.setFirstName(rs.getString(COL_CITY));
            		billingInfo.setFirstName(rs.getString(COL_COUNTRY));
            		billingInfo.setFirstName(rs.getString(COL_POSTALCODE));
            		billingInfo.setFirstName(rs.getString(COL_PHONE));
            		billingInfo.setFirstName(rs.getString(COL_CARD));
            		
            		Registered registered = new Registered(email, password, billingInfo);
					result.add(registered);
				} while (rs.next());
            }
            
		} catch (ClassNotFoundException ce) {
			// TODO: handle exception
		} catch (SQLException se) {
			// TODO: handle exception
		}
        finally {
        	try {
        		if(rs != null) {
        			rs.close();
        		}
            } catch (SQLException se) {
            }
            try {
                if (stmt != null) {
                	stmt.close();
                }      
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            }
        }
		
		return result;
	}
	
	
	public static ArrayList<Registered> getAllRegistered(){
		ArrayList<Registered> result = getRegistered("SELECT * FROM " + TABLE_NAME + ";");;
		
		return result;
	}
	
	public static Registered getRegisteredByEmail(String email) {
		ArrayList<Registered> result = getRegistered("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = '" + email +"';");	
		
		if(result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	
	public static void updateRegistered(Registered registered) {
		
	}
}
