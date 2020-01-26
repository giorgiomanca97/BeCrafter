package logic;


import logic.boundary.BuyBeer_Boundary;
import logic.designclasses.PageLoader;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Storehouse;
import logic.entity.Volume;
import logic.entity.dao.Beer_dao;

public class BuyBeer_Controller {
	private static BuyBeer_Controller instance = null;
	private Storehouse cart;
	
	private BuyBeer_Controller() {
		
	}
	
	synchronized public static BuyBeer_Controller getInstance() {
		if(instance == null) {
			instance = new BuyBeer_Controller();
		}
		
		return instance;
	}
	
	public void init() throws Exception {
		cart = new Storehouse();
	}
	
	public void addProductToCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		//aggiunge al carrello il prodotto selezionato
		
	}
	
	public void updateProductInsideCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		//aggiunge al carrello il prodotto selezionato
		
	}
	
	public void removeProductFromCart(String beerId, ContainerType containerType, Volume containerVolume) {
		//aggiunge al carrello il prodotto selezionato
		
	}
	
	public void checkout() {
		
	}
}
