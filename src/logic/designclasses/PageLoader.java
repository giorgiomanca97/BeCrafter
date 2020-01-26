package logic.designclasses;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import logic.StandaloneCustomerMain;

public class PageLoader {
	private FXMLLoader loader;
	private Object rootViev;
	
	public PageLoader(String resource) throws IOException {
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
}
