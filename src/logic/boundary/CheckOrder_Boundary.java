package logic.boundary;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CheckOrder_Boundary {

	@FXML private TextField tf_order;
	@FXML private Label lbl_order;
	@FXML private Label lbl_date;
	@FXML private Label lbl_price;
	@FXML private Label lbl_email;
	@FXML private Label lbl_shippingCode;
	@FXML private Label lbl_shippingCompany;
	@FXML private VBox vb_products;
	
	
	@FXML
	public void onBackPressed() {
		
	}
	
	@FXML
	public void onSearchPressed() {
		
	}
	
}
