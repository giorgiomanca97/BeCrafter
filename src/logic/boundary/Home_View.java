package logic.boundary;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StandaloneCustomerMain;

public class Home_View {
	private static final String WINDOW_TITLE = "Home";
	private static final String FXML_FILEPATH = "/res/fxml/Home_View.fxml";
	
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
	
	
	public void initialize() {

	}
	
	
	public static void start() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL url = StandaloneCustomerMain.class.getResource(FXML_FILEPATH);
		
		loader.setLocation(url);
		AnchorPane root = (AnchorPane) loader.load();
		
		Stage primaryStage = StandaloneCustomerMain.getPrimaryStage();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
	}
}
