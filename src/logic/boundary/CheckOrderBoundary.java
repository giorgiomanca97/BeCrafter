package logic.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.OrderNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.bean.CheckOrderBean;
import logic.designclasses.PageLoader;
import logic.entity.Price;

public class CheckOrderBoundary {
	@FXML private TextField tfOrder;
	@FXML private Label lblOrder;
	@FXML private Label lblDate;
	@FXML private Label lblPrice;
	@FXML private Label lblEmail;
	@FXML private Label lblShippingCode;
	@FXML private Label lblShippingCompany;
	@FXML private Label lblWarning;
	@FXML private VBox vbProducts;
	
	private CheckOrderBean checkOrderBean;
	
	
	public void initialize() {
		checkOrderBean = new CheckOrderBean();
		
		lblOrder.setText("");
		lblDate.setText("");
		lblPrice.setText("");
		lblEmail.setText("");
		lblShippingCode.setText("");
		lblShippingCompany.setText("");
		lblWarning.setText("");
	}
	
	@FXML
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	@FXML
	public void onSearchPressed() {
		lblWarning.setText("");

		try {
			checkOrderBean.searchOrder(tfOrder.getText());
			
			lblOrder.setText(checkOrderBean.getOrderId());
			lblDate.setText(checkOrderBean.getDate());
			lblPrice.setText(Price.toText(checkOrderBean.getPrice()) + " €");
			lblEmail.setText(checkOrderBean.getEmail());
			lblShippingCode.setText(checkOrderBean.getShippingCode());
			lblShippingCompany.setText(checkOrderBean.getShippingCompany());
		} catch (OrderNotFoundException onfe) {
			lblWarning.setText("no order found with this order code");
			lblOrder.setText("");
			lblDate.setText("");
			lblPrice.setText("");
			lblEmail.setText("");
			lblShippingCode.setText("");
			lblShippingCompany.setText("");
		}
	}
	
}
