package logic.boundary;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import logic.bean.Home_Bean;
import logic.designclasses.PageLoader;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;

public class Home_Boundary {
	@FXML private TextField tfSearch;
	@FXML private Button btnSearch;
	@FXML private CheckBox cbAle;
	@FXML private CheckBox cbLambic;
	@FXML private CheckBox cbLager;
	@FXML private CheckBox cbLight;
	@FXML private CheckBox cbAmber;
	@FXML private CheckBox cbRuby;
	@FXML private CheckBox cbDark;
	@FXML private CheckBox cbBottle;
	@FXML private CheckBox cbCan;
	@FXML private CheckBox cbBarrel;
	@FXML private CheckBox cbFiltered;
	@FXML private CheckBox cbUnfiltered;
	@FXML private TilePane tpProducts;
	@FXML private Label lblCartCounter;
	@FXML private Label lblLogin;
	@FXML private VBox vbMenu;
	
	private Home_Bean homeBean;
	
	
	public void initialize() {
		homeBean = new Home_Bean();
		closeMenu();
		setLoggedCustomer();
		updateProducts();
	}
	
	public void updateProducts() {
		List<BeerType> beerTypes = new ArrayList<>();
		List<BeerColor> beerColors = new ArrayList<>();
		List<ContainerType> containerTypes = new ArrayList<>();
		List<BeerFiltering> beerFilterings = new ArrayList<>();
		String searchName = tfSearch.getText();
		
		if(cbAle.isSelected()) {
			beerTypes.add(BeerType.ALE);
		}
		if(cbLambic.isSelected()) {
			beerTypes.add(BeerType.LAMBIC);
		}
		if(cbLager.isSelected()) {
			beerTypes.add(BeerType.LAGER);
		}
		if(cbLight.isSelected()) {
			beerColors.add(BeerColor.LIGHT);
		}
		if(cbAmber.isSelected()) {
			beerColors.add(BeerColor.AMBER);
		}
		if(cbRuby.isSelected()) {
			beerColors.add(BeerColor.RUBY);
		}
		if(cbDark.isSelected()) {
			beerColors.add(BeerColor.DARK);
		}
		if(cbBottle.isSelected()) {
			containerTypes.add(ContainerType.BOTTLE);
		}
		if(cbCan.isSelected()) {
			containerTypes.add(ContainerType.CAN);
		}
		if(cbBarrel.isSelected()) {
			containerTypes.add(ContainerType.BARREL);
		}
		if(cbFiltered.isSelected()) {
			beerFilterings.add(BeerFiltering.FILTERED);
		}
		if(cbUnfiltered.isSelected()) {
			beerFilterings.add(BeerFiltering.UNFILTERED);
		}
		
		homeBean.displayProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
		tpProducts.getChildren().clear();
		try {
			for (int i = 0; i < homeBean.countDisplayedProducts(); i++) {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_PRODUCTCARD);
				HomeProductCard_Boundary productView = (HomeProductCard_Boundary) pageLoader.getController();
				productView.loadProduct(i);
				tpProducts.getChildren().add(pageLoader.getRootView());
			}
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
	
	public void setLoggedCustomer() {
		String email = homeBean.loggedCustomer();
		
		if(email != null) {
			lblLogin.setText(email);
		}
	}
		
	@FXML
	public void openMenu() {
		if(homeBean.loggedCustomer() != null) {
			vbMenu.setDisable(false);
			vbMenu.setOpacity(1);
		}	
	}
	
	@FXML
	public void closeMenu() {
		vbMenu.setDisable(true);
		vbMenu.setOpacity(0);
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
		if(homeBean.loggedCustomer() == null) {
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
		String email = homeBean.loggedCustomer();
		
		if(email != null) {
			homeBean.logoutCustomer(email);
			closeMenu();
			lblLogin.setText("Login");
		}
	}

	@FXML
	public void onCheckOrderPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.CHECKORDER);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
}
