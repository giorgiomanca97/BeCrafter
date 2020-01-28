package logic.boundary;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import logic.Login_Controller;
import logic.bean.Home_Bean;
import logic.bean.Product_Bean;
import logic.designclasses.PageLoader;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Registered;

public class Home_Boundary {	
	@FXML private Label tb_ale_quantity;
	@FXML private Label tb_lambic_quantity;
	@FXML private Label tb_lager_quantity;
	@FXML private Label tb_light_quantity;
	@FXML private Label tb_amber_quantity;
	@FXML private Label tb_ruby_quantity;
	@FXML private Label tb_dark_quantity;
	@FXML private Label tb_bottle_quantity;
	@FXML private Label tb_can_quantity;
	@FXML private Label tb_barrel_quantity;
	@FXML private Label tb_filtered_quantity;
	@FXML private Label tb_unfiltered_quantity;
	@FXML private TextField tf_search;
	@FXML private Button btn_search;
	@FXML private CheckBox cb_ale;
	@FXML private CheckBox cb_lambic;
	@FXML private CheckBox cb_lager;
	@FXML private CheckBox cb_light;
	@FXML private CheckBox cb_amber;
	@FXML private CheckBox cb_ruby;
	@FXML private CheckBox cb_dark;
	@FXML private CheckBox cb_bottle;
	@FXML private CheckBox cb_can;
	@FXML private CheckBox cb_barrel;
	@FXML private CheckBox cb_filtered;
	@FXML private CheckBox cb_unfiltered;
	@FXML private TilePane tp_products;
	@FXML private Label lbl_cartCounter;
	@FXML private Label lbl_login;
	@FXML private VBox vb_menu;
	
	private Home_Bean homeBean;
	
	
		
	public void initialize() {		
		homeBean = new Home_Bean();
		closeMenu();
		setLoggedCustomer();
		updateProducts();
	}
	
	public void updateProducts() {
		ArrayList<BeerType> beerTypes = new ArrayList<BeerType>();
		ArrayList<BeerColor> beerColors = new ArrayList<BeerColor>();
		ArrayList<ContainerType> containerTypes = new ArrayList<ContainerType>();
		ArrayList<BeerFiltering> beerFilterings = new ArrayList<BeerFiltering>();
		String searchName = tf_search.getText();
		
		if(cb_ale.isSelected()) {
			beerTypes.add(BeerType.ALE);
		}
		if(cb_lambic.isSelected()) {
			beerTypes.add(BeerType.LAMBIC);
		}
		if(cb_lager.isSelected()) {
			beerTypes.add(BeerType.LAGER);
		}
		if(cb_light.isSelected()) {
			beerColors.add(BeerColor.LIGHT);
		}
		if(cb_amber.isSelected()) {
			beerColors.add(BeerColor.AMBER);
		}
		if(cb_ruby.isSelected()) {
			beerColors.add(BeerColor.RUBY);
		}
		if(cb_dark.isSelected()) {
			beerColors.add(BeerColor.DARK);
		}
		if(cb_bottle.isSelected()) {
			containerTypes.add(ContainerType.BOTTLE);
		}
		if(cb_can.isSelected()) {
			containerTypes.add(ContainerType.CAN);
		}
		if(cb_barrel.isSelected()) {
			containerTypes.add(ContainerType.BARREL);
		}
		if(cb_filtered.isSelected()) {
			beerFilterings.add(BeerFiltering.FILTERED);
		}
		if(cb_unfiltered.isSelected()) {
			beerFilterings.add(BeerFiltering.UNFILTERED);
		}
				
		tp_products.getChildren().clear();
		try {
			for (Product_Bean product : homeBean.showProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName)) {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.PRODUCTCARD);
				ProductCard_Boundary product_view = (ProductCard_Boundary) pageLoader.getController();
				product_view.loadProduct(product);
				tp_products.getChildren().add(pageLoader.getRootView());
			}
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
	
	public void setLoggedCustomer() {
		Registered registered = Login_Controller.getInstance().getLoggedCustomer();
		
		if(registered != null) {
			lbl_login.setText(registered.getEmail());
		}
	}
		
	@FXML
	public void openMenu() {
		if(Login_Controller.getInstance().getLoggedCustomer() != null) {
			vb_menu.setDisable(false);
			vb_menu.setOpacity(1);
		}	
	}
	
	@FXML
	public void closeMenu() {
		vb_menu.setDisable(true);
		vb_menu.setOpacity(0);
	}

	@FXML 
	public void onCartPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.CHECKOUT);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}

	@FXML 
	public void onLoginPressed() {
		if(Login_Controller.getInstance().getLoggedCustomer() == null) {
			try {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN);
				pageLoader.showOnPrimaryStage();
			} catch (IOException ioe) {
				Logger.getGlobal().log(Level.SEVERE, "Page loading error");
			}
		}
	}

	@FXML 
	public void onLogoutPressed() {
		Registered registered = Login_Controller.getInstance().getLoggedCustomer();
		
		if(registered != null) {
			Login_Controller.getInstance().logout(registered.getEmail());
			lbl_login.setText("Login");
		}
	}

	@FXML
	public void onCheckOrderPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.CHECKOUT);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
}
