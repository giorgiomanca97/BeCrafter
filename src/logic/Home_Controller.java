package logic;


import java.util.ArrayList;

import logic.boundary.Home_Boundary;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Storehouse;
import logic.entity.dao.Storehouse_dao;

public class Home_Controller {
	private static Home_Controller instance = null;
	private Storehouse storehouse;
	
	
	private Home_Controller() {
		
	}
	
	synchronized public static Home_Controller getInstance() {
		if(instance == null) {
			instance = new Home_Controller();
		}
		
		return instance;
	}
	
	
	public void init() throws Exception {
		this.storehouse = Storehouse_dao.getStorehouse();
		Home_Boundary.start();
		BuyBeer_Controller.getInstance().init();
	}
	
	public ArrayList<Product> showProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings, String searchName){
		return storehouse.getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
	}
	
	public void goToCheckout()  {
		BuyBeer_Controller.getInstance().checkout();
	}
	
}
