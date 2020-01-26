package logic.boundary;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.BuyBeer_Controller;
import logic.StandaloneCustomerMain;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import logic.entity.Volume;
import logic.entity.beans.BuyBeer_Bean;
import logic.entity.beans.Product_Bean;

public class ProductCard_Boundary {
	private static final String FXML_FILEPATH = "/res/fxml/BuyBeer_View.fxml";
	private static final String IMAGE_BOTTLE_FILEPATH = "/res/icons/beer_bottle.png";
	private static final String IMAGE_CAN_FILEPATH = "/res/icons/beer_can.png";
	private static final String IMAGE_BARREL_FILEPATH = "/res/icons/beer_barrel.png";
	
	@FXML
	private ImageView iv_beer_icon;
	
	@FXML
	private Label tb_beer_name;
	@FXML
	private Label tb_type;
	@FXML
	private Label tb_color;
	@FXML
	private Label tb_alcohol;
	@FXML
	private Label tb_filtering;
	@FXML
	private Label tb_volume;
	@FXML
	private Label tb_price;
	
	
	private Product_Bean product;
	
	
	public void initialize() {

	}
	
	public void loadProduct(Product_Bean product) {
		this.product = product;
		
		tb_beer_name.setText(product.getBeerName());
		tb_type.setText(product.getBeerType().toString());
		tb_color.setText(product.getBeerColor().toString());
		tb_alcohol.setText(String.valueOf(product.getBeerAlcohol()) + "%");
		tb_filtering.setText(product.getBeerFiltering().toString());
		tb_volume.setText(Volume.toText(product.getContainerVolume()));
		tb_price.setText(Price.toText(product.getPrice()));
		
		Image image = null;
		switch(product.getContainerType()) {
			case BOTTLE:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream(IMAGE_BOTTLE_FILEPATH));
				break;
			case CAN:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream(IMAGE_CAN_FILEPATH));
				break;
			case BARREL:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream(IMAGE_BARREL_FILEPATH));
				break;
		}
		iv_beer_icon.setImage(image);
	}
	
	public void onMousePressed() {
		if(product != null) {
			try {
				PageLoader pageLoader = new PageLoader(FXML_FILEPATH);
				BuyBeer_Boundary buyBeer_Boundary = (BuyBeer_Boundary) pageLoader.getController();
				buyBeer_Boundary.loadProduct(product);
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
			}
		}
	}
}
