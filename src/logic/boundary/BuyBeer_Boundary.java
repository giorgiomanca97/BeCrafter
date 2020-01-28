package logic.boundary;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logic.bean.Product_Bean;
import logic.designclasses.BeerImageLoader;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import logic.entity.Volume;

public class BuyBeer_Boundary {		
	@FXML private Label tb_beer_name;
	@FXML private Label tb_beer_cc;
	@FXML private Label tb_beer_desc;
	@FXML private Label tb_total_price;
	@FXML private ImageView img_back;
	@FXML private ImageView img_beer_container;
	@FXML private ImageView img_sub;
	@FXML private ImageView img_add;
	@FXML private Button btn_add_to_cart;
	@FXML private TextField tf_quantity;
	
	private Product_Bean buyBeerBean;
	
	
	public void initialize() {
		buyBeerBean = new Product_Bean();	
		buyBeerBean.loadSelectedProduct();
		buyBeerBean.setQuantity(1);
		
		tf_quantity.setText(String.valueOf(buyBeerBean.getQuantity()));
		tb_beer_name.setText(buyBeerBean.getBeerName());
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(buyBeerBean.getContainerType().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(buyBeerBean.getBeerColor().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(buyBeerBean.getBeerAlcohol() + "%");
		stringBuilder.append("  -  ");
		stringBuilder.append(buyBeerBean.getBeerFiltering().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(Volume.toText(buyBeerBean.getContainerVolume()));
		tb_beer_cc.setText(stringBuilder.toString());
		
		tb_beer_desc.setText(buyBeerBean.getBeerDescription());
		tb_total_price.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()));
		img_beer_container.setImage(BeerImageLoader.loadImage(buyBeerBean.getContainerType()));
	}
	
		
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}

	@FXML 
	public void onSubPressed() {
		int q = buyBeerBean.getQuantity() - 1;
		if(q < 1) {
			q = 1;
		}
		buyBeerBean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
		tb_total_price.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()));
	}


	@FXML 
	public void onQuantityChange() {
		try {
			int q = Integer.parseUnsignedInt(tf_quantity.getText());
			buyBeerBean.setQuantity(q);
		} catch (NumberFormatException nfe) {
			tf_quantity.setText(String.valueOf(buyBeerBean.getQuantity()));
		}
		tb_total_price.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()));
	}


	@FXML 
	public void onAddPressed() {
		int q = buyBeerBean.getQuantity() + 1;
		buyBeerBean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
		tb_total_price.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()));
	}
	
	@FXML 
	public void onAddToCartPressed() {
		buyBeerBean.addProductToCart();
		onBackPressed();
	}
	
}
