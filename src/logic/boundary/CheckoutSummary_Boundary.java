package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.bean.BuyBeer_Bean;

public class CheckoutSummary_Boundary {	
	@FXML private VBox vbox_products;
	@FXML private Label tb_overallCost;
	
	private Checkout_Boundary checkoutBoundary = null;
	BuyBeer_Bean buyBeerBean = null;
	
	
	public void initialize() {
		
	}
	
	public void setCheckoutBoundary(Checkout_Boundary checkoutBoundary) {
		this.checkoutBoundary = checkoutBoundary;
		this.buyBeerBean = checkoutBoundary.getBuyBeerBean();
	}

	@FXML 
	public void goBackToShoppingPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.onBackPressed();
		}
	}

	@FXML 
	public void onConfirmProductsPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.openTab(Checkout_Boundary.Tab.PAYMENT);
		}
	}
}
