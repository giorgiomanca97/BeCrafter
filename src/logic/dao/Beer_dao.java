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
	private static String tableBeers = "beers";
    private static String tableBeersColId = "id";
    private static String tableBeersColName = "name";
    private static String tableBeersColType = "type";
    private static String tableBeersColColor = "color";
    private static String tableBeersColFiltering = "filtering";
    private static String tableBeersColAlcohol = "alcohol";
    private static String tableBeersColPrice = "price";
    private static String tableBeersColDescription = "description";
    private static String tableBeersColRecipeId = "recipeId";
    
    
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
            		Beer beer = new Beer(IdConverter.intToId(rs.getInt(tableBeersColId), IdConverter.Type.BEER));
                	beer.setName(rs.getString(tableBeersColName));
                	beer.setType(BeerType.valueOf(rs.getString(tableBeersColType)));
                	beer.setColor(BeerColor.valueOf(rs.getString(tableBeersColColor)));
                	beer.setFiltered(BeerFiltering.valueOf(rs.getString(tableBeersColFiltering)));
                	beer.setAlcoholContent(rs.getFloat(tableBeersColAlcohol));
                	beer.setPricePerLiter(rs.getFloat(tableBeersColPrice));
                	beer.setDescription(rs.getString(tableBeersColDescription));
                	beer.setRecipe(Recipe_dao.getRecipeById(rs.getString(tableBeersColRecipeId)));
            		
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
		List<Beer> result = getBeers("SELECT * FROM " + tableBeers + ";");
		
		return result;
	}
	
	public static Beer getBeerById(String id) {
		try {
			int dbId = IdConverter.idToInt(id);
			List<Beer> result = getBeers("SELECT * FROM " + tableBeers + " WHERE " + tableBeersColId + " = '" + dbId +"';");	
			
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
