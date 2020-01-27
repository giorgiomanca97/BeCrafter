package logic.boundary;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.designclasses.PageLoader;

public class Checkout_Boundary {	
	@FXML private ImageView img_back;
	@FXML private Label tb_summary;
	@FXML private Label tb_payment;
	@FXML private Label tb_confirmation;
	@FXML private AnchorPane ap_checkout;
	
	
	public void initialize() {

	}
	
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
