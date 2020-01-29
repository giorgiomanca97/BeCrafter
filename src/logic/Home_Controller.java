package logic;


import java.util.ArrayList;

import logic.dao.Storehouse_dao;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Storehouse;

public class Home_Controller {
	private static Home_Controller instance = null;
	private Storehouse storehouse = null;
	private ArrayList<Product> displayedProducts = null;
	
	
	private Home_Controller() {
		storehouse = Storehouse_dao.getStorehouse();
		displayedProducts = new ArrayList<Product>();
		displayProducts(null, null, null, null, null);
	}
	
	synchronized public static Home_Controller getInstance() {
		if(instance == null) {
			instance = new Home_Controller();
		}
		
		return instance;
	}
	
	
	public void displayProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings, String searchName){
		displayedProducts = storehouse.getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
	}
	
	
	public int countProductsDisplayed() {
		return displayedProducts.size();
	}
	
	public Product getDisplayedProductsAt(int index) {
		return displayedProducts.get(index);
	}
}
