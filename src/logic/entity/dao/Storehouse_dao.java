package logic.entity.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.entity.Container;
import logic.entity.Product;
import logic.entity.RawMaterial;
import logic.entity.Storehouse;

public class Storehouse_dao {
	// Informazioni database
	private static String USER = "root";
    private static String PASS = "becrafter";
    private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
    private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    
    // Informazioni della tabella dei raw materials
    private static String RAWM_TABLE = "storehouse_rawmaterials";
    private static String RAWM_COL_TYPE = "";
    private static String RAWM_COL_QUANTITY = "";
    
    // Informazioni della tabella dei containers
    private static String CONT_TABLE = "storehouse_containers";
    private static String CONT_COL_TYPE = "";
    private static String CONT_COL_VOLUME = "";
    private static String CONT_COL_QUANTITY = "";
    
    // Informazioni della tabella dei products
    private static String PROD_TABLE = "storehouse_products";
    private static String PROD_BEERID = "";
    private static String PROD_COL_CONTTYPE = "";
    private static String PROD_COL_CONTVOLUME = "";
    private static String PROD_COL_QUANTITY = "";
    
    // Singleton instance per la storehouse che rappresenta il magazzino dell'azienda
	private static Storehouse storehouseInstance = null;
	
	private Storehouse_dao() {
		
	}
	
	synchronized public static Storehouse getStorehouse() {
		if(storehouseInstance == null) {
	        Statement stmt = null;
	        Connection conn = null;
	        
	        try {
	            Class.forName(DRIVER_CLASS_NAME);
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            ResultSet rs_rawMaterials = stmt.executeQuery("SELECT * FROM " + RAWM_TABLE + ";");
	            ResultSet rs_containers = stmt.executeQuery("SELECT * FROM " + CONT_TABLE + ";");
	            ResultSet rs_products = stmt.executeQuery("SELECT * FROM " + PROD_TABLE + ";");

	            
	            ArrayList<RawMaterial> rawMaterials;
	            if(rs_rawMaterials.first()) {
	            	do {
	            		
	            	} while (rs_rawMaterials.next());
	            }
	            
	            ArrayList<Container> containers;
	            if(rs_containers.first()) {
	            	do {
	            		
	            	} while (rs_containers.next());
	            }
	            
	            ArrayList<Product> products;
	            if(rs_products.first()) {
	            	do {
	            		
	            	} while (rs_products.next());
	            }
	            
	            // riposizionamento del cursore
	            rs.first();
	            do{

	                int idBeer = rs.getInt("idBeer");
	                int containerType = rs.getInt("containerType");
	                int containerVoulme = rs.getInt("containerVolume");
	                
	                System.out.println(idBeer + " " + containerType + " " + containerVoulme);
	            }while(rs.next());
	            
	            rs_rawMaterials.close();
	            rs_containers.close();
	            rs_products.close();
	            
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
	            
	        } catch (Exception e){
	        	
	        } finally {
	            // STEP 5.2: Clean-up dell'ambiente
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
		}
		
		return storehouseInstance;
	}
	
	public static void updateStorehouse() {
		if(storehouseInstance != null) {
			//TODO: aggioranare persistenza
		}
	}
	
	
}
