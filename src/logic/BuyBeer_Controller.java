package logic;


import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.designclasses.PageLoader;
import logic.designclasses.RefStorableList;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Volume;
import logic.entity.dao.Beer_dao;

public class BuyBeer_Controller {
	private static BuyBeer_Controller instance = null;
	
	private static final String WINDOW_TITLE = "Buy Product";
	private static final String FXML_FILEPATH = "/res/fxml/BuyBeer_View.fxml";
	
	private Product selectedProduct;
	private RefStorableList cart;
	private Stage buyProductStage;
	
	private BuyBeer_Controller() {
		
	}
	
	synchronized public static BuyBeer_Controller getInstance() {
		if(instance == null) {
			instance = new BuyBeer_Controller();
		}
		
		return instance;
	}
	
	public void init() throws Exception {
		cart = new RefStorableList();
	}
	
	
	
	
	public void openProductDetails(String beerId, ContainerType containerType, Volume containerVolume) {
		try {
			selectedProduct = getProduct(beerId, containerType, containerVolume);
			
			PageLoader pageLoader = new PageLoader(FXML_FILEPATH);
			
			if(buyProductStage != null) {
				buyProductStage.close();
			}
			buyProductStage = new Stage();
			buyProductStage.setTitle(WINDOW_TITLE);
			buyProductStage.setScene(new Scene(pageLoader.getRootView()));
			buyProductStage.setResizable(false);
			buyProductStage.show();
		} catch (IOException e) {
			//TODO: handle exception
		}
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
		
		System.out.println(cart);
	}
	
	public void checkout() {
		
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
