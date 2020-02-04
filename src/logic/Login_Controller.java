package logic;


import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import logic.dao.Registered_dao;
import logic.designclasses.CheckHelper;
import logic.entity.BillingInfo;
import logic.entity.Registered;

public class Login_Controller {
	private static Login_Controller instance = null;

	private Registered loggedCustomer = null;
	
	
	private Login_Controller() {
		
	}
	
	public static synchronized Login_Controller getInstance() {
		if(instance == null) {
			instance = new Login_Controller();
		}
		
		return instance;
	}
	
	public boolean isLogged(String email) {
		return (loggedCustomer != null && loggedCustomer.getEmail().equals(email));
	}
	
	public Registered getLoggedCustomer() {
		return this.loggedCustomer;
	}

	public void login(String email, String password) throws InexistentEmailException, WrongPasswordException{
		if(loggedCustomer != null && !loggedCustomer.getEmail().equals(email)) {
			logout(loggedCustomer.getEmail());
		}
		
		if(loggedCustomer == null) {
			Registered registered = Registered_dao.getRegisteredByEmail(email);
			
			if(registered != null && registered.getEmail().equals(email)) {
				if(registered.getPassword().equals(password)) {
					loggedCustomer = registered;
				} else {
					throw new WrongPasswordException();
				}
			} else {
				throw new InexistentEmailException();
			}
		} else if(!loggedCustomer.getPassword().equals(password)) {
			throw new WrongPasswordException();
		}
	}
	
	
	public void logout(String email) {
		if(loggedCustomer != null && loggedCustomer.getEmail().equals(email)) {
			loggedCustomer = null;
			BuyBeer_Controller.getInstance().initCart();
		}
	}
	
	public void register(String email, String password, BillingInfo billingInfo) throws InvalidEmailException, UsedEmailException, InvalidPasswordException {
		if(!CheckHelper.isValidEmail(email)) {
			throw new InvalidEmailException();
		}
		
		if(!CheckHelper.isValidPassword(password)) {
			throw new InvalidPasswordException();
		}

		if(Registered_dao.getRegisteredByEmail(email) != null) {
			throw new UsedEmailException();
		} 
		
		Registered registered = new Registered(email, password, billingInfo);
		Registered_dao.insertRegistered(registered);
	}
}
