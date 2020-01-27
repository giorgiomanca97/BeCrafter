package logic.boundary;


import java.io.IOException;

import error.EmptyFieldException;
import error.login.LoginException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.bean.Customer_Bean;
import logic.designclasses.PageLoader;
import javafx.scene.control.Label;

public class Login_Boundary {
	@FXML private TextField tf_email;
	@FXML private TextField tf_password;
	@FXML private Label lbl_error;

	Customer_Bean customerBean = null;
	
	
	public void initialize() {
		customerBean = new Customer_Bean();
		lbl_error.setText("");
	}

	@FXML 
	public void onLoginClicked() {
		customerBean.setEmail(tf_email.getText());
		customerBean.setPassword(tf_password.getText());
		
		lbl_error.setText("");
		try {
			customerBean.login();
		} catch (LoginException le) {
			lbl_error.setText("Email and Password do not match");
		} catch (EmptyFieldException efe) {
			lbl_error.setText("Please fill all the fields");
		}
	}

	@FXML 
	public void onRegisterPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.REGISTER);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
}
