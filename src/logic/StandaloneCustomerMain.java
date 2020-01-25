package logic;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class StandaloneCustomerMain extends Application{
	private static StandaloneCustomerMain instance = null;
	
	private static final String WINDOW_TITLE = "BeCrater";
	private static final String FXML_FILEPATH = "/res/fxml/Splash.fxml";
	
	private static Stage primaryStage = null; 
	
	
	private StandaloneCustomerMain() {
		
	}
	
	synchronized public static StandaloneCustomerMain getInstance() {
		if(instance == null) {
			instance = new StandaloneCustomerMain();
		}
		
		return instance;
	}
	
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private static void setPrimaryStage(Stage stage) {
		StandaloneCustomerMain.primaryStage = stage;
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
		
		HomeController homeController = HomeController.getInstance();
		homeController.init();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
