package logic.boundary;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.LoginException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.bean.Login_Bean;
import logic.designclasses.PageLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class Login_Boundary {
	@FXML private TextField tf_email;
	@FXML private PasswordField psw_password;
	@FXML private Label lbl_error;

	Login_Bean loginBean = null;
	
	
	public void initialize() {
		loginBean = new Login_Bean();
		lbl_error.setText("");
	}

	@FXML 
	public void onLoginClicked() {
		loginBean.setEmail(tf_email.getText());
		loginBean.setPassword(psw_password.getText());
		
		lbl_error.setText("");
		try {
			loginBean.login();
			onBackPressed();
		} catch (LoginException le) {
			lbl_error.setText("Email and Password do not match");
		} catch (EmptyFieldException efe) {
			lbl_error.setText("Please fill all the fields");
		} catch (IllegalCharacterException ice) {
			lbl_error.setText("Email and Password do not match");
		} 
	}

	@FXML 
	public void onRegisterPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.REGISTER);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}

	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
}
