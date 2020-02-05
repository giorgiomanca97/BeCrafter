package logic;


import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import logic.dao.RegisteredDao;
import logic.designclasses.CheckHelper;
import logic.entity.BillingInfo;
import logic.entity.Registered;

public class LoginController {
	private static LoginController instance = null;

	private Registered loggedCustomer = null;
	
	
	private LoginController() {
		
	}
	
	public static synchronized LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
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
			Registered registered = RegisteredDao.getRegisteredByEmail(email);
			
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
			BuyBeerController.getInstance().initCart();
		}
	}
	
	public void register(String email, String password, BillingInfo billingInfo) throws InvalidEmailException, UsedEmailException, InvalidPasswordException {
		if(!CheckHelper.isValidEmail(email)) {
			throw new InvalidEmailException();
		}
		
		if(!CheckHelper.isValidPassword(password)) {
			throw new InvalidPasswordException();
		}

		if(RegisteredDao.getRegisteredByEmail(email) != null) {
			throw new UsedEmailException();
		} 
		
		Registered registered = new Registered(email, password, billingInfo);
		RegisteredDao.insertRegistered(registered);
	}
}
