package logic.boundary;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logic.bean.BuyBeer_Bean;
import logic.designclasses.BeerImageLoader;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import logic.entity.Volume;

public class BuyBeer_Boundary {		
	@FXML private Label tbBeerName;
	@FXML private Label tbBeerVolume;
	@FXML private Label tbBeerDesc;
	@FXML private Label tbTotalPrice;
	@FXML private ImageView imgBack;
	@FXML private ImageView imgBeerContainer;
	@FXML private ImageView imgSub;
	@FXML private ImageView imgAdd;
	@FXML private Button btnAddToCart;
	@FXML private TextField tfQuantity;
	
	private BuyBeer_Bean buyBeerBean;
	
	
	public void initialize() {
		buyBeerBean = new BuyBeer_Bean();
		buyBeerBean.loadSelectedProduct();
		buyBeerBean.setQuantity(1);
		
		tfQuantity.setText(String.valueOf(buyBeerBean.getQuantity()));
		tbBeerName.setText(buyBeerBean.getBeerName());
		
		String sep = "  -  ";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(buyBeerBean.getContainerType().toString());
		stringBuilder.append(sep);
		stringBuilder.append(buyBeerBean.getBeerColor().toString());
		stringBuilder.append(sep);
		stringBuilder.append(buyBeerBean.getBeerAlcohol() + "%");
		stringBuilder.append(sep);
		stringBuilder.append(buyBeerBean.getBeerFiltering().toString());
		stringBuilder.append(sep);
		stringBuilder.append(Volume.toText(buyBeerBean.getContainerVolume()) + " €");
		tbBeerVolume.setText(stringBuilder.toString());
		
		tbBeerDesc.setText(buyBeerBean.getBeerDescription());
		tbTotalPrice.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()) + " €");
		imgBeerContainer.setImage(BeerImageLoader.loadImage(buyBeerBean.getContainerType()));
	}
	
		
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}

	@FXML 
	public void onSubPressed() {
		int q = buyBeerBean.getQuantity() - 1;
		if(q < 1) {
			q = 1;
		}
		buyBeerBean.setQuantity(q);
		tfQuantity.setText(String.valueOf(q));
		setTotalPrice();
	}


	@FXML 
	public void onQuantityChange() {
		try {
			int q = Integer.parseUnsignedInt(tfQuantity.getText());
			buyBeerBean.setQuantity(q);
		} catch (NumberFormatException nfe) {
			tfQuantity.setText(String.valueOf(buyBeerBean.getQuantity()));
		}
		setTotalPrice();
	}


	@FXML 
	public void onAddPressed() {
		int q = buyBeerBean.getQuantity() + 1;
		buyBeerBean.setQuantity(q);
		tfQuantity.setText(String.valueOf(q));
		setTotalPrice();
	}
	
	private void setTotalPrice() {
		tbTotalPrice.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()) + " €");
	}
	
	@FXML 
	public void onAddToCartPressed() {
		try {
			buyBeerBean.addProductToCart();
		} catch (ProductNotFoundException pnfe) {
			Logger.getGlobal().log(Level.SEVERE, "Product not found");
		} catch (StorableIllegalQuantityException sile) {
			Logger.getGlobal().log(Level.SEVERE, "Storable illegal quantity");
		}
		
		onBackPressed();
	}
	
}
