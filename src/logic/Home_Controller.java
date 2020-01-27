package logic;


import java.util.ArrayList;

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
		this.storehouse = Storehouse_dao.getStorehouse();
	}
	
	synchronized public static Home_Controller getInstance() {
		if(instance == null) {
			instance = new Home_Controller();
		}
		
		return instance;
	}
	
	
	public ArrayList<Product> showProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings, String searchName){
		return storehouse.getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
	}	
}
