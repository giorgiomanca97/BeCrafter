package logic.boundary;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;

public class CheckoutSummary_Boundary {
	private static final String WINDOW_TITLE = "Checkout Summary";
	private static final String FXML_FILEPATH = "/res/fxml/Checkout_Summary_View.fxml";
	
	@FXML
	private Button btn_back_shopping;
	@FXML
	private Button btn_Confirm_Products;
	
	@FXML
	private Label tb_overall_cost;
	
	@FXML
	private ListView<String> lv_cart;
	
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
