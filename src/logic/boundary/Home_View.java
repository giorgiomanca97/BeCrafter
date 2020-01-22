package logic.boundary;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Main;

public class Home_View {
	private static final String WINDOW_TITLE = "Home";
	private static final String FXML_FILEPATH = "/res/fxml/Home_View.fxml";
	
	// FXML Views
	
	// ----------
	
	public void initialize() {

	}
	
	
	public static void start() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL url = Main.class.getResource(FXML_FILEPATH);
		
		loader.setLocation(url);
		AnchorPane root = (AnchorPane) loader.load();
		
		Stage primaryStage = Main.getPrimaryStage();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
	}
}
