package logic.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.TextParseException;
import logic.designclasses.DaoHelper;
import logic.entity.RawMaterial;
import logic.entity.Recipe;

public class RecipeDao {	
	private RecipeDao() {
		
	}
	
	
	private static Recipe getRecipe(File file) {
		Recipe result = null;
		
		try (
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		) {			
			StringBuilder recipeTextBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null){
				recipeTextBuilder.append(line + "\n");
			}
			
			result = textToRecipe(recipeTextBuilder.toString());
		} catch (IOException | TextParseException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		} 
		
		return result;
	}
	
	public static List<Recipe> getAllRecipes(){
		List<Recipe> result = new ArrayList<>();
		
		File directory = new File(DaoHelper.getRecipesPath(false));
		File[] files = directory.listFiles();
		
		for (File file : files) {
			if(file.isFile()) {
				result.add(getRecipe(file));
			}
		}
		
		return result;
	}
	
	public static Recipe getRecipeById(String id) {
		File file = new File(DaoHelper.getRecipesPath(true) + id);
		
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
		List<RawMaterial> ingredients = recipe.getIngredients();
		
		stringBuilder.append(recipe.getId() + "\n");
		
		for (RawMaterial rawMaterial : ingredients) {
			stringBuilder.append(RawMaterialDao.rawMaterialToText(rawMaterial) + "\n");
		}
		
		return stringBuilder.toString();
	}
	
	public static Recipe textToRecipe(String text) throws TextParseException {
		Recipe result;
		
		String[] pieces = text.split("\n");
		
		try {
			result = new Recipe(pieces[0]);
			
			for(int i = 1; i < pieces.length; i++) {
				RawMaterial rawMaterial = RawMaterialDao.textToRawMaterial(pieces[i]);
				result.addIngredient(rawMaterial);
			}
					
			return result;
		} catch (Exception e) {
			throw new TextParseException(e);
		}
	}
}
