package logic.entity.dao;

import java.util.ArrayList;

import logic.entity.Beer;

public class Beer_dao {
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	
	// Informazioni tabella birre
	private static String TABLE = "storehouse_rawmaterials";
    private static String COL_ID = "";
    private static String COL_NAME = "";
	// TODO:completare colonne
	
	private Beer_dao() {
		
	}
	
	public ArrayList<Beer> getAllBeers(){
		ArrayList<Beer> result = new ArrayList<Beer>();
		
		return result;
	}
	
	public static Beer getBeerById(String id) {
		Beer result = null;
		
		
		return result;
	}
	
	public static void updateBeer(Beer beer) {
		
	}
}
