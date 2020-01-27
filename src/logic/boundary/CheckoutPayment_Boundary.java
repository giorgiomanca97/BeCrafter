package logic.boundary;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.bean.CheckoutPayment_Bean;

public class CheckoutPayment_Boundary {	
	@FXML private TextField tf_email;
	@FXML private TextField tf_firstname;
	@FXML private TextField tf_lastname;
	@FXML private TextField tf_address;
	@FXML private TextField tf_city;
	@FXML private TextField tf_country;
	@FXML private TextField tf_postalcode;
	@FXML private TextField tf_phoneNumber;
	@FXML private TextField tf_creditcardNumber;
	@FXML private Label lbl_error;
	
	
	private Checkout_Boundary checkoutBoundary = null;
	CheckoutPayment_Bean checkoutPaymentBean = null;
	
	
	public void initialize() {
		checkoutPaymentBean = new CheckoutPayment_Bean();
		lbl_error.setOpacity(0);
	}
	
	public void setCheckoutBoundary(Checkout_Boundary checkoutBoundary) {
		this.checkoutBoundary = checkoutBoundary;
	}
	
	@FXML 
	public void onBackToSummaryPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.openTab(Checkout_Boundary.Tab.SUMMARY);
		}
	}

	@FXML
	public void onConfirmPurchasePressed() {
		confirmPurchase();
	}
	
	public void confirmPurchase() {
		lbl_error.setOpacity(0);
		checkoutPaymentBean.setEmail(tf_email.getText());
		checkoutPaymentBean.setFirstName(tf_firstname.getText());
		checkoutPaymentBean.setLastName(tf_lastname.getText());
		checkoutPaymentBean.setAddress(tf_address.getText());
		checkoutPaymentBean.setCity(tf_city.getText());
		checkoutPaymentBean.setCountry(tf_country.getText());
		checkoutPaymentBean.setPostalCode(tf_postalcode.getText());
		checkoutPaymentBean.setPhoneNumber(tf_phoneNumber.getText());
		checkoutPaymentBean.setCreditCard(tf_creditcardNumber.getText());
		
		try {
			String orderId = checkoutPaymentBean.confirmPurchase();
			
			if(checkoutBoundary != null) {
				checkoutBoundary.openTab(Checkout_Boundary.Tab.CONFIRMATION);
				checkoutBoundary.setOrderId(orderId);
			}
		} catch (Exception e) {
			lbl_error.setOpacity(1);
		}
	}
}
