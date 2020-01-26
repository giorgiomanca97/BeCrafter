package logic;


import java.util.ArrayList;

import logic.boundary.Home_View;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Storehouse;
import logic.entity.dao.Storehouse_dao;

public class HomeController {
	private static HomeController instance = null;
	private Storehouse storehouse;
	
	
	private HomeController() {
		
	}
	
	synchronized public static HomeController getInstance() {
		if(instance == null) {
			instance = new HomeController();
		}
		
		return instance;
	}
	
	
	public void init() throws Exception {
		this.storehouse = Storehouse_dao.getStorehouse();
		
		Home_View.start();
	}
	
	public ArrayList<Product> showProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings, String searchName){
		return storehouse.getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
	}
	
	
}
