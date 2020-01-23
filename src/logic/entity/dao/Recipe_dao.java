package logic.entity.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import error.TextParseException;
import logic.entity.Recipe;

public class Recipe_dao {
	private static String RECIPES_FOLDER_PATH = "persistence/recipes/"; 
	
	public ArrayList<Recipe> getAllRecipes(){
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		
		return result;
	}
	
	public static Recipe getRecipeById(String id) {
		Recipe result = null;
		File file = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			file = new File(RECIPES_FOLDER_PATH + id);
			
			if(file.exists()) {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				result = new Recipe(id);
				String line;
				while ((line = bufferedReader.readLine()) != null){
					result.addIngredient(RawMaterial_dao.textToRawMaterial(line));
				}
			}
		} catch (IOException ioe) {

		} catch (TextParseException tpe) {

		} finally {
			try {
                if (bufferedReader != null) {
                	bufferedReader.close();
                }
            } catch (IOException ioe) {
            }
			try {
                if (fileReader != null) {
                	fileReader.close();
                }
            } catch (IOException ioe) {
            }
		}
		
		return result;
	}
	
	public static void updateRecipe(Recipe recipe) {
		
	}
	
	public String recipeToText(Recipe recipe) {
		StringBuilder stringBuilder = new StringBuilder();
		
		return stringBuilder.toString();
	}
	
	public Recipe textToRecipe(String text) {
		Recipe result = null;
		
		return result;
	}
}
