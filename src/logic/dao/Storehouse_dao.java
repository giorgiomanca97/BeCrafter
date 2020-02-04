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
import logic.designclasses.DaoHelper.QueryWord;
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
    
    // Informazioni della tabella dei containers
    private static String tableStoreCont = "storehouse_containers";
    private static String tableStoreContColType = "type";
    private static String tableStoreContColVolume = "volume";
    
    // Informazioni della tabella dei products
    private static String tableStoreProd = "storehouse_products";
    private static String tableStoreProdBeerId = "beerId";
    private static String tableStoreProdContType = "containerType";
    private static String tableStoreProdContVolume = "containerVolume";
    
    // Informazioni comuni alla tabella
    private static String tableStoreQuantity = "quantity";
    
    // Singleton instance per la storehouse che rappresenta il magazzino dell'azienda
	private static Storehouse storehouseInstance = null;
	
	
	private Storehouse_dao() {
		
	}
	
	
	public static synchronized Storehouse getStorehouse() {
		if(storehouseInstance == null) {
	        Statement stmt = null;
	        Connection conn = null;
	        ResultSet rsRawMaterials = null;
            ResultSet rsContainers = null;
            ResultSet rsProducts = null;
            String query = "";
	        
	        try {
	            conn = DaoHelper.getConnection();
	            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
	            
	            query = QueryWord.SELECT + " * " + QueryWord.FROM + " " + tableStoreRawm + ";";
	            rsRawMaterials = stmt.executeQuery(query);
	            List<RawMaterial> rawMaterials = new ArrayList<>();
	            if(rsRawMaterials.first()) {
	            	do {
	            		RawMaterialType type = RawMaterialType.valueOf(rsRawMaterials.getString(tableStoreRawmColType));
	     	            int quantity = rsRawMaterials.getInt(tableStoreQuantity);
	     	            
	     	            RawMaterial rawMaterial = new RawMaterial(type);
	     	            rawMaterial.setQuantity(quantity);
	     	            
	     	            rawMaterials.add(rawMaterial);
	            	} while (rsRawMaterials.next());
	            }
	            
	            query = QueryWord.SELECT + " * " + QueryWord.FROM + " " + tableStoreCont + ";";
	            rsContainers = stmt.executeQuery(query);
	            List<Container> containers = new ArrayList<>();
	            if(rsContainers.first()) {
	            	do {
	            		ContainerType type = ContainerType.valueOf(rsContainers.getString(tableStoreContColType));
	            		int volume = rsContainers.getInt(tableStoreContColVolume);
	            		int quantity = rsContainers.getInt(tableStoreQuantity);
	            		
	            		Container container = new Container(type, volume);
	            		container.setQuantity(quantity);
	            		
	            		containers.add(container);
	            	} while (rsContainers.next());
	            }
	            
	            query = QueryWord.SELECT + " * " + QueryWord.FROM + " " + tableStoreProd + ";";
	            rsProducts = stmt.executeQuery(query);
	            List<Product> products = new ArrayList<>();
	            if(rsProducts.first()) {
	            	do {
	            		String beerId = rsProducts.getString(tableStoreProdBeerId);
	            		ContainerType contType = ContainerType.valueOf(rsProducts.getString(tableStoreProdContType));
	            		int contVolume = rsProducts.getInt(tableStoreProdContVolume);
	            		int quantity = rsProducts.getInt(tableStoreQuantity);

	            		Beer beer = Beer_dao.getBeerById(beerId);
	            		Container container = new Container(contType, contVolume);
	            		container.setQuantity(quantity);
	            		
	            		Product product = new Product(beer, container);
	            		
	            		products.add(product);
	            	} while (rsProducts.next());
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
	            
	        } catch (ClassNotFoundException | SQLException | StorableIllegalQuantityException e) {
				Logger.getGlobal().log(Level.SEVERE, e.toString());
			} finally {
	        	List<ResultSet> rsList = new ArrayList<>();
	        	rsList.add(rsRawMaterials);
	        	rsList.add(rsContainers);
	        	rsList.add(rsProducts);
	        	
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
            
            query = QueryWord.DELETE + " " + tableStoreRawm + ";";
            stmt.executeUpdate(query);
            for (RawMaterial rawMaterial : storehouse.getAllRawMaterials()) {
            	query = QueryWord.INSERT + " " + tableStoreRawm + QueryWord.VALUES + " ('" + rawMaterial.getType().name() + "', " + rawMaterial.getQuantity() + ");";
            	stmt.executeUpdate(query);
			}
            
            query = QueryWord.DELETE + " " + tableStoreCont + ";";
            stmt.executeUpdate(query);
            for (Container container : storehouse.getAllContainers()) {
            	query = QueryWord.INSERT + " " + tableStoreCont + QueryWord.VALUES + " ('" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");";
				stmt.executeUpdate(query);
			}
            
            query = QueryWord.DELETE + " " + tableStoreProd + ";";
            stmt.executeUpdate(query);
            for (Product product : storehouse.getAllProducts()) {
            	Beer beer = product.getBeer();
            	Container container = product.getContainer();
            	query = QueryWord.INSERT + " " + tableStoreProd + QueryWord.VALUES + " ('" + beer.getId() + "', '" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");";
            	stmt.executeUpdate(query);
            }
            
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} finally {
        	DaoHelper.close(conn, stmt);
        }
	}
	
}
