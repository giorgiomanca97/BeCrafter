package logic;


import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.PaymentRefusedException;
import error.StorableIllegalQuantityException;
import error.id.IdException;
import error.login.UsedEmailException;
import logic.designclasses.CloneStorableList;
import logic.designclasses.IdConverterTest;
import logic.entity.Beer;
import logic.entity.BillingInfo;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Order;
import logic.entity.Product;
import logic.entity.Volume;
import logic.entity.dao.Beer_dao;
import logic.entity.dao.Order_dao;
import logic.entity.dao.Registered_dao;
import logic.entity.interfaces.Storable;

public class BuyBeer_Controller {
	private static BuyBeer_Controller instance = null;
	
	private Product selectedProduct;
	private CloneStorableList cart;
	private Random random = null; 		// Attributo per metodo dummy
	
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
		cart = new CloneStorableList();
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
		
		try {
			product.setQuantity(quantity);
			cart.add(product);
		} catch (StorableIllegalQuantityException siqe) {
			Logger.getGlobal().log(Level.SEVERE, "Illegat quantity for product");
		}
	}
	
	public void updateProductInsideCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) {
		Product product = (Product) cart.get(getProduct(beerId, containerType, containerVolume));
		
		try {
			product.setQuantity(quantity);
			cart.update(product);
		} catch (StorableIllegalQuantityException siqe) {
			Logger.getGlobal().log(Level.SEVERE, "Illegat quantity for product");
		}
	}
	
	public void removeProductFromCart(String beerId, ContainerType containerType, Volume containerVolume) {
		cart.remove(getProduct(beerId, containerType, containerVolume));
	}
	
	public String confirmPurchase(String email, BillingInfo billingInfo) throws UsedEmailException, PaymentRefusedException, IdException {
		if(Registered_dao.getRegisteredByEmail(email) != null && !Login_Controller.getInstance().isLogged(email)) {
			throw new UsedEmailException("Email already used. Please login!");
		}
		
		String lastOrderId = Order_dao.getLastId();
		String orderId;
		
		if(lastOrderId == null) {
			orderId = IdConverterTest.intToId(1, IdConverterTest.Type.ORDER);
		}
		else {
			orderId = IdConverterTest.nextId(lastOrderId);
		}
		
		Order order = new Order(orderId);
		order.setEmail(email);
		
		
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if(month.length() == 1) {
			month = "0" + month;
		}
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if(day.length() == 1) {
			day = "0" + day;
		}
		String date = year + "/" + month + "/" + day;
		order.setDate(date);
		
		float price = 0;
		for (Storable storable : cart.getAll()) {
			Product product = (Product) storable;
			price += product.getTotalPrice();
			order.addProduct(product);
		}
		order.setPrice(price);
		
		order.setShippingCode("");
		order.setShippingCompany("");
		
		order.setBillingInfo(billingInfo);
		
		if(!checkPayment(price, billingInfo.getCard())) {
			throw new PaymentRefusedException();
		}
		
		Order_dao.insertOrder(order);
		initCart();
		
		return order.getId();
	}
	
	private boolean checkPayment(float price, String creditCard) {
		// Metodo dummy
		if(random == null) {
			random = new Random();
		}

		int value = random.nextInt(100);
		
		if(value < 75) {
			return true;
		} else {
			return false;
		}
	}

	
	private Product getProduct(String beerId, ContainerType containerType, Volume containerVolume) {
		Beer beer = Beer_dao.getBeerById(beerId);
		Container container = new Container(containerType, containerVolume);
		Product product = new Product(beer, container);
		
		return product;
	}
	
}
