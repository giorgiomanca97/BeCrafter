package logic.entity.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    private static String RAWM_TABLE = "storehouse_rawmaterials";
    private static String RAWM_COL_TYPE = "type";
    private static String RAWM_COL_QUANTITY = "quantity";
    
    // Informazioni della tabella dei containers
    private static String CONT_TABLE = "storehouse_containers";
    private static String CONT_COL_TYPE = "type";
    private static String CONT_COL_VOLUME = "volume";
    private static String CONT_COL_QUANTITY = "quantity";
    
    // Informazioni della tabella dei products
    private static String PROD_TABLE = "storehouse_products";
    private static String PROD_BEERID = "beerId";
    private static String PROD_COL_CONTTYPE = "containerType";
    private static String PROD_COL_CONTVOLUME = "containerVolume";
    private static String PROD_COL_QUANTITY = "quantity";
    
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
	        
	        try {
	        	DaoHelper.loadDriver();
	            conn = DaoHelper.getConnection();
	            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
	            
	            rs_rawMaterials = stmt.executeQuery("SELECT * FROM " + RAWM_TABLE + ";");
	            rs_containers = stmt.executeQuery("SELECT * FROM " + CONT_TABLE + ";");
	            rs_products = stmt.executeQuery("SELECT * FROM " + PROD_TABLE + ";");
	            
	            ArrayList<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
	            if(rs_rawMaterials.first()) {
	            	do {
	            		RawMaterialType type = RawMaterialType.valueOf(rs_rawMaterials.getString(RAWM_COL_TYPE));
	     	            int quantity = rs_rawMaterials.getInt(RAWM_COL_QUANTITY);
	     	            
	     	            RawMaterial rawMaterial = new RawMaterial(type);
	     	            rawMaterial.setQuantity(quantity);
	     	            
	     	            rawMaterials.add(rawMaterial);
	            	} while (rs_rawMaterials.next());
	            }
	            
	            ArrayList<Container> containers = new ArrayList<Container>();
	            if(rs_containers.first()) {
	            	do {
	            		ContainerType type = ContainerType.valueOf(rs_containers.getString(CONT_COL_TYPE));
	            		int volume = rs_containers.getInt(CONT_COL_VOLUME);
	            		int quantity = rs_containers.getInt(CONT_COL_QUANTITY);
	            		
	            		Container container = new Container(type, volume);
	            		container.setQuantity(quantity);
	            		
	            		containers.add(container);
	            	} while (rs_containers.next());
	            }
	            
	            ArrayList<Product> products = new ArrayList<Product>();
	            if(rs_products.first()) {
	            	do {
	            		String beerId = rs_products.getString(PROD_BEERID);
	            		ContainerType contType = ContainerType.valueOf(rs_products.getString(PROD_COL_CONTTYPE));
	            		int contVolume = rs_products.getInt(PROD_COL_CONTVOLUME);
	            		int quantity = rs_products.getInt(PROD_COL_QUANTITY);

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
				// TODO: handle exception
			} catch (SQLException se) {
				// TODO: handle exception
			} catch (StorableIllegalQuantityException siqe) {
				// TODO: handle exception
			} 
	        finally {
	        	try {
	                if (rs_rawMaterials != null)
	                	rs_rawMaterials.close();
	            } catch (SQLException se) {
	            }
	        	try {
	        		if(rs_containers != null) {
	        			rs_containers.close();
	        		}
	            } catch (SQLException se) {
	            }
	        	try {
	        		if(rs_products != null) {
	        			rs_products.close();
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
		}
		
		return storehouseInstance;
	}
	
	
	public static void updateStorehouse(Storehouse storehouse) {
		Statement stmt = null;
        Connection conn = null;

        try {
        	DaoHelper.loadDriver();
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.WRITE);
            
            stmt.executeUpdate("DELETE FROM " + RAWM_TABLE + ";");
            stmt.executeUpdate("DELETE FROM " + CONT_TABLE + ";");
            stmt.executeUpdate("DELETE FROM " + PROD_TABLE + ";");
            
            for (RawMaterial rawMaterial : storehouse.getAllRawMaterials()) {
            	stmt.executeUpdate("INSERT INTO " + RAWM_TABLE + "VALUES ('" + rawMaterial.getType().name() + "', " + rawMaterial.getQuantity() + ");");
			}
            for (Container container : storehouse.getAllContainers()) {
				stmt.executeUpdate("INSERT INTO " + CONT_TABLE + "VALUES ('" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");");
			}
            for (Product product : storehouse.getAllProducts()) {
            	Beer beer = product.getBeer();
            	Container container = product.getContainer();
            	stmt.executeUpdate("INSERT INTO " + PROD_TABLE + "VALUES ('" + beer.getId() + "', '" + container.getType().name() + "', " + container.getVolume() + ", " + container.getQuantity() + ");");
            }
            
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
            }
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException se) {
            }
        }
	}
	
}
