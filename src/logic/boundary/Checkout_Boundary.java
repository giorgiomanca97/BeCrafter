package logic.boundary;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;
import logic.designclasses.PageLoader;

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
	
	
	public static void start(){
		PageLoader pageLoader;
		try {
			pageLoader = new PageLoader(FXML_FILEPATH);
			Stage primaryStage = StandaloneCustomerMain.getPrimaryStage();
			Scene scene = new Scene(pageLoader.getRootView());
			
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onBackPressed() {
		Home_Boundary.start();
	}
}
