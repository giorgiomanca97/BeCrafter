package logic;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.designclasses.PageLoader;


public class StandaloneCustomerMain extends Application{
	private static final String WINDOW_TITLE = "BeCrater";
	private static final String FXML_FILEPATH = "/res/fxml/Splash.fxml";
	
	private static Stage primaryStage = null; 
	
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private static void setPrimaryStage(Stage stage) {
		StandaloneCustomerMain.primaryStage = stage;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{	
		PageLoader pageLoader = new PageLoader(FXML_FILEPATH);
		Scene scene = new Scene(pageLoader.getRootView());
		
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
