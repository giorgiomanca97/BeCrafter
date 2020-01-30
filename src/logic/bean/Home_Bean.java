package logic.bean;


import java.util.List;

import error.ProductNotFoundException;
import logic.BuyBeer_Controller;
import logic.Home_Controller;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;
import logic.entity.Volume;

public class Home_Bean {
	private String h_beerId;
	private String h_beerName;
	private BeerType h_beerType;
	private BeerColor h_beerColor;
	private float h_beerAlcohol;
	private BeerFiltering h_beerFiltering;
	private String h_beerDescription;
	private ContainerType h_containerType;
	private int h_containerVolume;
	private float h_price;
	private int h_quantity;
	
	
	public Home_Bean() {
		
	}
	
	
	// Getters and Setters
	public String getBeerId() {
		return h_beerId;
	}

	public void setBeerId(String beerId) {
		this.h_beerId = beerId;
	}

	
	public String getBeerName() {
		return h_beerName;
	}

	public void setBeerName(String beerName) {
		this.h_beerName = beerName;
	}

	
	public BeerType getBeerType() {
		return h_beerType;
	}

	public void setBeerType(BeerType beerType) {
		this.h_beerType = beerType;
	}


	public BeerColor getBeerColor() {
		return h_beerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.h_beerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return h_beerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.h_beerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return h_beerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.h_beerFiltering = beerFiltering;
	}

	
	public String getBeerDescription() {
		return h_beerDescription;
	}

	public void setBeerDescription(String beerDescription) {
		this.h_beerDescription = beerDescription;
	}
	

	public ContainerType getContainerType() {
		return h_containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.h_containerType = containerType;
	}


	public int getContainerVolume() {
		return h_containerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.h_containerVolume = containerVolume;
	}


	public float getPrice() {
		return h_price;
	}

	public void setPrice(float price) {
		this.h_price = price;
	}
	
	
	public int getQuantity() {
		return h_quantity;
	}

	public void setQuantity(int quantity) {
		this.h_quantity = quantity;
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
		h_beerId = product.getBeer().getId();
		h_beerName = product.getBeer().getName();
		h_beerType = product.getBeer().getType();
		h_beerColor = product.getBeer().getColor();
		h_beerAlcohol = product.getBeer().getAlcoholContent();
		h_beerFiltering = product.getBeer().getFiltering();
		h_beerDescription = product.getBeer().getDescription();
		h_containerType = product.getContainer().getType();
		h_containerVolume = product.getContainer().getVolume();
		h_price = product.getPrice();
		h_quantity = product.getQuantity();
	}
	
	public void selectForSaleProduct() throws ProductNotFoundException {
		Volume volume = new Volume(h_containerVolume);
		BuyBeer_Controller.getInstance().selectProductForSale(h_beerId, h_containerType, volume);
	}
}
