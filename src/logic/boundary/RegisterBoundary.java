package logic.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.WrongFieldException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.PasswordMatchingException;
import error.login.UsedEmailException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.bean.RegisterBean;
import logic.designclasses.PageLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class RegisterBoundary {
	@FXML private TextField tfrEmail;
	@FXML private PasswordField pswPassword;
	@FXML private PasswordField pswPasswordConfirm;
	@FXML private TextField tfrFirstname;
	@FXML private TextField tfrLastname;
	@FXML private TextField tfrAddress;
	@FXML private TextField tfrCity;
	@FXML private TextField tfrCountry;
	@FXML private TextField tfrPostalcode;
	@FXML private TextField tfrPhoneNumber;
	@FXML private TextField tfrCreditCardNumber;
	@FXML private CheckBox cbAgreement;
	@FXML private Label lblError;
	@FXML private Label lblrInfoPassword;
	@FXML private Label lblrInfoCredit;
	
	RegisterBean registerBean = null;
	
	
	public void initialize() {
		registerBean = new RegisterBean();
		
		lblrInfoPassword.setOpacity(0f);
		lblrInfoCredit.setOpacity(0f);
		lblError.setText("");
	}

	@FXML 
	public void onRegisterClicked() {
		lblError.setText("");
		registerBean.setEmail(tfrEmail.getText());
		registerBean.setPassword(pswPassword.getText());
		registerBean.setConfirmPassword(pswPasswordConfirm.getText());
		registerBean.setFirstName(tfrFirstname.getText());
		registerBean.setLastName(tfrLastname.getText());
		registerBean.setAddress(tfrAddress.getText());
		registerBean.setCity(tfrCity.getText());
		registerBean.setCountry(tfrCountry.getText());
		registerBean.setPostalCode(tfrPostalcode.getText());
		registerBean.setPhoneNumber(tfrPhoneNumber.getText());
		registerBean.setCreditCard(tfrCreditCardNumber.getText());
		
		if(cbAgreement.isSelected()) {
			register();
		} else {
			lblError.setText("Please accept the agreement to register");
		}
		
	}
	
	private void register() {
		try {
			registerBean.register();
			PageLoader pageLoader = new PageLoader(PageLoader.Page.REGISTER_CONFIRMATION);
			pageLoader.showOnPrimaryStage();
		} catch (InvalidEmailException iee) {
			lblError.setText("The email is not a valid mail");
		} catch (UsedEmailException uee) {
			lblError.setText("The email is already registered");
		} catch (InvalidPasswordException ipe) {
			lblError.setText("The password do not match the specifics");
		} catch (EmptyFieldException efe) {
			lblError.setText("Please fill all the fields");
		} catch (IllegalCharacterException ice) {
			lblError.setText("Please remove the ' character from the fields");
		} catch (WrongFieldException wfe) {
			lblError.setText("Some fields are not correct");
		} catch (PasswordMatchingException psm) {
			lblError.setText("The password fields do not match");
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}

	@FXML
	public void onInfoPasswordEntered() {
		lblrInfoPassword.setOpacity(1f);
	}

	@FXML 
	public void onInfoPasswordExited() {
		lblrInfoPassword.setOpacity(0f);
	}

	@FXML 
	public void onInfoCreditEntered() {
		lblrInfoCredit.setOpacity(1f);
	}
	
	@FXML 
	public void onInfoCreditExited() {
		lblrInfoCredit.setOpacity(0f);
	}
}
