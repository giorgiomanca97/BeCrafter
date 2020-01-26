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
		PAGE("resource", "title");
		
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
