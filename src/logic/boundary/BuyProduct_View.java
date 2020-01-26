package logic.boundary;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;

public class BuyProduct_View {
	private static final String WINDOW_TITLE = "Buy Product";
	private static final String FXML_FILEPATH = "/res/fxml/BuyProduct_View.fxml";
	
	@FXML
	private Label tb_beer_name;
	@FXML
	private Label tb_beer_cc;
	@FXML
	private Label tb_beer_desc;
	@FXML
	private Label tb_selected_num;
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
