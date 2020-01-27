package logic;


import javafx.stage.Stage;
import logic.designclasses.RefStorableList;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Volume;
import logic.entity.dao.Beer_dao;

public class BuyBeer_Controller {
	private static BuyBeer_Controller instance = null;
	
	private Product selectedProduct;
	private RefStorableList cart;
	private Stage buyProductStage;
	
	
	private BuyBeer_Controller() {
		initCart();
	}
	
	synchronized public static BuyBeer_Controller getInstance() {
		if(instance == null) {
			instance = new BuyBeer_Controller();
		}
		
		return instance;
	}
	
	
	public void initCart() {
		cart = new RefStorableList();
	}
	
	
	public void selectProduct(String beerId, ContainerType containerType, Volume containerVolume) {
		selectedProduct = getProduct(beerId, containerType, containerVolume);
	}
	
	
	public Product getSelectedProduct() {
		return selectedProduct;
	}
	
	public void addProductToCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		Product product =  getProduct(beerId, containerType, containerVolume);
		product.addQuantity(quantity);
		cart.add(product);
		
		if(buyProductStage != null) {
			buyProductStage.close();
		}
		buyProductStage = null;
		
		//Home_Controller.getInstance().setCartCounter(cart.size());
		System.out.println(cart);
	}
	
	
	public void updateProductInsideCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		//aggiunge al carrello il prodotto selezionato
		
	}
	
	public void removeProductFromCart(String beerId, ContainerType containerType, Volume containerVolume) {
		//aggiunge al carrello il prodotto selezionato
		
	}
	
	public void confirmProducts() {
		
	}
	
	public void confirmPurchase() {
		
	}
	
	public void endCheckout() {
		
	}

	
	private Product getProduct(String beerId, ContainerType containerType, Volume containerVolume) {
		Beer beer = Beer_dao.getBeerById(beerId);
		Container container = new Container(containerType, containerVolume);
		Product product = new Product(beer, container);
		
		return product;
	}
	
}
