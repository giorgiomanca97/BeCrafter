package logic.entity.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.designclasses.DaoHelper;
import logic.designclasses.DaoHelper.StatementMode;
import logic.entity.Beer;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;

public class Beer_dao {
	// Informazioni tabella birre
	private static String TABLE_BEERS = "beers";
    private static String TABLE_BEERS_COL_ID = "id";
    private static String TABLE_BEERS_COL_NAME = "name";
    private static String TABLE_BEERS_COL_TYPE = "type";
    private static String TABLE_BEERS_COL_COLOR = "color";
    private static String TABLE_BEERS_COL_FILTERING = "filtering";
    private static String TABLE_BEERS_COL_ALCOHOL = "alcohol";
    private static String TABLE_BEERS_COL_PRICE = "price";
    private static String TABLE_BEERS_COL_DESCRIPTION = "description";
    private static String TABLE_BEERS_COL_RECIPEID = "recipeId";
    
    
	private Beer_dao() {
		
	}
	
	private static ArrayList<Beer> getBeers(String query){
		ArrayList<Beer> result = new ArrayList<Beer>();
		
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
            		Beer beer = new Beer(rs.getString(TABLE_BEERS_COL_ID));
                	beer.setName(rs.getString(TABLE_BEERS_COL_NAME));
                	beer.setType(BeerType.valueOf(rs.getString(TABLE_BEERS_COL_TYPE)));
                	beer.setColor(BeerColor.valueOf(rs.getString(TABLE_BEERS_COL_COLOR)));
                	beer.setFiltered(BeerFiltering.valueOf(rs.getString(TABLE_BEERS_COL_FILTERING)));
                	beer.setAlcoholContent(rs.getFloat(TABLE_BEERS_COL_ALCOHOL));
                	beer.setPricePerLiter(rs.getFloat(TABLE_BEERS_COL_PRICE));
                	beer.setDescription(rs.getString(TABLE_BEERS_COL_DESCRIPTION));
                	beer.setRecipe(Recipe_dao.getRecipeById(rs.getString(TABLE_BEERS_COL_RECIPEID)));
            		
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
		ArrayList<Beer> result = getBeers("SELECT * FROM " + TABLE_BEERS + ";");
		
		return result;
	}
	
	public static Beer getBeerById(String id) {
		ArrayList<Beer> result = getBeers("SELECT * FROM " + TABLE_BEERS + " WHERE " + TABLE_BEERS_COL_ID + " = '" + id +"';");	
		
		if(result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	
	public static void updateBeer(Beer beer) {
		// TODO: non necessario per la parte di sistema implementata
	}
}
