package logic.entity.beans;

import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;

public class Product_Bean {
	private String beerID;
	private BeerType beerType;
	private BeerColor beerColor;
	private float beerAlcohol;
	private BeerFiltering beerFiltering;
	private ContainerType containerType;
	private int containerVolume;

	
	public Product_Bean() {
		
	}


	public String getBeerID() {
		return beerID;
	}


	public void setBeerID(String beerID) {
		this.beerID = beerID;
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
	
}
