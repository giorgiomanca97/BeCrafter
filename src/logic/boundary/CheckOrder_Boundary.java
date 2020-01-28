package logic.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.OrderNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.bean.CheckOrder_Bean;
import logic.designclasses.PageLoader;

public class CheckOrder_Boundary {

	@FXML private TextField tf_order;
	@FXML private Label lbl_order;
	@FXML private Label lbl_date;
	@FXML private Label lbl_price;
	@FXML private Label lbl_email;
	@FXML private Label lbl_shippingCode;
	@FXML private Label lbl_shippingCompany;
	@FXML private Label lbl_warning;
	@FXML private VBox vb_products;
	
	private CheckOrder_Bean checkOrder_Bean;
	
	public void initialize() {
		
		checkOrder_Bean = new CheckOrder_Bean();
		
		lbl_order.setText("");
		lbl_date.setText("");
		lbl_price.setText("");
		lbl_email.setText("");
		lbl_shippingCode.setText("");
		lbl_shippingCompany.setText("");
		lbl_warning.setText("");
	}
	
	@FXML
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
	@FXML
	public void onSearchPressed() {
		lbl_warning.setText("");
		checkOrder_Bean.setOrderId(tf_order.getText());
		try {
			checkOrder_Bean.searchOrder();
			
			lbl_order.setText(checkOrder_Bean.getOrderId());
			lbl_date.setText(checkOrder_Bean.getDate());
			lbl_price.setText(checkOrder_Bean.getPrice());
			lbl_email.setText(checkOrder_Bean.getEmail());
			lbl_shippingCode.setText(checkOrder_Bean.getShippingCode());
			lbl_shippingCompany.setText(checkOrder_Bean.getShippingCompany());
		} catch (OrderNotFoundException onfe) {
			lbl_warning.setText("No order found with this order code");
			lbl_order.setText("");
			lbl_date.setText("");
			lbl_price.setText("");
			lbl_email.setText("");
			lbl_shippingCode.setText("");
			lbl_shippingCompany.setText("");
		}
	}
	
}
