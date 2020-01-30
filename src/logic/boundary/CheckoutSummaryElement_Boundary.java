package logic.boundary;


import java.util.logging.Level;
import java.util.logging.Logger;

import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.bean.CheckoutSummary_Bean;
import logic.designclasses.BeerImageLoader;
import logic.entity.Price;
import logic.entity.Volume;
import javafx.scene.control.TextField;

public class CheckoutSummaryElement_Boundary {
	@FXML private Label lbl_number;
	@FXML private ImageView img_containerType;
	@FXML private Label lbl_beerName;
	@FXML private Label lbl_beerType;
	@FXML private Label lbl_beerColor;
	@FXML private Label lbl_beerAlcohol;
	@FXML private Label lbl_beerFiltering;
	@FXML private Label lbl_containerVolume;
	@FXML private TextField tf_quantity;
	@FXML private Label lbl_totalVolume;
	@FXML private Label lbl_price;
	
	private CheckoutSummary_Boundary csBoundary;
	private int index;
	private CheckoutSummary_Bean checkoutSummaryBean;
	
	
	public boolean setElement(CheckoutSummary_Boundary csBoundary, int index) {
		this.checkoutSummaryBean = new CheckoutSummary_Bean();
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
		lbl_number.setText(String.valueOf(this.index + 1));
		img_containerType.setImage(BeerImageLoader.loadImage(checkoutSummaryBean.getContainerType()));
		lbl_beerName.setText(checkoutSummaryBean.getBeerName());
		lbl_beerType.setText(checkoutSummaryBean.getBeerType().toString());
		lbl_beerColor.setText(checkoutSummaryBean.getBeerColor().toString());
		lbl_beerAlcohol.setText(String.valueOf(checkoutSummaryBean.getBeerAlcohol()) + "%");
		lbl_beerFiltering.setText(checkoutSummaryBean.getBeerFiltering().toString());
		lbl_containerVolume.setText(Volume.toText(checkoutSummaryBean.getContainerVolume()));
		tf_quantity.setText(String.valueOf(checkoutSummaryBean.getQuantity()));
	}


	@FXML 
	public void onSubPressed() {
		int q = checkoutSummaryBean.getQuantity() - 1;
		if(q < 1) {
			q = 1;
		}
		checkoutSummaryBean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
		
		updateQuantity();
	}


	@FXML 
	public void onQuantityChange() {
		try {
			int q = Integer.parseUnsignedInt(tf_quantity.getText());
			checkoutSummaryBean.setQuantity(q);
		} catch (NumberFormatException nfe) {
			tf_quantity.setText(String.valueOf(checkoutSummaryBean.getQuantity()));
		}
		
		updateQuantity();
	}


	@FXML 
	public void onAddPressed() {
		int q = checkoutSummaryBean.getQuantity() + 1;
		checkoutSummaryBean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
		
		updateQuantity();
	}
	
	
	private void updateQuantity() {
		lbl_totalVolume.setText(Volume.toText(checkoutSummaryBean.getContainerVolume() * checkoutSummaryBean.getQuantity()));
		lbl_price.setText(Price.toText(checkoutSummaryBean.getPrice() * checkoutSummaryBean.getQuantity()) + " €");
		
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
