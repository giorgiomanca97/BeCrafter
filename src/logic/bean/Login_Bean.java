package logic.bean;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.InexistentEmailException;
import error.login.WrongPasswordException;
import logic.Login_Controller;

public class Login_Bean {
	private String l_email;
	private String l_password;	
	
	
	public Login_Bean() {
		
	}


	// Getters and Setters
	public String getEmail() {
		return l_email;
	}

	public void setEmail(String email) {
		this.l_email = email;
	}


	public String getPassword() {
		return l_password;
	}
	
	public void setPassword(String password) {
		this.l_password = password;
	}
	// ==============================
	
	
	public void login() throws InexistentEmailException, WrongPasswordException, EmptyFieldException, IllegalCharacterException {
		if(l_email.length() == 0 || l_password.length() == 0) {
			throw new EmptyFieldException();
		}
		if(l_email.contains("'") || l_password.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		Login_Controller.getInstance().login(l_email, l_password);
	}
}
