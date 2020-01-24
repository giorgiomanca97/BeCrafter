package logic.entity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.entity.BillingInfo;
import logic.entity.Order;
import logic.entity.OrderType;
import logic.entity.Product;
import logic.entity.Registered;
import logic.entity.interfaces.Storable;

public class Order_dao {
	private static String SEP = ";";
	
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	    
	// Informazioni tabella ordini
	private static String TABLE_NAME = "orders";
	private static String COL_ID = "id";
	private static String COL_EMAIL = "email";
	
	
	private Order_dao() {
		
	}
	
	
	private static ArrayList<Order> getOrders(String query){
		ArrayList<Order> result = new ArrayList<Order>();
		
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
            		Order order = null; //TODO: modificare
            		
					result.add(order);
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
	
	public static ArrayList<Order> getOrdersByEmail() {
		ArrayList<Order> result = null;
		
		return result;
	}
	
	public static Order getOrderById(String id) {
		Order result = null;
		
		return result;
	}
	
	
	public static void updateOrder(Order order) {
		
	}
	
	
	public static String orderToText(Order order) {
		StringBuilder stringBuilder = new StringBuilder();
		
		OrderType type = order.getType();
		BillingInfo billingInfo = order.getBillingInfo();
		
		stringBuilder.append(type.name());
		stringBuilder.append("\n");
		
		stringBuilder.append(order.getId());
		stringBuilder.append(SEP);
		stringBuilder.append(order.getEmail());
		stringBuilder.append(SEP);
		stringBuilder.append(order.getPrice());
		stringBuilder.append("\n");
		
		stringBuilder.append(BillingInfo_dao.periodicOrderToText(billingInfo));
		stringBuilder.append("\n");
		
		for (Product product : order.getProducts()) {
			stringBuilder.append(Product_dao.productToText(product) + "\n");
		}
		
		return stringBuilder.toString();
	}
	
	public static Order textToOrder(String text) {
		return null;
	}
}
