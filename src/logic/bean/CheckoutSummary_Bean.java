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
	private String cs_beerId;
	private String cs_beerName;
	private BeerType cs_beerType;
	private BeerColor cs_beerColor;
	private float cs_beerAlcohol;
	private BeerFiltering cs_beerFiltering;
	private ContainerType cs_containerType;
	private int cs_containerVolume;
	private float cs_price;
	private int cs_quantity;
	
	
	public CheckoutSummary_Bean() {
		cs_beerId = "";
		cs_beerName = "";
		cs_beerType = null;
		cs_beerColor = null;
		cs_beerAlcohol = 0f;
		cs_beerFiltering = null;
		cs_containerType = null;
		cs_containerVolume = 0;
		cs_price = 0f;
		cs_quantity = 0;
	}


	// Getters and Setters
	public String getBeerId() {
		return cs_beerId;
	}

	public void setBeerId(String beerId) {
		this.cs_beerId = beerId;
	}

	
	public String getBeerName() {
		return cs_beerName;
	}

	public void setBeerName(String beerName) {
		this.cs_beerName = beerName;
	}

	
	public BeerType getBeerType() {
		return cs_beerType;
	}

	public void setBeerType(BeerType beerType) {
		this.cs_beerType = beerType;
	}


	public BeerColor getBeerColor() {
		return cs_beerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.cs_beerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return cs_beerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.cs_beerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return cs_beerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.cs_beerFiltering = beerFiltering;
	}
	

	public ContainerType getContainerType() {
		return cs_containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.cs_containerType = containerType;
	}


	public int getContainerVolume() {
		return cs_containerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.cs_containerVolume = containerVolume;
	}


	public float getPrice() {
		return cs_price;
	}

	public void setPrice(float price) {
		this.cs_price = price;
	}
	
	
	public int getQuantity() {
		return cs_quantity;
	}

	public void setQuantity(int quantity) {
		this.cs_quantity = quantity;
	}
	// ==============================
	
	
	public void loadSelectedProduct() {
		Product product = BuyBeer_Controller.getInstance().getSelectedProduct();
		cs_beerId = product.getBeer().getId();
		cs_beerName = product.getBeer().getName();
		cs_beerType = product.getBeer().getType();
		cs_beerColor = product.getBeer().getColor();
		cs_beerAlcohol = product.getBeer().getAlcoholContent();
		cs_beerFiltering = product.getBeer().getFiltering();
		cs_containerType = product.getContainer().getType();
		cs_containerVolume = product.getContainer().getVolume();
		cs_price = product.getPrice();
		cs_quantity = product.getQuantity();
	}
	
	public boolean selectProductInCart(int index) {		
		return BuyBeer_Controller.getInstance().selectProductInCart(index);
	}
	
	public int cartSize() {
		return BuyBeer_Controller.getInstance().getCartSize();
	}
	
	public void updateProductInsideCart() throws ProductNotFoundException, StorableIllegalQuantityException {
		Volume volume = new Volume(cs_containerVolume);
		BuyBeer_Controller.getInstance().updateProductInsideCart(cs_beerId, cs_containerType, volume, cs_quantity);
	}
	
	public void removeProductFromCart() throws ProductNotFoundException {
		Volume volume = new Volume(cs_containerVolume);
		BuyBeer_Controller.getInstance().removeProductFromCart(cs_beerId, cs_containerType, volume);
	}
}
