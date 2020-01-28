package logic.entity.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    private static String TABLE_STORE_RAWM = "storehouse_rawmaterials";
    private static String TABLE_STORE_RAWM_COL_TYPE = "type";
    private static String TABLE_STORE_RAWM_COL_QUANTITY = "quantity";
    
    // Informazioni della tabella dei containers
    private static String TABLE_STORE_CONT = "storehouse_containers";
    private static String TABLE_STORE_CONT_COL_TYPE = "type";
    private static String TABLE_STORE_CONT_COL_VOLUME = "volume";
    private static String TABLE_STORE_CONT_COL_QUANTITY = "quantity";
    
    // Informazioni della tabella dei products
    private static String TABLE_STORE_PROD = "storehouse_products";
    private static String TABLE_STORE_PROD_BEERID = "beerId";
    private static String TABLE_STORE_PROD_COL_CONTTYPE = "containerType";
    private static String TABLE_STORE_PROD_COL_CONTVOLUME = "containerVolume";
    private static String TABLE_STORE_PROD_COL_QUANTITY = "quantity";
    
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
	        	DaoHelper.loadDriver();
	            conn = DaoHelper.getConnection();
	            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
	            
	            query = "SELECT * FROM " + TABLE_STORE_RAWM + ";";
	            rs_rawMaterials = stmt.executeQuery(query);
	            ArrayList<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
	            if(rs_rawMaterials.first()) {
	            	do {
	            		RawMaterialType type = RawMaterialType.valueOf(rs_rawMaterials.getString(TABLE_STORE_RAWM_COL_TYPE));
	     	            int quantity = rs_rawMaterials.getInt(TABLE_STORE_RAWM_COL_QUANTITY);
	     	            
	     	            RawMaterial rawMaterial = new RawMaterial(type);
	     	            rawMaterial.setQuantity(quantity);
	     	            
	     	            rawMaterials.add(rawMaterial);
	            	} while (rs_rawMaterials.next());
	            }
	            
	            query = "SELECT * FROM " + TABLE_STORE_CONT + ";";
	            rs_containers = stmt.executeQuery(query);
	            ArrayList<Container> containers = new ArrayList<Container>();
	            if(rs_containers.first()) {
	            	do {
	            		ContainerType type = ContainerType.valueOf(rs_containers.getString(TABLE_STORE_CONT_COL_TYPE));
	            		int volume = rs_containers.getInt(TABLE_STORE_CONT_COL_VOLUME);
	            		int quantity = rs_containers.getInt(TABLE_STORE_CONT_COL_QUANTITY);
	            		
	            		Container container = new Container(type, volume);
	            		container.setQuantity(quantity);
	            		
	            		containers.add(container);
	            	} while (rs_containers.next());
	            }
	            
	            query = "SELECT * FROM " + TABLE_STORE_PROD + ";";
	            rs_products = stmt.executeQuery(query);
	            ArrayList<Product> products = new ArrayList<Product>();
	            if(rs_products.first()) {
	            	do {
	            		String beerId = rs_products.getString(TABLE_STORE_PROD_BEERID);
	            		ContainerType contType = ContainerType.valueOf(rs_products.getString(TABLE_STORE_PROD_COL_CONTTYPE));
	            		int contVolume = rs_products.getInt(TABLE_STORE_PROD_COL_CONTVOLUME);
	            		int quantity = rs_products.getInt(TABLE_STORE_PROD_COL_QUANTITY);

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
	        	try {
	                if (rs_rawMaterials != null)
	                	rs_rawMaterials.close();
	            } catch (SQLException se) {
	            	Logger.getGlobal().log(Level.WARNING, "ResultSet closure error");
	            }
	        	try {
	        		if(rs_containers != null) {
	        			rs_containers.close();
	        		}
	            } catch (SQLException se) {
	            	Logger.getGlobal().log(Level.WARNING, "ResultSet closure error");
	            }
	        	try {
	        		if(rs_products != null) {
	        			rs_products.close();
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
		}
		
		return storehouseInstance;
	}
	
	
	public static void updateStorehouse(Storehouse storehouse) {
		Statement stmt = null;
        Connection conn = null;
        String query = "";

        try {
        	DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            query = "DELETE FROM " + TABLE_STORE_RAWM + ";";
            stmt.executeUpdate(query);
            for (RawMaterial rawMaterial : storehouse.getAllRawMaterials()) {
            	query = "INSERT INTO " + TABLE_STORE_RAWM + "VALUES ('" + rawMaterial.getType().name() + "', " + rawMaterial.getQuantity() + ");";
            	stmt.executeUpdate(query);
			}
            
            query = "DELETE FROM " + TABLE_STORE_CONT + ";";
            stmt.executeUpdate(query);
            for (Container container : storehouse.getAllContainers()) {
            	query = "INSERT INTO " + TABLE_STORE_CONT + "VALUES ('" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");";
				stmt.executeUpdate(query);
			}
            
            query = "DELETE FROM " + TABLE_STORE_PROD + ";";
            stmt.executeUpdate(query);
            for (Product product : storehouse.getAllProducts()) {
            	Beer beer = product.getBeer();
            	Container container = product.getContainer();
            	query = "INSERT INTO " + TABLE_STORE_PROD + "VALUES ('" + beer.getId() + "', '" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");";
            	stmt.executeUpdate(query);
            }
            
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
	
}
