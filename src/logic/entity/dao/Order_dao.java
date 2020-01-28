package logic.entity.dao;

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
	private static String ORDERS_FOLDER_PATH = "persistence/orders"; 
	
	// Informazioni tabella ordini
	private static String TABLE_ORDERS = "orders";
	private static String TABLE_ORDERS_COL_ID = "id";
	private static String TABLE_ORDERS_COL_EMAIL = "email";
	private static String TABLE_ORDERS_COL_DATE = "date";
	private static String TABLE_ORDERS_COL_PRICE = "price";
	private static String TABLE_ORDERS_COL_SHIPCODE = "shippingCode";
	private static String TABLE_ORDERS_COL_SHIPCOMP = "shippingCompany";
	
	
	private Order_dao() {
		
	}
	
	
	private static OrderDataFetch getOrderData(File file) {
		OrderDataFetch result = null;
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {			
			if(file.exists()) {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				StringBuilder textBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null){
					textBuilder.append(line + "\n");
				}
				
				result = fetchOrderData(textBuilder.toString());
			}
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "File reading error");
		} catch (TextParseException tpe) {
			Logger.getGlobal().log(Level.SEVERE, "File parsing error");
		} finally {
			try {
                if (bufferedReader != null) {
                	bufferedReader.close();
                }
            } catch (IOException ioe) {
            	Logger.getGlobal().log(Level.WARNING, "BufferedReader closure error");
            }
			try {
                if (fileReader != null) {
                	fileReader.close();
                }
            } catch (IOException ioe) {
            	Logger.getGlobal().log(Level.WARNING, "FileReader closure error");
            }
		}
		
		return result;
	}
	
	private static ArrayList<Order> getOrders(String query){
		ArrayList<Order> result = new ArrayList<Order>();
		
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
            		String orderId = IdConverter.intToId(rs.getInt(TABLE_ORDERS_COL_ID), IdConverter.Type.ORDER);
            		Order order = new Order(orderId);
            		order.setEmail(rs.getString(TABLE_ORDERS_COL_EMAIL));
            		order.setDate(rs.getString(TABLE_ORDERS_COL_DATE));
            		order.setPrice(rs.getFloat(TABLE_ORDERS_COL_PRICE));
            		order.setShippingCode(rs.getString(TABLE_ORDERS_COL_SHIPCODE));
            		order.setShippingCompany(rs.getString(TABLE_ORDERS_COL_SHIPCOMP));
            		
            		File file = new File(ORDERS_FOLDER_PATH + "/" + orderId);
            		OrderDataFetch orderDataFetch = getOrderData(file);
            		
            		if(orderDataFetch == null) {
            			throw new IOException();
            		}
            		
            		order.setBillingInfo(orderDataFetch.getBillingInfo());
            		for (Product product : orderDataFetch.getProducts()) {
						order.addProduct(product);
					}
            		
					result.add(order);
				} while (rs.next());
            }
            
		} catch (ClassNotFoundException ce) {
			Logger.getGlobal().log(Level.SEVERE, "Database driver not found");
		} catch (SQLException se) {
			Logger.getGlobal().log(Level.SEVERE, "Database query <" + query + "> failed");
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "File reading error");
		} catch (IdException ie) {
			Logger.getGlobal().log(Level.SEVERE, "Id logic error");
		}
        finally {
        	try {
        		if(rs != null) {
        			rs.close();
        		}
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "ResultSet closure error");
            }
            try {
                if (stmt != null) {
                	stmt.close();
                }      
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Statement closure error");
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Connection closure error");
            }
        }
		
		return result;
	}
	
	public static ArrayList<Order> getOrdersByEmail(String email) {
		ArrayList<Order> result = getOrders("SELECT * FROM " + TABLE_ORDERS + " WHERE " + TABLE_ORDERS_COL_EMAIL + " = '" + email +"';");;
		
		return result;
	}
	
	public static Order getOrderById(String id) {
		ArrayList<Order> result = getOrders("SELECT * FROM " + TABLE_ORDERS + " WHERE " + TABLE_ORDERS_COL_ID + " = '" + id +"';");	
		
		if(result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	
	public static String getLastId() {
		String result = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
            
            query = "SELECT MAX(" + TABLE_ORDERS_COL_ID + ") FROM " + TABLE_ORDERS + ";";
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	result = IdConverter.intToId(rs.getInt(1), IdConverter.Type.ORDER);
            }
		} catch (ClassNotFoundException ce) {
			Logger.getGlobal().log(Level.SEVERE, "Database driver not found");
		} catch (SQLException se) {
			Logger.getGlobal().log(Level.SEVERE, "Database query <" + query + "> failed");
		} catch (IdException ie) {
			Logger.getGlobal().log(Level.SEVERE, "Id logic error");
		}
		finally {
			try {
        		if(rs != null) {
        			rs.close();
        		}
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "ResultSet closure error");
            }
            try {
                if (stmt != null) {
                	stmt.close();
                }      
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Statement closure error");
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Connection closure error");
            }
		}
		
		return result;
	}
	
	
	private static void saveOrderData(Order order) {
		File file = new File(ORDERS_FOLDER_PATH + "/" + order.getId());
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			if(file.createNewFile()) {
				fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.write(printOrderData(order));
			}
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "File reading error");
		} finally {
			try {
                if (bufferedWriter != null) {
                	bufferedWriter.close();
                }
            } catch (IOException ioe) {
            	Logger.getGlobal().log(Level.WARNING, "BufferedWriter closure error");
            }
			try {
                if (fileWriter != null) {
                	fileWriter.close();
                }
            } catch (IOException ioe) {
            	Logger.getGlobal().log(Level.WARNING, "FileWriter closure error");
            }
		}
	}
	
	public static void insertOrder(Order order) {
		Connection conn = null;
		Statement stmt = null;
		String query = "";
        
		try {
        	DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            query = "INSERT INTO " + TABLE_ORDERS + " (" + TABLE_ORDERS_COL_EMAIL + ", " + TABLE_ORDERS_COL_DATE + ", " + TABLE_ORDERS_COL_PRICE + ", " + TABLE_ORDERS_COL_SHIPCODE + ", " + TABLE_ORDERS_COL_SHIPCOMP + ") " +
					"VALUES ('" + order.getEmail() + "', '" + order.getDate() + "', " + order.getPrice() + ", '" + order.getShippingCode() + "', '" + order.getShippingCompany() + "');";
            stmt.executeUpdate(query);
            
            saveOrderData(order);
		} catch (ClassNotFoundException ce) {
			Logger.getGlobal().log(Level.SEVERE, "Database driver not found");
		} catch (SQLException se) {
			Logger.getGlobal().log(Level.SEVERE, "Database query <" + query + "> failed");
		}
        finally {
        	try {
                if (stmt != null) {
                	stmt.close();
                }      
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Statement closure error");
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING, "Connection closure error");
            }
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
		private ArrayList<Product> products;

		
		private OrderDataFetch(BillingInfo billingInfo) {
			this.billingInfo = billingInfo;
			this.products = new ArrayList<Product>();
		}
		
		
		private BillingInfo getBillingInfo() {
			return this.billingInfo;
		}
		
		private void addProduct(Product product) {
			products.add(product);
		}
		
		private ArrayList<Product> getProducts() {
			return this.products;
		}
	}
}
