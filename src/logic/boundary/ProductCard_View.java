package logic.boundary;


import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.Main;

public class ProductCard_View {
	@FXML
	private ImageView imageView;
	
	@FXML
	private WebView webView;
	
	
	public void initialize() {

	}
	
	
	public static void start() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL url = Main.class.getResource("/res/fxml/ProductCard_View.fxml");
		
		loader.setLocation(url);
		AnchorPane root = (AnchorPane) loader.load();
		
		Stage primaryStage = Main.getPrimaryStage();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("BUY");
		primaryStage.setScene(scene);
	}
}
