package logic;


import java.security.SecureRandom;
import java.util.Calendar;

import error.EmptyCartException;
import error.PaymentRefusedException;
import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import error.id.IdException;
import error.login.UsedEmailException;
import logic.dao.Beer_dao;
import logic.dao.Order_dao;
import logic.dao.Registered_dao;
import logic.dao.Storehouse_dao;
import logic.designclasses.StorableCloneList;
import logic.designclasses.CheckHelper;
import logic.designclasses.IdConverter;
import logic.entity.Beer;
import logic.entity.BillingInfo;
import logic.entity.Container;
import logic.entity.ContainerType;
import logic.entity.Order;
import logic.entity.Product;
import logic.entity.Storehouse;
import logic.entity.Volume;
import logic.entity.interfaces.Storable;

public class BuyBeer_Controller {
	private static BuyBeer_Controller instance = null;
	
	private Storehouse storehouse;
	private Product selectedProduct;
	private StorableCloneList cart;
	private SecureRandom random = null; 		// Attributo per metodo dummy
	
	private BuyBeer_Controller() {
		storehouse = Storehouse_dao.getStorehouse();
		initCart();
	}
	
	public static synchronized BuyBeer_Controller getInstance() {
		if(instance == null) {
			instance = new BuyBeer_Controller();
		}
		
		return instance;
	}
	
	
	public void initCart() {
		cart = new StorableCloneList();
	}
	
	
	public void selectProductForSale(String beerId, ContainerType containerType, Volume containerVolume) throws ProductNotFoundException {
		Product product = storehouse.get(beerId, containerType, containerVolume);
		
		if(product == null) {
			throw new ProductNotFoundException();
		}
		product.resetQuantity();
		
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
	
	public void addProductToCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) throws ProductNotFoundException, StorableIllegalQuantityException {
		Product product = storehouse.get(beerId, containerType, containerVolume);
		
		if(product == null) {
			throw new ProductNotFoundException();
		}
		
		product.setQuantity(quantity);
		
		cart.add(product);
	}
	
	public void updateProductInsideCart(String beerId, ContainerType containerType, Volume containerVolume, int quantity) throws ProductNotFoundException, StorableIllegalQuantityException {
		try {
			Product cartProduct = (Product) cart.get(getProduct(beerId, containerType, containerVolume));
			
			if(cartProduct == null) {
				throw new ProductNotFoundException();
			}
			
			cartProduct.setQuantity(quantity);
			
			cart.update(cartProduct);
		} catch (NullPointerException npe) {
			throw new ProductNotFoundException(npe);
		}
	}
	
	public void removeProductFromCart(String beerId, ContainerType containerType, Volume containerVolume) throws ProductNotFoundException {
		try {
			Product cartProduct = (Product) cart.remove(getProduct(beerId, containerType, containerVolume));
			
			if(cartProduct == null) {
				throw new ProductNotFoundException();
			}
		} catch (NullPointerException npe) {
			throw new ProductNotFoundException(npe);
		}
		
	}
	
	public String confirmPurchase(String email, BillingInfo billingInfo) throws EmptyCartException, UsedEmailException, PaymentRefusedException, IdException {
		if(cart.size() == 0) {
			throw new EmptyCartException();
		}
		
		if(Registered_dao.getRegisteredByEmail(email) != null && !Login_Controller.getInstance().isLogged(email)) {
			throw new UsedEmailException("Email already used. Please login!");
		}
		
		String lastOrderId = Order_dao.getLastId();
		String orderId;
		
		if(lastOrderId == null) {
			orderId = IdConverter.intToId(1, IdConverter.Type.ORDER);
		}
		else {
			orderId = IdConverter.nextId(lastOrderId);
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
	
	
	private Product getProduct(String beerId, ContainerType containerType, Volume containerVolume) {
		Beer beer = Beer_dao.getBeerById(beerId);
		if(beer == null) {
			return null;
		}
		
		Container container = new Container(containerType, containerVolume);
		return new Product(beer, container);
	}
	
	private boolean checkPayment(float price, String creditCard) {
		// Metodo dummy
		if(random == null) {
			random = new SecureRandom();
		}
		
		if(price < 0) {
			return false;
		}
		
		if(!CheckHelper.isValidCreditCard(creditCard)) {
			return false;
		}

		int value = random.nextInt(100);
		
		return (value < 75);
	}
}
