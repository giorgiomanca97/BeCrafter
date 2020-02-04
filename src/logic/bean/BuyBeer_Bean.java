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

public class BuyBeer_Bean {
	private String bbBeerId;
	private String bbBeerName;
	private BeerType bbBeerType;
	private BeerColor bbBeerColor;
	private float bbBeerAlcohol;
	private BeerFiltering bbBeerFiltering;
	private String bbBeerDescription;
	private ContainerType bbContainerType;
	private int bbContainerVolume;
	private float bbPrice;
	private int bbQuantity;
	
	
	public BuyBeer_Bean() {
		bbBeerId = "";
		bbBeerName = "";
		bbBeerType = null;
		bbBeerColor = null;
		bbBeerAlcohol = 0f;
		bbBeerFiltering = null;
		bbBeerDescription = "";
		bbContainerType = null;
		bbContainerVolume = 0;
		bbPrice = 0f;
		bbQuantity = 0;
	}


	// Getters and Setters
	public String getBeerId() {
		return bbBeerId;
	}

	public void setBeerId(String beerId) {
		this.bbBeerId = beerId;
	}

	
	public String getBeerName() {
		return bbBeerName;
	}

	public void setBeerName(String beerName) {
		this.bbBeerName = beerName;
	}

	
	public BeerType getBeerType() {
		return bbBeerType;
	}

	public void setBeerType(BeerType beerType) {
		this.bbBeerType = beerType;
	}


	public BeerColor getBeerColor() {
		return bbBeerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.bbBeerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return bbBeerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.bbBeerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return bbBeerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.bbBeerFiltering = beerFiltering;
	}

	
	public String getBeerDescription() {
		return bbBeerDescription;
	}

	public void setBeerDescription(String beerDescription) {
		this.bbBeerDescription = beerDescription;
	}
	

	public ContainerType getContainerType() {
		return bbContainerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.bbContainerType = containerType;
	}


	public int getContainerVolume() {
		return bbContainerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.bbContainerVolume = containerVolume;
	}


	public float getPrice() {
		return bbPrice;
	}

	public void setPrice(float price) {
		this.bbPrice = price;
	}
	
	
	public int getQuantity() {
		return bbQuantity;
	}

	public void setQuantity(int quantity) {
		this.bbQuantity = quantity;
	}
	// ==============================
	
	
	public void selectProductForSale() throws ProductNotFoundException {
		Volume volume = new Volume(bbContainerVolume);
		BuyBeer_Controller.getInstance().selectProductForSale(bbBeerId, bbContainerType, volume);
	}
	
	public boolean loadSelectedProduct() {
		Product product = BuyBeer_Controller.getInstance().getSelectedProduct();
		
		if(product == null) {
			return false;
		}
		
		bbBeerId = product.getBeer().getId();
		bbBeerName = product.getBeer().getName();
		bbBeerType = product.getBeer().getType();
		bbBeerColor = product.getBeer().getColor();
		bbBeerAlcohol = product.getBeer().getAlcoholContent();
		bbBeerFiltering = product.getBeer().getFiltering();
		bbBeerDescription = product.getBeer().getDescription();
		bbContainerType = product.getContainer().getType();
		bbContainerVolume = product.getContainer().getVolume();
		bbPrice = product.getPrice();
		bbQuantity = product.getQuantity();
		
		return true;
	}
	
	public void addProductToCart() throws ProductNotFoundException, StorableIllegalQuantityException {
		if(bbQuantity <= 0) {
			throw new StorableIllegalQuantityException();
		}
		
		Volume volume = new Volume(bbContainerVolume);
		BuyBeer_Controller.getInstance().addProductToCart(bbBeerId, bbContainerType, volume, bbQuantity);
	}

}
