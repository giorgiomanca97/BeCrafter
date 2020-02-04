package logic.bean;


import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import logic.BuyBeer_Controller;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Volume;

public class CheckoutSummary_Bean{
	private String csBeerId;
	private String csBeerName;
	private BeerType csBeerType;
	private BeerColor csBeerColor;
	private float csBeerAlcohol;
	private BeerFiltering csBeerFiltering;
	private ContainerType csContainerType;
	private int csContainerVolume;
	private float csPrice;
	private int csQuantity;
	
	
	public CheckoutSummary_Bean() {
		csBeerId = "";
		csBeerName = "";
		csBeerType = null;
		csBeerColor = null;
		csBeerAlcohol = 0f;
		csBeerFiltering = null;
		csContainerType = null;
		csContainerVolume = 0;
		csPrice = 0f;
		csQuantity = 0;
	}


	// Getters and Setters
	public String getBeerId() {
		return csBeerId;
	}

	public void setBeerId(String beerId) {
		this.csBeerId = beerId;
	}

	
	public String getBeerName() {
		return csBeerName;
	}

	public void setBeerName(String beerName) {
		this.csBeerName = beerName;
	}

	
	public BeerType getBeerType() {
		return csBeerType;
	}

	public void setBeerType(BeerType beerType) {
		this.csBeerType = beerType;
	}


	public BeerColor getBeerColor() {
		return csBeerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.csBeerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return csBeerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.csBeerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return csBeerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.csBeerFiltering = beerFiltering;
	}
	

	public ContainerType getContainerType() {
		return csContainerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.csContainerType = containerType;
	}


	public int getContainerVolume() {
		return csContainerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.csContainerVolume = containerVolume;
	}


	public float getPrice() {
		return csPrice;
	}

	public void setPrice(float price) {
		this.csPrice = price;
	}
	
	
	public int getQuantity() {
		return csQuantity;
	}

	public void setQuantity(int quantity) {
		this.csQuantity = quantity;
	}
	// ==============================
	
	
	public void loadSelectedProduct() {
		Product product = BuyBeer_Controller.getInstance().getSelectedProduct();
		csBeerId = product.getBeer().getId();
		csBeerName = product.getBeer().getName();
		csBeerType = product.getBeer().getType();
		csBeerColor = product.getBeer().getColor();
		csBeerAlcohol = product.getBeer().getAlcoholContent();
		csBeerFiltering = product.getBeer().getFiltering();
		csContainerType = product.getContainer().getType();
		csContainerVolume = product.getContainer().getVolume();
		csPrice = product.getPrice();
		csQuantity = product.getQuantity();
	}
	
	public boolean selectProductInCart(int index) {		
		return BuyBeer_Controller.getInstance().selectProductInCart(index);
	}
	
	public int cartSize() {
		return BuyBeer_Controller.getInstance().getCartSize();
	}
	
	public void updateProductInsideCart() throws ProductNotFoundException, StorableIllegalQuantityException {
		Volume volume = new Volume(csContainerVolume);
		BuyBeer_Controller.getInstance().updateProductInsideCart(csBeerId, csContainerType, volume, csQuantity);
	}
	
	public void removeProductFromCart() throws ProductNotFoundException {
		Volume volume = new Volume(csContainerVolume);
		BuyBeer_Controller.getInstance().removeProductFromCart(csBeerId, csContainerType, volume);
	}
}
