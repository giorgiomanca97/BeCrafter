package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.InexistentEmailException;
import error.login.WrongPasswordException;
import logic.Login_Controller;
import logic.bean.Home_Bean;
import logic.bean.Login_Bean;

public class TestLogin_Controller {
	private static Login_Controller loginController;
	
	@BeforeClass
	public static void setLoginController() {
		loginController = Login_Controller.getInstance();
	}

	// Giorgio Manca 0239067
	@Test
	public void testLogin() {
		String message = "";
		String email = "test@provider.org";
		String password = "TestPass1";
		
		Login_Bean loginBean = new Login_Bean();
		loginBean.setEmail(email);
		loginBean.setPassword(password);
		
		try {
			loginBean.login();
		} catch (InexistentEmailException e) {
			message = "Inexistent Email";
		} catch (WrongPasswordException e) {
			message = "Wrong Password";
		} catch (EmptyFieldException e) {
			message = "Empty Field";
		} catch (IllegalCharacterException e) {
			message = "Illegal Character";
		}
		
		assertEquals(message, true, loginController.isLogged(email));
	}
	
	// Giorgio Manca 0239067
	@After
	@Test
	public void testLogout() {
		String message = "";
		String email = "test@provider.org";
		
		Home_Bean hBean = new Home_Bean();
		hBean.logoutCustomer(email);
		
		assertEquals(message, false, loginController.isLogged(email));
	}
	
}
