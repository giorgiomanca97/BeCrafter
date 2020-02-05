package logic;


import java.util.List;

import logic.dao.StorehouseDao;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Storehouse;

public class HomeController {
	private static HomeController instance = null;
	private Storehouse storehouse = null;
	private List<Product> displayedProducts = null;
	
	
	private HomeController() {
		storehouse = StorehouseDao.getStorehouse();
		displayProducts(null, null, null, null, null);
	}
	
	public static synchronized HomeController getInstance() {
		if(instance == null) {
			instance = new HomeController();
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
