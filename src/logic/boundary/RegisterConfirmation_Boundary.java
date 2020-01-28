package logic.boundary;

import java.io.IOException;

import javafx.fxml.FXML;
import logic.designclasses.PageLoader;

public class RegisterConfirmation_Boundary {
	
	public void initialize() {

	}
	
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@FXML
	public void onOkClicked() {
		onBackPressed();
	}
}
