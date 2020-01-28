package logic.entity.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.designclasses.DaoHelper;
import logic.designclasses.DaoHelper.StatementMode;
import logic.entity.BillingInfo;
import logic.entity.Order;
import logic.entity.Registered;

public class Registered_dao {
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
        	DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
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
            		
            		ArrayList<Order> orders = Order_dao.getOrdersByEmail(email);
            		for (Order order : orders) {
            			registered.addOrder(order);
					}
            		
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
	
	
	public static void insertRegistered(Registered registered) {
		Statement stmt = null;
        Connection conn = null;
        
		try {
        	DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            BillingInfo bi = registered.getBillingInfo();
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME + " VALUES ('" + registered.getEmail() + "', '" + registered.getPassword() + "', '" + bi.getFirstName() + "', '" + bi.getLastName() + "', '" + 
            					bi.getAddress() + "', '" + bi.getCity() + "', '" + bi.getCountry() + "', '" + bi.getPostalCode() + "', '" + bi.getPhone() + "', '" + bi.getCard() + "');");
            
            
		} catch (ClassNotFoundException ce) {
			// TODO: handle exception
		} catch (SQLException se) {
			se.printStackTrace();
		}
        finally {
            try {
                if (stmt != null) {
                	stmt.close();
                }      
            } catch (SQLException se) {
            	// TODO: handle exception
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            	// TODO: handle exception
            }
        }
	}
	
	public static void updateRegistered(Registered registered) {
		Statement stmt = null;
        Connection conn = null;
        
		try {
			DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            BillingInfo bi = registered.getBillingInfo();
            stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + COL_PASSWORD + " = '" + registered.getPassword() + "', " + COL_FIRSTNAME + " = '" + bi.getFirstName() + "', " + COL_LASTNAME + " = '" + bi.getLastName() + "', " + 
            					COL_ADDRESS + " = '" + bi.getAddress() + "', " + COL_CITY + " = '" + bi.getCity() + "', " + COL_COUNTRY + " = '" + bi.getCountry() + "', " + COL_POSTALCODE + " = '" + bi.getPostalCode() + 
            					COL_PHONE + " = '" + bi.getPhone() + "', " + COL_CARD + " = '" + bi.getCard() + "' WHERE " + COL_EMAIL + " = '" + registered.getEmail() + "';");
            
            
		} catch (ClassNotFoundException ce) {
			// TODO: handle exception
		} catch (SQLException se) {
			// TODO: handle exception
		}
        finally {
            try {
                if (stmt != null) {
                	stmt.close();
                }      
            } catch (SQLException se) {
            	// TODO: handle exception
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            	// TODO: handle exception
            }
        }
	}
}
