package logic.boundary;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;
import logic.designclasses.PageLoader;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.bean.Home_Bean;
import logic.entity.bean.Product_Bean;

public class Home_Boundary {
	private static final String WINDOW_TITLE = "Home";
	private static final String FXML_FILEPATH = "/res/fxml/Home_View.fxml";
	private static final String PRODUCTCARD_FILEPATH = "/res/fxml/ProductCard_View.fxml";
	
	@FXML
	private Label tb_ale_quantity;
	@FXML
	private Label tb_lambic_quantity;
	@FXML
	private Label tb_lager_quantity;
	@FXML
	private Label tb_light_quantity;
	@FXML
	private Label tb_amber_quantity;
	@FXML
	private Label tb_ruby_quantity;
	@FXML
	private Label tb_dark_quantity;
	@FXML
	private Label tb_bottle_quantity;
	@FXML
	private Label tb_can_quantity;
	@FXML
	private Label tb_barrel_quantity;
	@FXML
	private Label tb_filtered_quantity;
	@FXML
	private Label tb_unfiltered_quantity;
	@FXML
	private Label tb_cart;
	@FXML
	private Label tb_login;
	
	@FXML
	private TextField tf_search;
	
	@FXML
	private Button btn_search;
	
	@FXML
	private CheckBox cb_ale;
	@FXML
	private CheckBox cb_lambic;
	@FXML
	private CheckBox cb_lager;
	@FXML
	private CheckBox cb_light;
	@FXML
	private CheckBox cb_amber;
	@FXML
	private CheckBox cb_ruby;
	@FXML
	private CheckBox cb_dark;
	@FXML
	private CheckBox cb_bottle;
	@FXML
	private CheckBox cb_can;
	@FXML
	private CheckBox cb_barrel;
	@FXML
	private CheckBox cb_filtered;
	@FXML
	private CheckBox cb_unfiltered;
	
	@FXML
	private VBox vb_menu;
	
	@FXML
	private HBox hb_check_order;
	@FXML
	private HBox hb_cart;
	@FXML
	private HBox hb_login;
	@FXML
	private HBox hb_my_profile;
	@FXML
	private HBox hb_my_orders;
	@FXML
	private HBox hb_logout;
	
	@FXML
	private TilePane tp_products;
	
	
	private Home_Bean bean;
	
		
	public static void start() throws Exception {
		PageLoader pageLoader = new PageLoader(FXML_FILEPATH);
		
		Stage primaryStage = StandaloneCustomerMain.getPrimaryStage();
		Scene scene = new Scene(pageLoader.getRootView());
		
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
	}
	
	public void initialize() {		
		bean = new Home_Bean();
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
			for (Product_Bean product : bean.showProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName)) {
				PageLoader pageLoader = new PageLoader(PRODUCTCARD_FILEPATH);
				ProductCard_Boundary product_view = (ProductCard_Boundary) pageLoader.getController();
				product_view.loadProduct(product);
				tp_products.getChildren().add(pageLoader.getRootView());
			}
		} catch (IOException ie) {
			// TODO: handle exception
		}
	}
		
	
	public void openMenu() {
		vb_menu.setDisable(false);
		vb_menu.setOpacity(1);
	}
	
	public void closeMenu() {
		vb_menu.setDisable(true);
		vb_menu.setOpacity(0);
	}
	
}
