package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.designclasses.DaoHelper;
import logic.designclasses.DaoHelper.StatementMode;
import logic.entity.BillingInfo;
import logic.entity.Order;
import logic.entity.Registered;

public class RegisteredDao {
	// Informazioni tabella ordini
	private static String tableRegistered = "registered";
	private static String tableRegisteredColEmail = "email";
	private static String tableRegisteredColPass = "password";
	private static String tableRegisteredColFirstName = "firstName";
	private static String tableRegisteredColLastName = "lastName";
	private static String tableRegisteredColAddress = "address";
	private static String tableRegisteredColCity = "city";
	private static String tableRegisteredColCountry = "country";
	private static String tableRegisteredColPostalCode = "postalCode";
	private static String tableRegisteredColPhoneNumber = "phone";
	private static String tableRegisteredColCardNumber = "card";
	
	
	private RegisteredDao() {
		
	}
	
	
	private static List<Registered> getRegistered(String query){
		List<Registered> result = new ArrayList<>();
		
		Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	do {
            		String email = rs.getString(tableRegisteredColEmail);
            		String password = rs.getString(tableRegisteredColPass);
            		            		
            		BillingInfo billingInfo = new BillingInfo();
            		billingInfo.setFirstName(rs.getString(tableRegisteredColFirstName));
            		billingInfo.setLastName(rs.getString(tableRegisteredColLastName));
            		billingInfo.setAddress(rs.getString(tableRegisteredColAddress));
            		billingInfo.setCity(rs.getString(tableRegisteredColCity));
            		billingInfo.setCountry(rs.getString(tableRegisteredColCountry));
            		billingInfo.setPostalCode(rs.getString(tableRegisteredColPostalCode));
            		billingInfo.setPhone(rs.getString(tableRegisteredColPhoneNumber));
            		billingInfo.setCard(rs.getString(tableRegisteredColCardNumber));
            		
            		Registered registered = new Registered(email, password, billingInfo);
            		
            		List<Order> orders = OrderDao.getOrdersByEmail(email);
            		for (Order order : orders) {
            			registered.addOrder(order);
					}
            		
					result.add(registered);
				} while (rs.next());
            }
            
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
        	DaoHelper.close(conn, stmt, rs);
        }
		
		return result;
	}
	
	public static List<Registered> getAllRegistered(){
		return getRegistered("SELECT * FROM " + tableRegistered + ";");
	}
	
	public static Registered getRegisteredByEmail(String email) {
		List<Registered> result = getRegistered("SELECT * FROM " + tableRegistered + " WHERE " + tableRegisteredColEmail + " = '" + email +"';");	
		
		if(result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	
	public static void insertRegistered(Registered registered) {
		Connection conn = null;
		Statement stmt = null;
        String query = "";
        
		try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            BillingInfo bi = registered.getBillingInfo();
            query = "INSERT INTO " + tableRegistered + " VALUES ('" + registered.getEmail() + "', '" + registered.getPassword() + "', '" + bi.getFirstName() + "', '" + bi.getLastName() + "', '" + 
					bi.getAddress() + "', '" + bi.getCity() + "', '" + bi.getCountry() + "', '" + bi.getPostalCode() + "', '" + bi.getPhone() + "', '" + bi.getCard() + "');";
            
            stmt.executeUpdate(query);
            
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
        	DaoHelper.close(conn, stmt);
        }
	}
	
	public static void updateRegistered(Registered registered) {
		Statement stmt = null;
        Connection conn = null;
        String query = "";
        
		try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            BillingInfo bi = registered.getBillingInfo();
            query = "UPDATE " + tableRegistered + " SET " + tableRegisteredColPass + " = '" + registered.getPassword() + "', " + tableRegisteredColFirstName + " = '" + bi.getFirstName() + "', " + tableRegisteredColLastName + " = '" + bi.getLastName() + "', " + 
					tableRegisteredColAddress + " = '" + bi.getAddress() + "', " + tableRegisteredColCity + " = '" + bi.getCity() + "', " + tableRegisteredColCountry + " = '" + bi.getCountry() + "', " + tableRegisteredColPostalCode + " = '" + bi.getPostalCode() + 
					tableRegisteredColPhoneNumber + " = '" + bi.getPhone() + "', " + tableRegisteredColCardNumber + " = '" + bi.getCard() + "' WHERE " + tableRegisteredColEmail + " = '" + registered.getEmail() + "';";
            
            stmt.executeUpdate(query);
            
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
        	DaoHelper.close(conn, stmt);
        }
	}
}
