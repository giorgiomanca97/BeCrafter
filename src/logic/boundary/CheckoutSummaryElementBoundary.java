package logic.boundary;


import java.util.logging.Level;
import java.util.logging.Logger;

import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.bean.CheckoutSummaryBean;
import logic.designclasses.BeerImageLoader;
import logic.entity.Price;
import logic.entity.Volume;
import javafx.scene.control.TextField;

public class CheckoutSummaryElementBoundary {
	@FXML private Label lblNumber;
	@FXML private ImageView imgContainerType;
	@FXML private Label lblBeerName;
	@FXML private Label lblBeerType;
	@FXML private Label lblBeerColor;
	@FXML private Label lblBeerAlcohol;
	@FXML private Label lblBeerFiltering;
	@FXML private Label lblContainerVolume;
	@FXML private TextField tfQuantity;
	@FXML private Label lblTotalVolume;
	@FXML private Label lblPrice;
	
	private CheckoutSummaryBoundary csBoundary;
	private int index;
	private CheckoutSummaryBean checkoutSummaryBean;
	
	
	public boolean setElement(CheckoutSummaryBoundary csBoundary, int index) {
		this.checkoutSummaryBean = new CheckoutSummaryBean();
		if(this.checkoutSummaryBean.selectProductInCart(index)) {
			this.csBoundary = csBoundary;
			this.index = index;	
			
			this.checkoutSummaryBean.loadSelectedProduct();
			
			loadProduct();
			updateQuantity();
			return true;
		} else {
			return false;
		}
	}
	
	private void loadProduct() {
		lblNumber.setText(String.valueOf(this.index + 1));
		imgContainerType.setImage(BeerImageLoader.loadImage(checkoutSummaryBean.getContainerType()));
		lblBeerName.setText(checkoutSummaryBean.getBeerName());
		lblBeerType.setText(checkoutSummaryBean.getBeerType().toString());
		lblBeerColor.setText(checkoutSummaryBean.getBeerColor().toString());
		lblBeerAlcohol.setText(String.valueOf(checkoutSummaryBean.getBeerAlcohol()) + "%");
		lblBeerFiltering.setText(checkoutSummaryBean.getBeerFiltering().toString());
		lblContainerVolume.setText(Volume.toText(checkoutSummaryBean.getContainerVolume()));
		tfQuantity.setText(String.valueOf(checkoutSummaryBean.getQuantity()));
	}


	@FXML 
	public void onSubPressed() {
		int q = checkoutSummaryBean.getQuantity() - 1;
		if(q < 1) {
			q = 1;
		}
		checkoutSummaryBean.setQuantity(q);
		tfQuantity.setText(String.valueOf(q));
		
		updateQuantity();
	}


	@FXML 
	public void onQuantityChange() {
		try {
			int q = Integer.parseUnsignedInt(tfQuantity.getText());
			checkoutSummaryBean.setQuantity(q);
		} catch (NumberFormatException nfe) {
			tfQuantity.setText(String.valueOf(checkoutSummaryBean.getQuantity()));
		}
		
		updateQuantity();
	}


	@FXML 
	public void onAddPressed() {
		int q = checkoutSummaryBean.getQuantity() + 1;
		checkoutSummaryBean.setQuantity(q);
		tfQuantity.setText(String.valueOf(q));
		
		updateQuantity();
	}
	
	
	private void updateQuantity() {
		lblTotalVolume.setText(Volume.toText(checkoutSummaryBean.getContainerVolume() * checkoutSummaryBean.getQuantity()));
		lblPrice.setText(Price.toText(checkoutSummaryBean.getPrice() * checkoutSummaryBean.getQuantity()) + " €");
		
		try {
			checkoutSummaryBean.updateProductInsideCart();
		} catch (ProductNotFoundException e) {
			Logger.getGlobal().log(Level.SEVERE, "Product not found");
		} catch (StorableIllegalQuantityException e) {
			Logger.getGlobal().log(Level.SEVERE, "Storable illegal quantity");
		}
		
		csBoundary.updateOverallPrice(this.index, checkoutSummaryBean.getPrice() * checkoutSummaryBean.getQuantity());
	}
	

	@FXML 
	public void onDelPressed() {
		try {
			checkoutSummaryBean.removeProductFromCart();
		} catch (ProductNotFoundException e) {
			Logger.getGlobal().log(Level.SEVERE, "Product not found");
		}
		
		csBoundary.displayCart();
	}
}
