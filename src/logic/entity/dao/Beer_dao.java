package logic.entity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.entity.Beer;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;

public class Beer_dao {
	// Informazioni database
	private static String USER = "root";
	private static String PASS = "becrafter";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/becrafter";
	private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
	
	// Informazioni tabella birre
	private static String TABLE_NAME = "beers";
    private static String COL_ID = "id";
    private static String COL_NAME = "name";
    private static String COL_TYPE = "type";
    private static String COL_COLOR = "color";
    private static String COL_FILTERING = "filtering";
    private static String COL_ALCOHOL = "alcohol";
    private static String COL_PRICE = "price";
    private static String COL_DESCRIPTION = "description";
    private static String COL_RECIPEID = "recipeId";
    
    
	private Beer_dao() {
		
	}
	
	private static ArrayList<Beer> getBeers(String query){
		ArrayList<Beer> result = new ArrayList<Beer>();
		
		Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
        	Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            
            Beer beer = null;
            if(rs.first()) {
            	do {
            		beer = new Beer(rs.getString(COL_ID));
                	beer.setName(rs.getString(COL_NAME));
                	beer.setType(BeerType.valueOf(rs.getString(COL_TYPE)));
                	beer.setColor(BeerColor.valueOf(rs.getString(COL_COLOR)));
                	beer.setFiltered(BeerFiltering.valueOf(rs.getString(COL_FILTERING)));
                	beer.setAlcoholContent(rs.getFloat(COL_ALCOHOL));
                	beer.setPricePerLiter(rs.getFloat(COL_PRICE));
                	beer.setDescription(rs.getString(COL_DESCRIPTION));
                	beer.setRecipe(Recipe_dao.getRecipeById(rs.getString(COL_RECIPEID)));
            		
					result.add(beer);
				} while (rs.next());
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
	
	public static ArrayList<Beer> getAllBeers(){
		ArrayList<Beer> result = getBeers("SELECT * FROM " + TABLE_NAME + ";");
		
		return result;
	}
	
	public static Beer getBeerById(String id) {
		ArrayList<Beer> result = getBeers("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + id +"';");	
		
		if(result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	
	public static void updateBeer(Beer beer) {
		
	}
}
