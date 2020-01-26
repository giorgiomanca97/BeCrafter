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

public class Checkout_Boundary {
	private static final String WINDOW_TITLE = "Checkout";
	private static final String FXML_FILEPATH = "/res/fxml/Checkout_View.fxml";
	
	@FXML
	private ImageView img_back;
	
	@FXML
	private Label tb_summary;
	@FXML
	private Label tb_payment;
	@FXML
	private Label tb_confirmation;
	
	@FXML
	private AnchorPane ap_checkout;
	
	
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
