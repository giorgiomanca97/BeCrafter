package logic.entity.dao;

import java.util.ArrayList;

import logic.entity.Recipe;

public class Recipe_dao {
	private static String RECIPES_PATH = ""; 
	
	public ArrayList<Recipe> getAllRecipes(){
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		
		return result;
	}
	
	public static Recipe getRecipeById(String id) {
		Recipe result = new Recipe(id);
		
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
