package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CheckoutConfirmationBoundary {
	@FXML private Label tbOrderCode;
	
	private CheckoutBoundary checkoutBoundary = null;
	
	
	public void setCheckoutBoundary(CheckoutBoundary checkoutBoundary) {
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
