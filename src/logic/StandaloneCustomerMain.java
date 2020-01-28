package logic;


import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.designclasses.PageLoader;

public class StandaloneCustomerMain extends Application{
	private static Stage primaryStage = null; 
	
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private static void setPrimaryStage(Stage stage) {
		StandaloneCustomerMain.primaryStage = stage;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{	
		setPrimaryStage(primaryStage);
		
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
