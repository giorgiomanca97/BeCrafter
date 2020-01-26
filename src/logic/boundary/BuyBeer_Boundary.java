package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.StandaloneCustomerMain;
import logic.entity.Price;
import logic.entity.Volume;
import logic.entity.beans.BuyBeer_Bean;

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
	
	
	private BuyBeer_Bean bean;
	
	
	public void initialize() {
		bean = new BuyBeer_Bean();
		bean.setQuantity(1);
		
		tf_quantity.setText(String.valueOf(bean.getQuantity()));
		
		bean.loadSelectedProduct();
		
		tb_beer_name.setText(bean.getBeerName());
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(bean.getContainerType().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(bean.getBeerColor().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(bean.getBeerAlcohol() + "%");
		stringBuilder.append("  -  ");
		stringBuilder.append(bean.getBeerFiltering().toString());
		stringBuilder.append("  -  ");
		stringBuilder.append(Volume.toText(bean.getContainerVolume()));
		tb_beer_cc.setText(stringBuilder.toString());
		
		tb_beer_desc.setText(bean.getBeerDescription());
		
		tb_total_price.setText(Price.toText(bean.getPrice()));
		
		Image image = null;
		switch(bean.getContainerType()) {
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
		int q = bean.getQuantity() + 1;
		bean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
	}
	
	
	public void decreaseQuantity() {
		int q = bean.getQuantity() - 1;
		if(q < 1) {
			q = 1;
		}
		bean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
	}
	
	public void updateQuantity() {
		try {
			int q = Integer.parseUnsignedInt(tf_quantity.getText());
			bean.setQuantity(q);
		} catch (NumberFormatException nfe) {
			tf_quantity.setText(String.valueOf(bean.getQuantity()));
		} 
	}
	
	
	public void addProductToCart() {
		bean.addProductToCart();
	}
}
