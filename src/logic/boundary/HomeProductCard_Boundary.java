package logic.boundary;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.bean.Home_Bean;
import logic.designclasses.BeerImageLoader;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import logic.entity.Volume;

public class HomeProductCard_Boundary {
	@FXML private ImageView iv_beer_icon;
	@FXML private Label tb_beer_name;
	@FXML private Label tb_type;
	@FXML private Label tb_color;
	@FXML private Label tb_alcohol;
	@FXML private Label tb_filtering;
	@FXML private Label tb_volume;
	@FXML private Label tb_price;
	
	private Home_Bean homeBean;
	
	
	public void initialize() {
		
	}
	
	public void loadProduct(int index) {
		homeBean = new Home_Bean();
		homeBean.loadDisplayedProductAt(index);
		
		tb_beer_name.setText(homeBean.getBeerName());
		tb_type.setText(homeBean.getBeerType().toString());
		tb_color.setText(homeBean.getBeerColor().toString());
		tb_alcohol.setText(String.valueOf(homeBean.getBeerAlcohol()) + "%");
		tb_filtering.setText(homeBean.getBeerFiltering().toString());
		tb_volume.setText(Volume.toText(homeBean.getContainerVolume()));
		tb_price.setText(Price.toText(homeBean.getPrice()) + " €");
		iv_beer_icon.setImage(BeerImageLoader.loadImage(homeBean.getContainerType()));
	}
	
	public void onMousePressed() {
		if(homeBean != null) {
			try {
				homeBean.selectForSaleProduct();
				PageLoader pageLoader = new PageLoader(PageLoader.Page.BUYBEER);
				pageLoader.showOnPrimaryStage();
			} catch (IOException ioe) {
				Logger.getGlobal().log(Level.SEVERE, "Page loading error");
			}			
		}
	}
}
