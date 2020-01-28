package logic.boundary;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.InvalidEmailException;
import error.login.UsedEmailException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.bean.Customer_Bean;

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
	@FXML private Label lbl_login;
	@FXML private TextField tf_loginEmail;
	@FXML private TextField tf_loginPassword;
	
	private Checkout_Boundary checkoutBoundary = null;
	private Customer_Bean checkoutPaymentBean = null;
	
	
	public void initialize() {
		checkoutPaymentBean = new Customer_Bean();
		lbl_error.setText("");
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
		lbl_error.setText("");
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
				checkoutBoundary.setOrderId(orderId);
				checkoutBoundary.openTab(Checkout_Boundary.Tab.CONFIRMATION);
			}
		} catch(InvalidEmailException iee) {
			lbl_error.setText("Wrong email format");
		} catch(UsedEmailException uee) {
			lbl_error.setText("This email is already registered");
		} catch(EmptyFieldException uee) {
			lbl_error.setText("Please fill all the empty fields");
		} catch (IllegalCharacterException ice) {
			lbl_error.setText("Please remove the ' character from the fields");
		} catch (Exception e) {
			lbl_error.setText("Unexpected Error. Please retry");
		}
	}

	@FXML 
	public void onLoginPressed() {
		
	}
}
