package logic.boundary;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.designclasses.BeerImageLoader;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import logic.entity.Volume;
import logic.entity.bean.Product_Bean;

public class ProductCard_Boundary {
	@FXML private ImageView iv_beer_icon;
	@FXML private Label tb_beer_name;
	@FXML private Label tb_type;
	@FXML private Label tb_color;
	@FXML private Label tb_alcohol;
	@FXML private Label tb_filtering;
	@FXML private Label tb_volume;
	@FXML private Label tb_price;
	
	private Product_Bean productBean;
	
	
	public void initialize() {
		
	}
	
	public void loadProduct(Product_Bean product) {
		this.productBean = product;
		
		tb_beer_name.setText(product.getBeerName());
		tb_type.setText(product.getBeerType().toString());
		tb_color.setText(product.getBeerColor().toString());
		tb_alcohol.setText(String.valueOf(product.getBeerAlcohol()) + "%");
		tb_filtering.setText(product.getBeerFiltering().toString());
		tb_volume.setText(Volume.toText(product.getContainerVolume()));
		tb_price.setText(Price.toText(product.getPrice()));
		iv_beer_icon.setImage(BeerImageLoader.loadImage(product.getContainerType()));
	}
	
	public void onMousePressed() {
		if(productBean != null) {
			try {
				productBean.selectProduct();
				PageLoader pageLoader = new PageLoader(PageLoader.Page.BUYBEER);
				pageLoader.showOnPrimaryStage();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}			
		}
	}
}
