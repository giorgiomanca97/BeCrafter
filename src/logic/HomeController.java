package logic;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController {
	private static HomeController instance = null;
	
	private static final String WINDOW_TITLE = "Home";
	private static final String FXML_FILEPATH = "/res/fxml/Home_View.fxml";
	
	
	private HomeController() {
		
	}
	
	synchronized public static HomeController getInstance() {
		if(instance == null) {
			instance = new HomeController();
		}
		
		return instance;
	}
	
	
	public void init() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL url = StandaloneCustomerMain.class.getResource(FXML_FILEPATH);
		loader.setLocation(url);
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root);
		
		Stage primaryStage = StandaloneCustomerMain.getPrimaryStage();
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
}
