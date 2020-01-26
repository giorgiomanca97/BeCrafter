package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.designclasses.PageLoader;
import logic.entity.beans.Product_Bean;

public class BuyBeer_Boundary {
	private static final String WINDOW_TITLE = "Buy Product";
	private static final String FXML_FILEPATH = "/res/fxml/BuyProduct_View.fxml";
	
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
	
	
	public void initialize() {

	}
	
	
	public static void start() throws Exception {
		PageLoader pageLoader = new PageLoader(FXML_FILEPATH);
		
		Scene scene = new Scene(pageLoader.getRootView());
		
		Stage stage = new Stage();
		stage.setTitle(WINDOW_TITLE);
		stage.setScene(scene);
	}
	
	public void loadProduct(Product_Bean product) {
		//tb_beer_name;
	}
	
	public void addProductToCart() {
		
	}
}
