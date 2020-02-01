package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import error.login.InexistentEmailException;
import error.login.WrongPasswordException;
import logic.Login_Controller;

public class TestLogin_Controller {
	private static Login_Controller loginController;
	
	@BeforeClass
	public static void setLoginController() {
		loginController = Login_Controller.getInstance();
	}

	@Test
	public void testLogin() {
		String message = "";
		String email = "test@provider.org";
		String password = "TestPass1";
		
		try {
			loginController.login(email, password);
		} catch (InexistentEmailException e) {
			message = "Inexistent Email";
		} catch (WrongPasswordException e) {
			message = "Wrong Password";
		}
		
		assertEquals(message, true, loginController.isLogged(email));
	}
	
	@After
	@Test
	public void testLogout() {
		String message = "";
		String email = "test@provider.org";
		
		loginController.logout(email);
		
		assertEquals(message, false, loginController.isLogged(email));
	}

}
