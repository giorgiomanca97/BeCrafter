package logic;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.boundary.Home_View;
import logic.entity.Storehouse;
import logic.entity.dao.Storehouse_dao;


public class StandaloneCustomerMain extends Application{
	private static final String WINDOW_TITLE = "Splash";
	private static final String FXML_FILEPATH = "/res/fxml/Home_View.fxml";
	
	private static Stage primaryStage; 
	private Storehouse storehouse;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{	
		FXMLLoader loader = new FXMLLoader();
		URL url = StandaloneCustomerMain.class.getResource(FXML_FILEPATH);
		loader.setLocation(url);
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		setPrimaryStage(primaryStage);
		
		storehouse = Storehouse_dao.getStorehouse();

		//System.out.println(storehouse);
		Home_View.start();
	}
	
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	
	private static void setPrimaryStage(Stage stage) {
		StandaloneCustomerMain.primaryStage = stage;
	}
}