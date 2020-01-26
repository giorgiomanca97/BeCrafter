package logic.boundary;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;
import logic.designclasses.PageLoader;

public class CheckoutSummary_Boundary {
	private static final String WINDOW_TITLE = "Checkout Summary";
	private static final String FXML_FILEPATH = "/res/fxml/Checkout_Summary_View.fxml";
	
	@FXML private Button btn_back_shopping;
	@FXML private Button btn_Confirm_Products;
	@FXML private Label tb_overall_cost;
	@FXML private HBox hbox_cart;
	
	public void initialize() {

	}
	
	
	public static void start() {
		try {
			PageLoader pageLoader = new PageLoader(FXML_FILEPATH);
			
			Stage primaryStage = StandaloneCustomerMain.getPrimaryStage();
			Scene scene = new Scene(pageLoader.getRootView());
			
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.setScene(scene);
		} catch (IOException ioe) {
			
		}
	}
}
