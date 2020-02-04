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
	@FXML private TextField tfcpEmail;
	@FXML private TextField tfcpFirstname;
	@FXML private TextField tfcpLastname;
	@FXML private TextField tfcpAddress;
	@FXML private TextField tfcpCity;
	@FXML private TextField tfcpCountry;
	@FXML private TextField tfcpPostalCode;
	@FXML private TextField tfcpPhoneNumber;
	@FXML private TextField tfcpCreditCardNumber;
	@FXML private TextField tfLoginEmail;
	@FXML private PasswordField pswPassword;
	@FXML private Button btnLogin;
	@FXML private Label lblLogin;
	@FXML private Label lblError;
	@FXML private Label lblError2;
	@FXML private Label lblcpInfoCredit;
	
	private Checkout_Boundary checkoutBoundary = null;
	private CheckoutPayment_Bean checkoutPaymentBean = null;
	
	
	public void initialize() {
		checkoutPaymentBean = new CheckoutPayment_Bean();
		
		String email = checkoutPaymentBean.loggedCustomer();
		if(email != null) {
			tfLoginEmail.setText(email);
			tfLoginEmail.setDisable(true);
			tfcpEmail.setText(email);
			tfcpEmail.setDisable(true);
			lblLogin.setText("Please insert the password to autofill");
		}
		
		lblcpInfoCredit.setOpacity(0f);
		lblError.setText("");
		lblError2.setText("");
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
		lblError.setText("");
		checkoutPaymentBean.setEmail(tfcpEmail.getText());
		checkoutPaymentBean.setFirstName(tfcpFirstname.getText());
		checkoutPaymentBean.setLastName(tfcpLastname.getText());
		checkoutPaymentBean.setAddress(tfcpAddress.getText());
		checkoutPaymentBean.setCity(tfcpCity.getText());
		checkoutPaymentBean.setCountry(tfcpCountry.getText());
		checkoutPaymentBean.setPostalCode(tfcpPostalCode.getText());
		checkoutPaymentBean.setPhoneNumber(tfcpPhoneNumber.getText());
		checkoutPaymentBean.setCreditCard(tfcpCreditCardNumber.getText());
		
		try {
			String orderId = checkoutPaymentBean.confirmPurchase();
			
			if(checkoutBoundary != null) {
				checkoutBoundary.setOrderId(orderId);
				checkoutBoundary.openTab(Checkout_Boundary.Tab.CONFIRMATION);
			}
		} catch(InvalidEmailException iee) {
			lblError.setText("Wrong email format");
		} catch(UsedEmailException uee) {
			lblError.setText("This email is already registered");
		} catch(EmptyFieldException uee) {
			lblError.setText("Please fill all the empty fields");
		} catch (IllegalCharacterException ice) {
			lblError.setText("Please remove the ' character from the fields");
		} catch (WrongFieldException wfe) {
			lblError.setText("Some fields are not correct");
		} catch (PaymentRefusedException pre) {
			lblError.setText("Payment refused. Please retry");
		} catch (IdException | EmptyCartException e) {
			lblError.setText("Unexpected Error. Please retry");
		}
	}
	
	
	@FXML 
	public void onLoginPressed() {
		checkoutPaymentBean.setEmail(tfLoginEmail.getText());
		checkoutPaymentBean.setPassword(pswPassword.getText());
		lblError2.setText("");
		try {
			checkoutPaymentBean.login();
			checkoutPaymentBean.loadLoggedCustomer();
			
			lblError.setText("");
			tfLoginEmail.setDisable(true);
			pswPassword.setDisable(true);
			btnLogin.setDisable(true);
			
			autoFill();
			
			tfcpEmail.setDisable(true);
			lblLogin.setText("Logged as " + checkoutPaymentBean.getEmail());
			
		} catch (InexistentEmailException | WrongPasswordException | IllegalCharacterException e) {
			lblError2.setText("Email and Password do not match");
		} catch (EmptyFieldException efe) {
			lblError2.setText("Please fill all the fields");
		} catch (LoginException le) {
			lblError2.setText("Something unexpected happen. Please retry");
		}
	}
	
	private void autoFill() {
		if(checkoutPaymentBean != null) {
			tfcpEmail.setText(checkoutPaymentBean.getEmail());
			tfcpFirstname.setText(checkoutPaymentBean.getFirstName());
			tfcpLastname.setText(checkoutPaymentBean.getLastName());
			tfcpAddress.setText(checkoutPaymentBean.getAddress());
			tfcpCity.setText(checkoutPaymentBean.getCity());
			tfcpCountry.setText(checkoutPaymentBean.getCountry());
			tfcpPostalCode.setText(checkoutPaymentBean.getPostalCode());
			tfcpPhoneNumber.setText(checkoutPaymentBean.getPhoneNumber());
			tfcpCreditCardNumber.setText(checkoutPaymentBean.getCreditCard());
		}
	}

	@FXML 
	public void onInfoCreditEntered() {
		lblcpInfoCredit.setOpacity(1f);
	}

	@FXML 
	public void onInfoCreditExited() {
		lblcpInfoCredit.setOpacity(0f);
	}
}
