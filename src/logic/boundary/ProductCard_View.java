package logic.boundary;


import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;

public class ProductCard_View {
	private static final String WINDOW_TITLE = "Product Card";
	private static final String FXML_FILEPATH = "/res/fxml/ProductCard_View.fxml";
	
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
	
	
	public void initialize() {

	}
	
	
	public static void start() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL url = StandaloneCustomerMain.class.getResource(FXML_FILEPATH);
		
		loader.setLocation(url);
		AnchorPane root = (AnchorPane) loader.load();
		
		Stage primaryStage = StandaloneCustomerMain.getPrimaryStage();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
	}
}
