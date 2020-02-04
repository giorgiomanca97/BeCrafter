package logic.bean;


import java.util.List;

import error.ProductNotFoundException;
import logic.BuyBeer_Controller;
import logic.Home_Controller;
import logic.Login_Controller;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Registered;
import logic.entity.Volume;

public class Home_Bean {
	private String hBeerId;
	private String hBeerName;
	private BeerType hBeerType;
	private BeerColor hBeerColor;
	private float hBeerAlcohol;
	private BeerFiltering hBeerFiltering;
	private String hBeerDescription;
	private ContainerType hContainerType;
	private int hContainerVolume;
	private float hPrice;
	private int hQuantity;
	
	
	public Home_Bean() {
		hBeerId = "";
		hBeerName = "";
		hBeerType = null;
		hBeerColor = null;
		hBeerAlcohol = 0f;
		hBeerFiltering = null;
		hBeerDescription = "";
		hContainerType = null;
		hContainerVolume = 0;
		hPrice = 0f;
		hQuantity = 0;
	}
	
	
	// Getters and Setters
	public String getBeerId() {
		return hBeerId;
	}

	public void setBeerId(String beerId) {
		this.hBeerId = beerId;
	}

	
	public String getBeerName() {
		return hBeerName;
	}

	public void setBeerName(String beerName) {
		this.hBeerName = beerName;
	}

	
	public BeerType getBeerType() {
		return hBeerType;
	}

	public void setBeerType(BeerType beerType) {
		this.hBeerType = beerType;
	}


	public BeerColor getBeerColor() {
		return hBeerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.hBeerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return hBeerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.hBeerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return hBeerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.hBeerFiltering = beerFiltering;
	}

	
	public String getBeerDescription() {
		return hBeerDescription;
	}

	public void setBeerDescription(String beerDescription) {
		this.hBeerDescription = beerDescription;
	}
	

	public ContainerType getContainerType() {
		return hContainerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.hContainerType = containerType;
	}


	public int getContainerVolume() {
		return hContainerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.hContainerVolume = containerVolume;
	}


	public float getPrice() {
		return hPrice;
	}

	public void setPrice(float price) {
		this.hPrice = price;
	}
	
	
	public int getQuantity() {
		return hQuantity;
	}

	public void setQuantity(int quantity) {
		this.hQuantity = quantity;
	}
	// ==============================
	
	
	public void displayProducts(List<BeerType> beerTypes, List<BeerColor> beerColors, List<ContainerType> containerTypes, List<BeerFiltering> beerFilterings, String searchName){
		Home_Controller.getInstance().displayProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
	}
	
	public int countDisplayedProducts() {
		return Home_Controller.getInstance().countProductsDisplayed();
	}
	
	public void loadDisplayedProductAt(int index) {
		Product product = Home_Controller.getInstance().getDisplayedProductsAt(index);
		hBeerId = product.getBeer().getId();
		hBeerName = product.getBeer().getName();
		hBeerType = product.getBeer().getType();
		hBeerColor = product.getBeer().getColor();
		hBeerAlcohol = product.getBeer().getAlcoholContent();
		hBeerFiltering = product.getBeer().getFiltering();
		hBeerDescription = product.getBeer().getDescription();
		hContainerType = product.getContainer().getType();
		hContainerVolume = product.getContainer().getVolume();
		hPrice = product.getPrice();
		hQuantity = product.getQuantity();
	}
	
	public void selectForSaleProduct() throws ProductNotFoundException {
		Volume volume = new Volume(hContainerVolume);
		BuyBeer_Controller.getInstance().selectProductForSale(hBeerId, hContainerType, volume);
	}
	
	public String loggedCustomer() {
		Registered registered = Login_Controller.getInstance().getLoggedCustomer();
		
		if(registered == null) {
			return null;
		} else {
			return registered.getEmail();
		}
	}
	
	public void logoutCustomer(String email) {
		Login_Controller.getInstance().logout(email);;
	}
}
