package logic.boundary;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.bean.HomeBean;
import logic.designclasses.BeerImageLoader;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import logic.entity.Volume;

public class HomeProductCardBoundary {
	@FXML private ImageView ivBeerIcon;
	@FXML private Label tbBeerName;
	@FXML private Label tbType;
	@FXML private Label tbColor;
	@FXML private Label tbAlcohol;
	@FXML private Label tbFiltering;
	@FXML private Label tbVolume;
	@FXML private Label tbPrice;
	
	private HomeBean homeBean;
	
	
	public void loadProduct(int index) {
		homeBean = new HomeBean();
		homeBean.loadDisplayedProductAt(index);
		
		tbBeerName.setText(homeBean.getBeerName());
		tbType.setText(homeBean.getBeerType().toString());
		tbColor.setText(homeBean.getBeerColor().toString());
		tbAlcohol.setText(String.valueOf(homeBean.getBeerAlcohol()) + "%");
		tbFiltering.setText(homeBean.getBeerFiltering().toString());
		tbVolume.setText(Volume.toText(homeBean.getContainerVolume()));
		tbPrice.setText(Price.toText(homeBean.getPrice()) + " €");
		ivBeerIcon.setImage(BeerImageLoader.loadImage(homeBean.getContainerType()));
	}
	
	@FXML
	public void onMousePressed() {
		if(homeBean != null) {
			try {
				homeBean.selectForSaleProduct();
				PageLoader pageLoader = new PageLoader(PageLoader.Page.BUYBEER);
				pageLoader.showOnPrimaryStage();
			} catch (ProductNotFoundException e) {
				Logger.getGlobal().log(Level.SEVERE, "Product not found");
			} catch (IOException ioe) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}			
		}
	}

}
