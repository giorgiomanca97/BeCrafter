package logic.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.TextParseException;
import logic.designclasses.DaoHelper;
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
				
				StringBuilder recipeTextBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null){
					recipeTextBuilder.append(line + "\n");
				}
				
				result = textToRecipe(recipeTextBuilder.toString());
			}
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "File reading error");
		} catch (TextParseException tpe) {
			Logger.getGlobal().log(Level.SEVERE, "File parsing error");
		} finally {
			DaoHelper.close(bufferedReader);
			DaoHelper.close(fileReader);
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
		// Non necessario per la parte di sistema implementata
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
