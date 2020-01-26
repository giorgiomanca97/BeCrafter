package logic.boundary;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;

public class Checkout_Payment_Boundary {
	private static final String WINDOW_TITLE = "Checkout Payment";
	private static final String FXML_FILEPATH = "/res/fxml/Checkout_Payment_View.fxml";
	
	@FXML
	private TextField tf_email;
	@FXML
	private TextField tf_firstname;
	@FXML
	private TextField tf_lastname;
	@FXML
	private TextField tf_address;
	@FXML
	private TextField tf_city;
	@FXML
	private TextField tf_country;
	@FXML
	private TextField tf_postalcode;
	@FXML
	private TextField tf_phone_number;
	@FXML
	private TextField tf_creditcard_number;
	
	@FXML
	private Button btn_back_summary;
	@FXML
	private Button btn_confirm_purchase;
	
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
