package logic.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.StorableIllegalQuantityException;
import logic.designclasses.DaoHelper;
import logic.designclasses.DaoHelper.StatementMode;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.RawMaterial;
import logic.entity.RawMaterialType;
import logic.entity.Storehouse;

public class Storehouse_dao {
    // Informazioni della tabella dei raw materials
    private static String tableStoreRawm = "storehouse_rawmaterials";
    private static String tableStoreRawmColType = "type";
    private static String tableStoreRawmColQuantity = "quantity";
    
    // Informazioni della tabella dei containers
    private static String tableStoreCont = "storehouse_containers";
    private static String tableStoreContColType = "type";
    private static String tableStoreContColVolume = "volume";
    private static String tableStoreContColQuantity = "quantity";
    
    // Informazioni della tabella dei products
    private static String tableStoreProd = "storehouse_products";
    private static String tableStoreProdBeerId = "beerId";
    private static String tableStoreProdContType = "containerType";
    private static String tableStoreProdContVolume = "containerVolume";
    private static String tableStoreProdQuantity = "quantity";
    
    // Singleton instance per la storehouse che rappresenta il magazzino dell'azienda
	private static Storehouse storehouseInstance = null;
	
	
	private Storehouse_dao() {
		
	}
	
	
	synchronized public static Storehouse getStorehouse() {
		if(storehouseInstance == null) {
	        Statement stmt = null;
	        Connection conn = null;
	        ResultSet rs_rawMaterials = null;
            ResultSet rs_containers = null;
            ResultSet rs_products = null;
            String query = "";
	        
	        try {
	            conn = DaoHelper.getConnection();
	            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
	            
	            query = "SELECT * FROM " + tableStoreRawm + ";";
	            rs_rawMaterials = stmt.executeQuery(query);
	            List<RawMaterial> rawMaterials = new ArrayList<>();
	            if(rs_rawMaterials.first()) {
	            	do {
	            		RawMaterialType type = RawMaterialType.valueOf(rs_rawMaterials.getString(tableStoreRawmColType));
	     	            int quantity = rs_rawMaterials.getInt(tableStoreRawmColQuantity);
	     	            
	     	            RawMaterial rawMaterial = new RawMaterial(type);
	     	            rawMaterial.setQuantity(quantity);
	     	            
	     	            rawMaterials.add(rawMaterial);
	            	} while (rs_rawMaterials.next());
	            }
	            
	            query = "SELECT * FROM " + tableStoreCont + ";";
	            rs_containers = stmt.executeQuery(query);
	            List<Container> containers = new ArrayList<>();
	            if(rs_containers.first()) {
	            	do {
	            		ContainerType type = ContainerType.valueOf(rs_containers.getString(tableStoreContColType));
	            		int volume = rs_containers.getInt(tableStoreContColVolume);
	            		int quantity = rs_containers.getInt(tableStoreContColQuantity);
	            		
	            		Container container = new Container(type, volume);
	            		container.setQuantity(quantity);
	            		
	            		containers.add(container);
	            	} while (rs_containers.next());
	            }
	            
	            query = "SELECT * FROM " + tableStoreProd + ";";
	            rs_products = stmt.executeQuery(query);
	            List<Product> products = new ArrayList<>();
	            if(rs_products.first()) {
	            	do {
	            		String beerId = rs_products.getString(tableStoreProdBeerId);
	            		ContainerType contType = ContainerType.valueOf(rs_products.getString(tableStoreProdContType));
	            		int contVolume = rs_products.getInt(tableStoreProdContVolume);
	            		int quantity = rs_products.getInt(tableStoreProdQuantity);

	            		Beer beer = Beer_dao.getBeerById(beerId);
	            		Container container = new Container(contType, contVolume);
	            		container.setQuantity(quantity);
	            		
	            		Product product = new Product(beer, container);
	            		
	            		products.add(product);
	            	} while (rs_products.next());
	            }
	             
	            storehouseInstance = new Storehouse();
	            for (RawMaterial r : rawMaterials) {
	            	storehouseInstance.add(r);
				}
	            for (Container c : containers) {
	            	storehouseInstance.add(c);
				}
	            for (Product p : products) {
	            	storehouseInstance.add(p);
				}
	            
	        } catch (ClassNotFoundException ce) {
				Logger.getGlobal().log(Level.SEVERE, "Database driver not found");
			} catch (SQLException se) {
				Logger.getGlobal().log(Level.SEVERE, "Database query <" + query + "> failed");
			} catch (StorableIllegalQuantityException siqe) {
				Logger.getGlobal().log(Level.SEVERE, "Database Storable wrong quantity");
			} 
	        finally {
	        	List<ResultSet> rsList = new ArrayList<>();
	        	rsList.add(rs_rawMaterials);
	        	rsList.add(rs_containers);
	        	rsList.add(rs_products);
	        	
	        	DaoHelper.close(conn, stmt, rsList);
	        }
		}
		
		return storehouseInstance;
	}
	
	
	public static void updateStorehouse(Storehouse storehouse) {
		Connection conn = null;
		Statement stmt = null;
        String query = "";

        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            query = "DELETE FROM " + tableStoreRawm + ";";
            stmt.executeUpdate(query);
            for (RawMaterial rawMaterial : storehouse.getAllRawMaterials()) {
            	query = "INSERT INTO " + tableStoreRawm + "VALUES ('" + rawMaterial.getType().name() + "', " + rawMaterial.getQuantity() + ");";
            	stmt.executeUpdate(query);
			}
            
            query = "DELETE FROM " + tableStoreCont + ";";
            stmt.executeUpdate(query);
            for (Container container : storehouse.getAllContainers()) {
            	query = "INSERT INTO " + tableStoreCont + "VALUES ('" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");";
				stmt.executeUpdate(query);
			}
            
            query = "DELETE FROM " + tableStoreProd + ";";
            stmt.executeUpdate(query);
            for (Product product : storehouse.getAllProducts()) {
            	Beer beer = product.getBeer();
            	Container container = product.getContainer();
            	query = "INSERT INTO " + tableStoreProd + "VALUES ('" + beer.getId() + "', '" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");";
            	stmt.executeUpdate(query);
            }
            
		} catch (ClassNotFoundException ce) {
			Logger.getGlobal().log(Level.SEVERE, "Database driver not found");
		} catch (SQLException se) {
			Logger.getGlobal().log(Level.SEVERE, "Database query <" + query + "> failed");
		}
        finally {
        	DaoHelper.close(conn, stmt);
        }
	}
	
}
