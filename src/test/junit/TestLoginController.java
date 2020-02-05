package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.InexistentEmailException;
import error.login.WrongPasswordException;
import logic.LoginController;
import logic.bean.HomeBean;
import logic.bean.LoginBean;

public class TestLoginController {
	private static LoginController loginController;
	
	@BeforeClass
	public static void setLoginController() {
		loginController = LoginController.getInstance();
	}

	// Giorgio Manca 0239067
	@Test
	public void testLogin() {
		String message = "";
		String email = "test@provider.org";
		String pass = "TestPass1";
		
		LoginBean loginBean = new LoginBean();
		loginBean.setEmail(email);
		loginBean.setPassword(pass);
		
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
		
		HomeBean hBean = new HomeBean();
		hBean.logoutCustomer(email);
		
		assertEquals(message, false, loginController.isLogged(email));
	}
	
}
