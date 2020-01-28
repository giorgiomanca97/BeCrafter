package logic.boundary;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.WrongFieldException;
import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.LoginException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.bean.Customer_Bean;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

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
	@FXML private PasswordField psw_password;
	@FXML private Label lbl_error2;
	@FXML private Button btn_login;
	
	private Checkout_Boundary checkoutBoundary = null;
	private Customer_Bean checkoutPaymentBean = null;
	
	
	public void initialize() {
		checkoutPaymentBean = new Customer_Bean();
		
		try {
			checkoutPaymentBean.loadLoggedCustomer();
			tf_loginEmail.setText(checkoutPaymentBean.getEmail());
			tf_loginEmail.setDisable(true);
			tf_email.setText(checkoutPaymentBean.getEmail());
			tf_email.setDisable(true);
			lbl_login.setText("Please insert the password to procede");
		} catch (LoginException e) {
			
		}
		
		lbl_error.setText("");
		lbl_error2.setText("");
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
		} catch (WrongFieldException wfe) {
			lbl_error.setText("Some fields are not correct");
		} catch (Exception e) {
			lbl_error.setText("Unexpected Error. Please retry");
		}
	}
	
	
	@FXML 
	public void onLoginPressed() {
		checkoutPaymentBean.setEmail(tf_loginEmail.getText());
		checkoutPaymentBean.setPassword(psw_password.getText());
		lbl_error2.setText("");
		try {
			checkoutPaymentBean.login();
			checkoutPaymentBean.loadLoggedCustomer();
			
			lbl_error.setText("");
			tf_loginEmail.setDisable(true);
			psw_password.setDisable(true);
			btn_login.setDisable(true);
			
			autoFill();
			
			tf_email.setDisable(true);
			lbl_login.setText("Logged as " + checkoutPaymentBean.getEmail());
			
		} catch (InexistentEmailException | WrongPasswordException | IllegalCharacterException e) {
			lbl_error2.setText("Email and Password do not match");
		} catch (EmptyFieldException efe) {
			lbl_error2.setText("Please fill all the fields");
		} catch (LoginException le) {
			lbl_error2.setText("Something unexpected happen. Please retry");
		}
	}
	
	private void autoFill() {
		if(checkoutPaymentBean != null) {
			tf_email.setText(checkoutPaymentBean.getEmail());
			tf_firstname.setText(checkoutPaymentBean.getFirstName());
			tf_lastname.setText(checkoutPaymentBean.getLastName());
			tf_address.setText(checkoutPaymentBean.getAddress());
			tf_city.setText(checkoutPaymentBean.getCity());
			tf_country.setText(checkoutPaymentBean.getCountry());
			tf_postalcode.setText(checkoutPaymentBean.getPostalCode());
			tf_phoneNumber.setText(checkoutPaymentBean.getPhoneNumber());
			tf_creditcardNumber.setText(checkoutPaymentBean.getCreditCard());
		}
	}
}
