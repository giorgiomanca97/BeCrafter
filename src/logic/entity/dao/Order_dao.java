package logic.entity.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import error.TextParseException;
import logic.designclasses.IDconverter;
import logic.entity.BillingInfo;
import logic.entity.Order;
import logic.entity.Product;

public class Order_dao {
	private static String ORDERS_FOLDER_PATH = "persistence/orders"; 
	
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	    
	// Informazioni tabella ordini
	private static String TABLE_NAME = "orders";
	private static String COL_ID = "id";
	private static String COL_EMAIL = "email";
	private static String COL_DATE = "date";
	private static String COL_PRICE = "price";
	private static String COL_SHIPCODE = "shippingCode";
	private static String COL_SHIPCOMP = "shippingCompany";
	
	
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

		} catch (TextParseException tpe) {

		} finally {
			try {
                if (bufferedReader != null) {
                	bufferedReader.close();
                }
            } catch (IOException ioe) {
            }
			try {
                if (fileReader != null) {
                	fileReader.close();
                }
            } catch (IOException ioe) {
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
        	Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	do {
            		String orderId = IDconverter.intToId(rs.getInt(COL_ID), IDconverter.Type.ORDER);
            		Order order = new Order(orderId);
            		order.setEmail(rs.getString(COL_EMAIL));
            		order.setDate(rs.getString(COL_DATE));
            		order.setPrice(rs.getFloat(COL_PRICE));
            		order.setShippingCode(rs.getString(COL_SHIPCODE));
            		order.setShippingCompany(rs.getString(COL_SHIPCOMP));
            		
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
			// TODO: handle exception
		} catch (SQLException se) {
			// TODO: handle exception
		} catch (IOException ioe) {
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
	
	public static ArrayList<Order> getOrdersByEmail(String email) {
		ArrayList<Order> result = getOrders("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = '" + email +"';");;
		
		return result;
	}
	
	public static Order getOrderById(String id) {
		ArrayList<Order> result = getOrders("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + id +"';");	
		
		if(result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	
	public static String getNextId() {
		String result = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = stmt.executeQuery("SELECT MAX(" + COL_ID + ") FROM " + TABLE_NAME + ";");
            
            if(rs.first()) {
            	result = rs.getString(COL_ID);
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
			// TODO: handle exception
		} finally {
			try {
                if (bufferedWriter != null) {
                	bufferedWriter.close();
                }
            } catch (IOException ioe) {
            }
			try {
                if (fileWriter != null) {
                	fileWriter.close();
                }
            } catch (IOException ioe) {
            }
		}
	}
	
	public static void insertOrder(Order order) {
		Connection conn = null;
		Statement stmt = null;
        
		try {
        	Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME + " (" + COL_EMAIL + ", " + COL_DATE + ", " + COL_PRICE + ", " + COL_SHIPCODE + ", " + COL_SHIPCOMP + ") " +
            					"VALUES ('" + order.getEmail() + "', '" + order.getDate() + "', " + order.getPrice() + ", '" + order.getShippingCode() + "', '" + order.getShippingCompany() + "');");
            
            saveOrderData(order);
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
	
	
	private static String printOrderData(Order order) {
		StringBuilder stringBuilder = new StringBuilder();
		
		BillingInfo billingInfo = order.getBillingInfo();
		
		stringBuilder.append(order.getId());
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
