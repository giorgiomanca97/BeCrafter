package logic.bean;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.InexistentEmailException;
import error.login.WrongPasswordException;
import logic.Login_Controller;

public class Login_Bean {
	private String lEmail;
	private String lPassword;	
	
	
	public Login_Bean() {
		lEmail = "";
		lPassword = "";
	}


	// Getters and Setters
	public String getEmail() {
		return lEmail;
	}

	public void setEmail(String email) {
		this.lEmail = email;
	}


	public String getPassword() {
		return lPassword;
	}
	
	public void setPassword(String password) {
		this.lPassword = password;
	}
	// ==============================
	
	
	public void login() throws InexistentEmailException, WrongPasswordException, EmptyFieldException, IllegalCharacterException {
		if(lEmail.length() == 0 || lPassword.length() == 0) {
			throw new EmptyFieldException();
		}
		if(lEmail.contains("'") || lPassword.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		Login_Controller.getInstance().login(lEmail, lPassword);
	}
}
