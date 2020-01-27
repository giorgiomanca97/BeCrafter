package logic;


import error.StorableIllegalQuantityException;
import logic.designclasses.RefStorableList;
import logic.entity.Beer;
import logic.entity.BillingInfo;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Volume;
import logic.entity.dao.Beer_dao;
import logic.entity.interfaces.Storable;

public class BuyBeer_Controller {
	private static BuyBeer_Controller instance = null;
	
	private Product selectedProduct;
	private RefStorableList cart;
	
	
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
	
	
	public void selectForSaleProduct(String beerId, ContainerType containerType, Volume containerVolume) {
		Product product = getProduct(beerId, containerType, containerVolume);
		this.selectedProduct = product;
	}
	
	public boolean selectProductInCart(int index) {
		if(index < cart.size()) {
			Storable storable = cart.getAt(index);
			selectedProduct = (Product) storable;
			return true;
		} else {
			return false;
		}
	}
	
	public Product getSelectedProduct() {
		return selectedProduct;
	}
	
	public int getCartSize() {
		return cart.size();
	}
	
	public void addProductToCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		Product product = getProduct(beerId, containerType, containerVolume);
		product.addQuantity(quantity);
		cart.add(product);
	}
	
	public void updateProductInsideCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		Product product = (Product) cart.get(getProduct(beerId, containerType, containerVolume));
		
		try {
			product.setQuantity(quantity);
			cart.update(product);
		} catch (StorableIllegalQuantityException siqe) {
			// TODO: handle exception
		}
	}
	
	public void removeProductFromCart(String beerId, ContainerType containerType, Volume containerVolume) {
		cart.remove(getProduct(beerId, containerType, containerVolume));
	}
	
	public boolean confirmPurchase(String email, BillingInfo billingInfo) {
		return true;
	}

	
	private Product getProduct(String beerId, ContainerType containerType, Volume containerVolume) {
		Beer beer = Beer_dao.getBeerById(beerId);
		Container container = new Container(containerType, containerVolume);
		Product product = new Product(beer, container);
		
		return product;
	}
	
}
