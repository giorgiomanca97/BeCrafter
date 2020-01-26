package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.StandaloneCustomerMain;
import logic.entity.beans.Product_Bean;

public class ProductCard_View {
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
	
	
	private Home_View home_View;
	private Product_Bean bean;
	
	
	public void initialize() {

	}
	
	public void setHomeView(Home_View home_View) {
		this.home_View = home_View;
	}
	
	public void loadProduct(Product_Bean bean) {
		this.bean = bean;
		
		tb_beer_name.setText(bean.getBeerName());
		tb_type.setText(bean.getBeerType().toString());
		tb_color.setText(bean.getBeerColor().toString());
		tb_alcohol.setText(String.valueOf(bean.getBeerAlcohol()));
		tb_filtering.setText(bean.getBeerFiltering().toString());
		tb_volume.setText(String.valueOf(bean.getContainerVolume()));
		tb_price.setText(String.valueOf(bean.getPrice()));
		
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
		iv_beer_icon.setImage(image);
	}
}
