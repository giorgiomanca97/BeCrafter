package logic;


import java.util.List;

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
	private List<Product> displayedProducts = null;
	
	
	private Home_Controller() {
		storehouse = Storehouse_dao.getStorehouse();
		displayProducts(null, null, null, null, null);
	}
	
	public static synchronized Home_Controller getInstance() {
		if(instance == null) {
			instance = new Home_Controller();
		}
		
		return instance;
	}
	
	
	public void displayProducts(List<BeerType> beerTypes, List<BeerColor> beerColors, List<ContainerType> containerTypes, List<BeerFiltering> beerFilterings, String searchName){
		displayedProducts = storehouse.getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
	}
	
	public int countProductsDisplayed() {
		if(displayedProducts != null) {
			return displayedProducts.size();
		} else {
			return 0;
		}
	}
	
	public Product getDisplayedProductsAt(int index) {
		if(displayedProducts != null && index < displayedProducts.size()) {
			return displayedProducts.get(index);
		} else {
			return null;
		}
	}
}
