package logic.entity.dao;

public class Order_dao {
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	    
	// Informazioni tabella ordini
	private static String TABLE_NAME = "orders";
	private static String COL_ID = "id";
	private static String COL_DATE = "date";
	private static String COL_EMAIL = "email";
	private static String COL_PRICE = "price";
	private static String COL_FIRSTNAME = "firstName";
	private static String COL_LASTNAME = "lastName";
	private static String COL_ADDRESS = "address";
	private static String COL_CITY = "city";
	private static String COL_COUNTRY = "country";
	private static String COL_POSTALCODE = "postalCode";
	private static String COL_PHONE = "phone";
	private static String COL_CARD = "card";
	
	private Order_dao() {
		
	}
}
