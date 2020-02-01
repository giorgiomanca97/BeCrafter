package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.id.IdException;
import logic.designclasses.DaoHelper;
import logic.designclasses.IdConverter;
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
	
	private static List<Beer> getBeers(String query){
		List<Beer> result = new ArrayList<>();
		
		Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DaoHelper.getConnection();
            stmt = DaoHelper.getStatement(conn, StatementMode.READ);
            rs = stmt.executeQuery(query);
            
            if(rs.first()) {
            	do {
            		Beer beer = new Beer(IdConverter.intToId(rs.getInt(TABLE_BEERS_COL_ID), IdConverter.Type.BEER));
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
			Logger.getGlobal().log(Level.SEVERE, "Database driver not found");
		} catch (SQLException se) {
			Logger.getGlobal().log(Level.SEVERE, "Database query <" + query + "> failed");
		} catch (IdException ie) {
			Logger.getGlobal().log(Level.SEVERE, "Id logic error");
		}
        finally {
        	DaoHelper.close(conn, stmt, rs);
        }
		
		return result;
	}
	
	public static List<Beer> getAllBeers(){
		List<Beer> result = getBeers("SELECT * FROM " + TABLE_BEERS + ";");
		
		return result;
	}
	
	public static Beer getBeerById(String id) {
		try {
			int dbId = IdConverter.idToInt(id);
			List<Beer> result = getBeers("SELECT * FROM " + TABLE_BEERS + " WHERE " + TABLE_BEERS_COL_ID + " = '" + dbId +"';");	
			
			if(result.size() == 0) {
				return null;
			} else {
				return result.get(0);
			}
		} catch (IdException ie) {
			Logger.getGlobal().log(Level.SEVERE, "Id logic error");
			return null;
		}
	}
	
	
	public static void updateBeer(Beer beer) {
		// Non necessario per la parte di sistema implementata
	}
}
