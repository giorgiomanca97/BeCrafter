package logic.designclasses;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;

public class PageLoader {
	private FXMLLoader loader;
	private Object rootViev;
	private String title;
	private Stage stage;
	
	
	public PageLoader(String resource, String title) throws IOException {
		init(resource, title);
	}
	
	
	public PageLoader(Page page) throws IOException {
		init(page.getResource(), page.getTitle());
	}
	
	private void init(String resource, String title) throws IOException {
		this.loader = new FXMLLoader();
		URL url = StandaloneCustomerMain.class.getResource(resource);
		this.loader.setLocation(url);
		this.rootViev = loader.load();
		this.title = title;
	}
	
	
	public FXMLLoader getLoader() {
		return this.loader;
	}
	
	public Parent getRootView() {
		return (Parent) this.rootViev;
	}
	
	public Object getController() {
		return loader.getController();
	}
	
	public void showOnPrimaryStage() {
		this.stage = null;
		
		Scene scene = new Scene(this.getRootView());
		StandaloneCustomerMain.getPrimaryStage().setTitle(this.title);
		StandaloneCustomerMain.getPrimaryStage().setScene(scene);
		StandaloneCustomerMain.getPrimaryStage().setResizable(false);
		StandaloneCustomerMain.getPrimaryStage().setMaximized(false);
		StandaloneCustomerMain.getPrimaryStage().setFullScreen(false);
		StandaloneCustomerMain.getPrimaryStage().show();
	}
	
	public void showOnOwnStage(boolean resizable) {
		this.stage = new Stage();
		
		Scene scene = new Scene(this.getRootView());
		this.stage.setTitle(this.title);
		this.stage.setScene(scene);
		this.stage.setResizable(resizable);
		this.stage.show();
	}
	
	public void closeOwnStage() {
		if(this.stage != null) {
			this.stage.close();
			this.stage = null;
		}
	}
	
	
	public enum Page{
		BUYBEER("/res/fxml/BuyBeer_View.fxml", "Buy Beer"),
		CHECKOUT("/res/fxml/Checkout_View.fxml", "Checkout"),
		CHECKOUT_CONFIRMATION("/res/fxml/CheckoutConfirmation_View.fxml", "Checkout Confirmation"),
		CHECKOUT_PAYMENT("/res/fxml/CheckoutPayment_View.fxml", "Checkout Payment"),
		CHECKOUT_SUMMARY("/res/fxml/CheckoutSummary_View.fxml", "Checkout Summary"),
		CHECKOUT_SUMMARY_ELEMENT("/res/fxml/CheckoutSummaryElement_View.fxml", "Checkout Summary Element"),
		HOME("/res/fxml/Home_View.fxml", "Home"),
		LOGIN("/res/fxml/Login_View.fxml", "Login"),
		PRODUCTCARD("/res/fxml/ProductCard_View.fxml", "Product"),
		REGISTER("/res/fxml/Register_View.fxml", "Register"),
		REGISTER_CONFIRMATION("/res/fxml/RegisterConfirmation_View.fxml", "Register Confirmation"),
		CHECKORDER("/res/fxml/CheckOrder_View.fxml", "Check Order");
		
		private String resource;
		private String title;
		
		private Page(String resource, String title) {
			this.resource = resource;
			this.title = title;
		}
		
		public String getResource() {
			return this.resource;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
}
