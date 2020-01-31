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
	private String bb_beerId;
	private String bb_beerName;
	private BeerType bb_beerType;
	private BeerColor bb_beerColor;
	private float bb_beerAlcohol;
	private BeerFiltering bb_beerFiltering;
	private String bb_beerDescription;
	private ContainerType bb_containerType;
	private int bb_containerVolume;
	private float bb_price;
	private int bb_quantity;
	
	
	public BuyBeer_Bean() {
		bb_beerId = "";
		bb_beerName = "";
		bb_beerType = null;
		bb_beerColor = null;
		bb_beerAlcohol = 0f;
		bb_beerFiltering = null;
		bb_beerDescription = "";
		bb_containerType = null;
		bb_containerVolume = 0;
		bb_price = 0f;
		bb_quantity = 0;
	}


	// Getters and Setters
	public String getBeerId() {
		return bb_beerId;
	}

	public void setBeerId(String beerId) {
		this.bb_beerId = beerId;
	}

	
	public String getBeerName() {
		return bb_beerName;
	}

	public void setBeerName(String beerName) {
		this.bb_beerName = beerName;
	}

	
	public BeerType getBeerType() {
		return bb_beerType;
	}

	public void setBeerType(BeerType beerType) {
		this.bb_beerType = beerType;
	}


	public BeerColor getBeerColor() {
		return bb_beerColor;
	}

	public void setBeerColor(BeerColor beerColor) {
		this.bb_beerColor = beerColor;
	}


	public float getBeerAlcohol() {
		return bb_beerAlcohol;
	}

	public void setBeerAlcohol(float beerAlcohol) {
		this.bb_beerAlcohol = beerAlcohol;
	}


	public BeerFiltering getBeerFiltering() {
		return bb_beerFiltering;
	}

	public void setBeerFiltering(BeerFiltering beerFiltering) {
		this.bb_beerFiltering = beerFiltering;
	}

	
	public String getBeerDescription() {
		return bb_beerDescription;
	}

	public void setBeerDescription(String beerDescription) {
		this.bb_beerDescription = beerDescription;
	}
	

	public ContainerType getContainerType() {
		return bb_containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.bb_containerType = containerType;
	}


	public int getContainerVolume() {
		return bb_containerVolume;
	}

	public void setContainerVolume(int containerVolume) {
		this.bb_containerVolume = containerVolume;
	}


	public float getPrice() {
		return bb_price;
	}

	public void setPrice(float price) {
		this.bb_price = price;
	}
	
	
	public int getQuantity() {
		return bb_quantity;
	}

	public void setQuantity(int quantity) {
		this.bb_quantity = quantity;
	}
	// ==============================
	
	
	public void selectForSaleProduct() throws ProductNotFoundException {
		Volume volume = new Volume(bb_containerVolume);
		BuyBeer_Controller.getInstance().selectProductForSale(bb_beerId, bb_containerType, volume);
	}
	
	public boolean loadSelectedProduct() {
		Product product = BuyBeer_Controller.getInstance().getSelectedProduct();
		
		if(product == null) {
			return false;
		}
		
		bb_beerId = product.getBeer().getId();
		bb_beerName = product.getBeer().getName();
		bb_beerType = product.getBeer().getType();
		bb_beerColor = product.getBeer().getColor();
		bb_beerAlcohol = product.getBeer().getAlcoholContent();
		bb_beerFiltering = product.getBeer().getFiltering();
		bb_beerDescription = product.getBeer().getDescription();
		bb_containerType = product.getContainer().getType();
		bb_containerVolume = product.getContainer().getVolume();
		bb_price = product.getPrice();
		bb_quantity = product.getQuantity();
		
		return true;
	}
	
	public void addProductToCart() throws ProductNotFoundException, StorableIllegalQuantityException {
		if(bb_quantity <= 0) {
			throw new StorableIllegalQuantityException();
		}
		
		Volume volume = new Volume(bb_containerVolume);
		BuyBeer_Controller.getInstance().addProductToCart(bb_beerId, bb_containerType, volume, bb_quantity);
	}

}
