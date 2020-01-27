package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.bean.BuyBeer_Bean;

public class CheckoutConfirmation_Boundary {
	@FXML private Label tb_order_code;
	
	private Checkout_Boundary checkoutBoundary = null;
	BuyBeer_Bean buyBeerBean = null;
	
	
	public void initialize() {
		
	}
	
	public void setCheckoutBoundary(Checkout_Boundary checkoutBoundary) {
		this.checkoutBoundary = checkoutBoundary;
		tb_order_code.setText(checkoutBoundary.getOrderId());
	}

	@FXML 
	public void onOkPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.onBackPressed();
		}
	}
}
