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
import logic.bean.Customer_Bean;
import logic.designclasses.PageLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class Register_Boundary {
	@FXML private TextField tf_email;
	@FXML private PasswordField psw_password;
	@FXML private PasswordField psw_passwordConfirm;
	@FXML private TextField tf_firstname;
	@FXML private TextField tf_lastname;
	@FXML private TextField tf_address;
	@FXML private TextField tf_city;
	@FXML private TextField tf_country;
	@FXML private TextField tf_postalcode;
	@FXML private TextField tf_phoneNumber;
	@FXML private TextField tf_creditCardNumber;
	@FXML private CheckBox cb_agreement;
	@FXML private Label lbl_error;
	
	Customer_Bean customerBean = null;
	
	
	public void initialize() {
		customerBean = new Customer_Bean();
		lbl_error.setText("");
	}

	@FXML 
	public void onRegisterClicked() {
		lbl_error.setText("");
		customerBean.setEmail(tf_email.getText());
		customerBean.setPassword(psw_password.getText());
		customerBean.setFirstName(tf_firstname.getText());
		customerBean.setLastName(tf_lastname.getText());
		customerBean.setAddress(tf_address.getText());
		customerBean.setCity(tf_city.getText());
		customerBean.setCountry(tf_country.getText());
		customerBean.setPostalCode(tf_postalcode.getText());
		customerBean.setPhoneNumber(tf_phoneNumber.getText());
		customerBean.setCreditCard(tf_creditCardNumber.getText());
		
		if(cb_agreement.isSelected()) {
			if(psw_password.getText().equals(psw_passwordConfirm.getText())) {
				try {
					customerBean.register();
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
}
