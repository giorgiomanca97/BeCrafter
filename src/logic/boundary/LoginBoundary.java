package logic.boundary;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.LoginException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.bean.LoginBean;
import logic.designclasses.PageLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class LoginBoundary {
	@FXML private TextField tfEmail;
	@FXML private PasswordField pswPassword;
	@FXML private Label lblError;

	LoginBean loginBean = null;
	
	
	public void initialize() {
		loginBean = new LoginBean();
		lblError.setText("");
	}

	@FXML 
	public void onLoginClicked() {
		loginBean.setEmail(tfEmail.getText());
		loginBean.setPassword(pswPassword.getText());
		
		lblError.setText("");
		try {
			loginBean.login();
			onBackPressed();
		} catch (LoginException | IllegalCharacterException e) {
			lblError.setText("Email and Password do not match");
		} catch (EmptyFieldException efe) {
			lblError.setText("Please fill all the fields");
		}
	}

	@FXML 
	public void onRegisterPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.REGISTER);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}

	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
}
