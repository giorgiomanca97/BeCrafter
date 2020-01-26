package logic.entity.beans;


import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Volume;

public class Product_Bean {
	private String beerId;
	private String beerName;
	private BeerType beerType;
	private BeerColor beerColor;
	private float beerAlcohol;
	private BeerFiltering beerFiltering;
	private ContainerType containerType;
	private int containerVolume;
	private float price;

	
	public Product_Bean() {
		
	}


	public String getBeerId() {
		return beerId;
	}

	public void setBeerId(String beerId) {
		this.beerId = beerId;
	}

	
	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	
	public BeerType getBeerType() {
		return beerType;
	}

	public void setBeerType(BeerType beerType) {
		this.beerType = beerType;
	}


	public BeerColor getBeerColor() {
		return beerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.beerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return beerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.beerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return beerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.beerFiltering = beerFiltering;
	}


	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}


	public int getContainerVolume() {
		return containerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.containerVolume = containerVolume;
	}


	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
