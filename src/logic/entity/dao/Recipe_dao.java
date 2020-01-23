package logic.entity.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import error.TextParseException;
import logic.entity.RawMaterial;
import logic.entity.Recipe;

public class Recipe_dao {
	private static String RECIPES_FOLDER_PATH = "persistence/recipes"; 
	
	
	private Recipe_dao() {
		
	}
	
	
	private static Recipe getRecipe(File file) {
		Recipe result = null;
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {			
			if(file.exists()) {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				StringBuilder textBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null){
					textBuilder.append(line + "\n");
				}
				
				result = textToRecipe(textBuilder.toString());
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
	
	public static ArrayList<Recipe> getAllRecipes(){
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		
		File directory = new File(RECIPES_FOLDER_PATH);
		File[] files = directory.listFiles();
		
		for (File file : files) {
			if(file.isFile()) {
				result.add(getRecipe(file));
			}
		}
		
		return result;
	}
	
	public static Recipe getRecipeById(String id) {
		File file = new File(RECIPES_FOLDER_PATH + "/" + id);
		
		if(file.exists()) {
			return getRecipe(file);
		} else {
			return null;
		}
	}
	
	
	public static void updateRecipe(Recipe recipe) {
		// TODO: non necessario per la parte di sistema implementata
	}
	
	
	public static String recipeToText(Recipe recipe) {
		StringBuilder stringBuilder = new StringBuilder();
		ArrayList<RawMaterial> ingredients = recipe.getIngredients();
		
		stringBuilder.append(recipe.getId() + "\n");
		
		for (RawMaterial rawMaterial : ingredients) {
			stringBuilder.append(RawMaterial_dao.rawMaterialToText(rawMaterial) + "\n");
		}
		
		return stringBuilder.toString();
	}
	
	public static Recipe textToRecipe(String text) throws TextParseException {
		Recipe result;
		
		String[] pieces = text.split("\n");
		
		try {
			result = new Recipe(pieces[0]);
			
			for(int i = 1; i < pieces.length; i++) {
				RawMaterial rawMaterial = RawMaterial_dao.textToRawMaterial(pieces[i]);
				result.addIngredient(rawMaterial);
			}
					
			return result;
		} catch (Exception e) {
			throw new TextParseException(e);
		}
	}
}
