package logic.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}

	@FXML
	public void onOkClicked() {
		onBackPressed();
	}
}
