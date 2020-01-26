package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.BuyBeer_Controller;
import logic.StandaloneCustomerMain;
import logic.designclasses.PageLoader;
import logic.entity.ContainerType;
import logic.entity.Price;
import logic.entity.Volume;
import logic.entity.beans.Product_Bean;

public class BuyBeer_Boundary {	
	private static final String IMAGE_BOTTLE_FILEPATH = "/res/icons/beer_bottle.png";
	private static final String IMAGE_CAN_FILEPATH = "/res/icons/beer_can.png";
	private static final String IMAGE_BARREL_FILEPATH = "/res/icons/beer_barrel.png";
	
	@FXML
	private Label tb_beer_name;
	@FXML
	private Label tb_beer_cc;
	@FXML
	private Label tb_beer_desc;
	@FXML
	private Label tb_total_price;
	
	@FXML
	private ImageView img_back;
	@FXML
	private ImageView img_beer_container;
	@FXML
	private ImageView img_sub;
	@FXML
	private ImageView img_add;
	
	@FXML
	private Button btn_add_to_cart;
	
	@FXML
	private TextField tf_quantity;
	
	
	private Product_Bean product;
	private int quantity;
	
	
	public void initialize() {
		quantity = 1;
		tf_quantity.setText(String.valueOf(quantity));
	}
	
	
	
	
	
	public void loadProduct(Product_Bean product) {
		this.product = product;
		
		tb_beer_name.setText(product.getBeerName());
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(product.getContainerType().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(product.getBeerColor().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(product.getBeerAlcohol() + "%");
		stringBuilder.append("  -  ");
		stringBuilder.append(product.getBeerFiltering().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(Volume.toText(product.getContainerVolume()));
		tb_beer_cc.setText(stringBuilder.toString());
		
		tb_beer_desc.setText(product.getBeerDescription());
		
		tb_total_price.setText(Price.toText(product.getPrice()));
		
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
		img_beer_container.setImage(image);
	}
	
	
	public void increaseQuantity() {
		quantity++;
		tf_quantity.setText(String.valueOf(quantity));
	}
	
	
	public void decreaseQuantity() {
		quantity--;
		if(quantity < 1) {
			quantity = 1;
		}
		tf_quantity.setText(String.valueOf(quantity));
	}
	
	public void updateQuantity() {
		try {
			quantity = Integer.parseUnsignedInt(tf_quantity.getText());
		} catch (NumberFormatException nfe) {
			tf_quantity.setText(String.valueOf(quantity));
		} 
	}
	
	
	public void addProductToCart() {
		String beerId = product.getBeerId();
		ContainerType containerType = product.getContainerType();
		Volume containerVolume = new Volume(product.getContainerVolume());
		
		BuyBeer_Controller.getInstance().addProductToCart(beerId, containerType, containerVolume, quantity);
	}
}
