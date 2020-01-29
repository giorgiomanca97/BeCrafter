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
	
	private CheckOrder_Bean checkOrderBean;
	
	public void initialize() {
		
		checkOrderBean = new CheckOrder_Bean();
		
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
		checkOrderBean.setOrderId(tf_order.getText());
		try {
			checkOrderBean.searchOrder();
			
			lbl_order.setText(checkOrderBean.getOrderId());
			lbl_date.setText(checkOrderBean.getDate());
			lbl_price.setText(checkOrderBean.getPrice());
			lbl_email.setText(checkOrderBean.getEmail());
			lbl_shippingCode.setText(checkOrderBean.getShippingCode());
			lbl_shippingCompany.setText(checkOrderBean.getShippingCompany());
		} catch (OrderNotFoundException onfe) {
			lbl_warning.setText("no order found with this order code");
			lbl_order.setText("");
			lbl_date.setText("");
			lbl_price.setText("");
			lbl_email.setText("");
			lbl_shippingCode.setText("");
			lbl_shippingCompany.setText("");
		}
	}
	
}
