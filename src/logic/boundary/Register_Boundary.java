package logic.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.WrongFieldException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.UsedEmailException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.bean.Register_Bean;
import logic.designclasses.PageLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class Register_Boundary {
	@FXML private TextField tf_r_email;
	@FXML private PasswordField psw_password;
	@FXML private PasswordField psw_passwordConfirm;
	@FXML private TextField tf_r_firstname;
	@FXML private TextField tf_r_lastname;
	@FXML private TextField tf_r_address;
	@FXML private TextField tf_r_city;
	@FXML private TextField tf_r_country;
	@FXML private TextField tf_r_postalcode;
	@FXML private TextField tf_r_phoneNumber;
	@FXML private TextField tf_r_creditCardNumber;
	@FXML private CheckBox cb_agreement;
	@FXML private Label lbl_error;
	@FXML private Label lbl_r_infoPassword;
	@FXML private Label lbl_r_infoCredit;
	
	Register_Bean registerBean = null;
	
	
	public void initialize() {
		registerBean = new Register_Bean();
		
		lbl_r_infoPassword.setOpacity(0f);
		lbl_r_infoCredit.setOpacity(0f);
		lbl_error.setText("");
	}

	@FXML 
	public void onRegisterClicked() {
		lbl_error.setText("");
		registerBean.setEmail(tf_r_email.getText());
		registerBean.setPassword(psw_password.getText());
		registerBean.setFirstName(tf_r_firstname.getText());
		registerBean.setLastName(tf_r_lastname.getText());
		registerBean.setAddress(tf_r_address.getText());
		registerBean.setCity(tf_r_city.getText());
		registerBean.setCountry(tf_r_country.getText());
		registerBean.setPostalCode(tf_r_postalcode.getText());
		registerBean.setPhoneNumber(tf_r_phoneNumber.getText());
		registerBean.setCreditCard(tf_r_creditCardNumber.getText());
		
		if(cb_agreement.isSelected()) {
			if(psw_password.getText().equals(psw_passwordConfirm.getText())) {
				try {
					registerBean.register();
					PageLoader pageLoader = new PageLoader(PageLoader.Page.REGISTER_CONFIRMATION);
					pageLoader.showOnPrimaryStage();
				} catch (InvalidEmailException iee) {
					lbl_error.setText("The email is not a valid mail");
				} catch (UsedEmailException uee) {
					lbl_error.setText("The email is already registered");
				} catch (InvalidPasswordException ipe) {
					lbl_error.setText("The password do not match the specifics");
				} catch (EmptyFieldException efe) {
					lbl_error.setText("Please fill all the fields");
				} catch (IllegalCharacterException ice) {
					lbl_error.setText("Please remove the ' character from the fields");
				} catch (WrongFieldException wfe) {
					lbl_error.setText("Some fields are not correct");
				} catch (IOException ioe) {
					Logger.getGlobal().log(Level.SEVERE, "Page loading error");
				}
			} else {
				lbl_error.setText("The password fields do not match");
			}
		} else {
			lbl_error.setText("Please accept the agreement to register");
		}
		
	}
	
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}

	@FXML
	public void onInfoPasswordEntered() {
		lbl_r_infoPassword.setOpacity(1f);
	}

	@FXML 
	public void onInfoPasswordExited() {
		lbl_r_infoPassword.setOpacity(0f);
	}

	@FXML 
	public void onInfoCreditEntered() {
		lbl_r_infoCredit.setOpacity(1f);
	}
	
	@FXML 
	public void onInfoCreditExited() {
		lbl_r_infoCredit.setOpacity(0f);
	}
}
