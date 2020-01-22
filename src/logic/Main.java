package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.boundary.ProductCard_View;

public class Main extends Application{
	private static Stage primaryStage; 
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/res/fxml/Splash.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		setPrimaryStage(primaryStage);
		
		//Thread.sleep(500);
		ProductCard_View.start();
	}
	
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	
	private static void setPrimaryStage(Stage stage) {
		Main.primaryStage = stage;
	}
}
