package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CheckoutConfirmation_Boundary {
	@FXML private Label tbOrderCode;
	
	private Checkout_Boundary checkoutBoundary = null;
	
	
	public void setCheckoutBoundary(Checkout_Boundary checkoutBoundary) {
		this.checkoutBoundary = checkoutBoundary;
		tbOrderCode.setText(checkoutBoundary.getOrderId());
	}

	@FXML 
	public void onOkPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.onBackPressed();
		}
	}
}
