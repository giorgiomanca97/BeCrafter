package logic.boundary;


import error.EmptyCartException;
import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.PaymentRefusedException;
import error.WrongFieldException;
import error.id.IdException;
import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.LoginException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.bean.CheckoutPayment_Bean;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class CheckoutPayment_Boundary {	
	@FXML private TextField tf_cp_email;
	@FXML private TextField tf_cp_firstname;
	@FXML private TextField tf_cp_lastname;
	@FXML private TextField tf_cp_address;
	@FXML private TextField tf_cp_city;
	@FXML private TextField tf_cp_country;
	@FXML private TextField tf_cp_postalcode;
	@FXML private TextField tf_cp_phoneNumber;
	@FXML private TextField tf_cp_creditcardNumber;
	@FXML private TextField tf_loginEmail;
	@FXML private PasswordField psw_password;
	@FXML private Button btn_login;
	
	@FXML private Label lbl_login;
	@FXML private Label lbl_error;
	@FXML private Label lbl_error2;
	@FXML private Label lbl_cp_infoCredit;
	
	private Checkout_Boundary checkoutBoundary = null;
	private CheckoutPayment_Bean checkoutPaymentBean = null;
	
	
	public void initialize() {
		checkoutPaymentBean = new CheckoutPayment_Bean();
		
		try {
			checkoutPaymentBean.loadLoggedCustomer();
			tf_loginEmail.setText(checkoutPaymentBean.getEmail());
			tf_loginEmail.setDisable(true);
			tf_cp_email.setText(checkoutPaymentBean.getEmail());
			tf_cp_email.setDisable(true);
			lbl_login.setText("Please insert the password to procede");
		} catch (LoginException e) {
			
		}
		
		lbl_cp_infoCredit.setOpacity(0f);
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
		checkoutPaymentBean.setEmail(tf_cp_email.getText());
		checkoutPaymentBean.setFirstName(tf_cp_firstname.getText());
		checkoutPaymentBean.setLastName(tf_cp_lastname.getText());
		checkoutPaymentBean.setAddress(tf_cp_address.getText());
		checkoutPaymentBean.setCity(tf_cp_city.getText());
		checkoutPaymentBean.setCountry(tf_cp_country.getText());
		checkoutPaymentBean.setPostalCode(tf_cp_postalcode.getText());
		checkoutPaymentBean.setPhoneNumber(tf_cp_phoneNumber.getText());
		checkoutPaymentBean.setCreditCard(tf_cp_creditcardNumber.getText());
		
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
		} catch (PaymentRefusedException pre) {
			lbl_error.setText("Payment refused. Please retry");
		} catch (IdException | EmptyCartException e) {
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
			
			tf_cp_email.setDisable(true);
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
			tf_cp_email.setText(checkoutPaymentBean.getEmail());
			tf_cp_firstname.setText(checkoutPaymentBean.getFirstName());
			tf_cp_lastname.setText(checkoutPaymentBean.getLastName());
			tf_cp_address.setText(checkoutPaymentBean.getAddress());
			tf_cp_city.setText(checkoutPaymentBean.getCity());
			tf_cp_country.setText(checkoutPaymentBean.getCountry());
			tf_cp_postalcode.setText(checkoutPaymentBean.getPostalCode());
			tf_cp_phoneNumber.setText(checkoutPaymentBean.getPhoneNumber());
			tf_cp_creditcardNumber.setText(checkoutPaymentBean.getCreditCard());
		}
	}

	@FXML 
	public void onInfoCreditEntered() {
		lbl_cp_infoCredit.setOpacity(1f);
	}

	@FXML 
	public void onInfoCreditExited() {
		lbl_cp_infoCredit.setOpacity(0f);
	}
}
