package logic.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.TextParseException;
import error.id.IdException;
import logic.designclasses.DaoHelper;
import logic.designclasses.DaoHelper.StatementMode;
import logic.designclasses.IdConverter;
import logic.entity.BillingInfo;
import logic.entity.Order;
import logic.entity.Product;

public class Order_dao {
	private static String ordersFolderPath = "C:\\Becrafter\\persistence\\orders"; 
	private static String folderSep = "\\";
	
	// Informazioni tabella ordini
	private static String tableOrders = "orders";
	private static String tableOrdersColId = "id";
	private static String tableOrdersColEmail = "email";
	private static String tableOrdersColDate = "date";
	private static String tableOrdersColPrice = "price";
	private static String tableOrdersColShipCode = "shippingCode";
	private static String tableOrdersColShipComp = "shippingCompany";
	
	
	private Order_dao() {
		
	}
	
	
	private static OrderDataFetch getOrderData(File file) throws TextParseException {
		OrderDataFetch result = null;
				
		try (
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		) {							
			StringBuilder orderTextBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null){
				orderTextBuilder.append(line + "\n");
			}
			
			result = fetchOrderData(orderTextBuilder.toString());
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		}
		
		if(result == null) {
			throw new NullPointerException();
		}
		
		return result;
	}
	
	private static List<Order> getOrders(String query){
		List<Order> result = new ArrayList<>();
		
		Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	do {
            		String orderId = IdConverter.intToId(rs.getInt(tableOrdersColId), IdConverter.Type.ORDER);
            		Order order = new Order(orderId);
            		order.setEmail(rs.getString(tableOrdersColEmail));
            		order.setDate(rs.getString(tableOrdersColDate));
            		order.setPrice(rs.getFloat(tableOrdersColPrice));
            		order.setShippingCode(rs.getString(tableOrdersColShipCode));
            		order.setShippingCompany(rs.getString(tableOrdersColShipComp));
            		
            		File file = new File(ordersFolderPath + folderSep + orderId);
            		OrderDataFetch orderDataFetch = getOrderData(file);
            		          		
            		order.setBillingInfo(orderDataFetch.getBillingInfo());
            		for (Product product : orderDataFetch.getProducts()) {
						order.addProduct(product);
					}
            		
					result.add(order);
				} while (rs.next());
            }
            
		} catch (ClassNotFoundException | SQLException | TextParseException | NullPointerException | IdException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
        	DaoHelper.close(conn, stmt, rs);
        }
		
		return result;
	}
	
	public static List<Order> getOrdersByEmail(String email) {
		return getOrders("SELECT * FROM " + tableOrders + " WHERE " + tableOrdersColEmail + " = '" + email +"';");
	}
	
	public static Order getOrderById(String id) {
		try {
			int dbId = IdConverter.idToInt(id);
			List<Order> result = getOrders("SELECT * FROM " + tableOrders + " WHERE " + tableOrdersColId + " = " +  dbId + ";");	
			
			if(result.size() == 0) {
				return null;
			} else {
				return result.get(0);
			}
		} catch (IdException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	
	public static String getLastId() {
		String result = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
            
            query = "SELECT MAX(" + tableOrdersColId + ") FROM " + tableOrders + ";";
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	result = IdConverter.intToId(rs.getInt(1), IdConverter.Type.ORDER);
            }
		} catch (ClassNotFoundException | SQLException | IdException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
			DaoHelper.close(conn, stmt, rs);
		}
		
		return result;
	}
	
	
	private static void saveOrderData(Order order) {
		File file = new File(ordersFolderPath + folderSep + order.getId());
		
		try (
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		) {
			if(file.createNewFile()) {
				bufferedWriter.write(printOrderData(order));
			}
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		}
	}
	
	public static void insertOrder(Order order) {
		Connection conn = null;
		Statement stmt = null;
		String query = "";
        
		try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            query = "INSERT INTO " + tableOrders + " (" + tableOrdersColEmail + ", " + tableOrdersColDate + ", " + tableOrdersColPrice + ", " + tableOrdersColShipCode + ", " + tableOrdersColShipComp + ") " +
					"VALUES ('" + order.getEmail() + "', '" + order.getDate() + "', " + order.getPrice() + ", '" + order.getShippingCode() + "', '" + order.getShippingCompany() + "');";
            stmt.executeUpdate(query);
            
            saveOrderData(order);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
        	DaoHelper.close(conn, stmt);
        }
	}
	
	
	private static String printOrderData(Order order) {
		StringBuilder stringBuilder = new StringBuilder();
		
		BillingInfo billingInfo = order.getBillingInfo();
		
		stringBuilder.append(BillingInfo_dao.billingInfoToText(billingInfo));
		stringBuilder.append("\n");

		for (Product product : order.getProducts()) {
			stringBuilder.append(Product_dao.productToText(product) + "\n");
		}		
		
		return stringBuilder.toString();
	}
	
	public static OrderDataFetch fetchOrderData(String text) throws TextParseException{
		String[] lines = text.split("\n");
		
		if(lines.length < 1) {
			throw new TextParseException();
		}
		
		try {
			BillingInfo billingInfo = BillingInfo_dao.textToBillingInfo(lines[0]);
			OrderDataFetch orderDataFetch = new OrderDataFetch(billingInfo);
			
			for(int i = 1; i < lines.length; i++) {
				Product product = Product_dao.textToProduct(lines[i]);
				orderDataFetch.addProduct(product);
			}
			
			return orderDataFetch;
		} catch (Exception e) {
			throw new TextParseException(e);
		}
	}
	
	
	static class OrderDataFetch {
		private BillingInfo billingInfo;
		private List<Product> products;

		
		private OrderDataFetch(BillingInfo billingInfo) {
			this.billingInfo = billingInfo;
			this.products = new ArrayList<>();
		}
		
		
		private BillingInfo getBillingInfo() {
			return this.billingInfo;
		}
		
		private void addProduct(Product product) {
			products.add(product);
		}
		
		private List<Product> getProducts() {
			return this.products;
		}
	}
}
