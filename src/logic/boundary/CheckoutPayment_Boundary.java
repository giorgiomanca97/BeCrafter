package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.bean.BuyBeer_Bean;

public class CheckoutPayment_Boundary {	
	@FXML private TextField tf_email;
	@FXML private TextField tf_firstname;
	@FXML private TextField tf_lastname;
	@FXML private TextField tf_address;
	@FXML private TextField tf_city;
	@FXML private TextField tf_country;
	@FXML private TextField tf_postalcode;
	@FXML private TextField tf_phone_number;
	@FXML private TextField tf_creditcard_number;
	
	
	private Checkout_Boundary checkoutBoundary = null;
	BuyBeer_Bean buyBeerBean = null;
	
	
	public void initialize() {
		
	}
	
	public void setCheckoutBoundary(Checkout_Boundary checkoutBoundary) {
		this.checkoutBoundary = checkoutBoundary;
		this.buyBeerBean = checkoutBoundary.getBuyBeerBean();
	}

	@FXML 
	public void onBackToSummaryPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.openTab(Checkout_Boundary.Tab.SUMMARY);
		}
	}

	@FXML
	public void onConfirmPurchasePressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.openTab(Checkout_Boundary.Tab.CONFIRMATION);
		}
	}
}
