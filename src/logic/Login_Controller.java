package logic;


import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import logic.entity.BillingInfo;
import logic.entity.Registered;
import logic.entity.dao.Registered_dao;

public class Login_Controller {
	private static Login_Controller instance = null;
	private static String invalidEmailChars = ",:;!?^*+-/%[](){}";
	
	private Registered loggedCustomer = null;
	
	
	private Login_Controller() {
		
	}
	
	synchronized public static Login_Controller GetInstance() {
		if(instance == null) {
			instance = new Login_Controller();
		}
		
		return instance;
	}
	
	public boolean isLogged(String email) {
		if(loggedCustomer != null && loggedCustomer.getEmail().equals(email)) {
			return true;
		} else {
			return false;
		}
	}

	public void login(String email, String password) throws InexistentEmailException, WrongPasswordException{
		Registered registered = Registered_dao.getRegisteredByEmail(email);
		
		if(registered.getEmail().equals(email)) {
			if(registered.getPassword().equals(password)) {
				loggedCustomer = registered;
			} else {
				throw new WrongPasswordException();
			}
		} else {
			throw new InexistentEmailException();
		}
	}
	
	public void register(String email, String password, BillingInfo billingInfo) throws InvalidEmailException, UsedEmailException, InvalidPasswordException {
		if(email.length() == 0) {
			throw new InvalidEmailException();
		}
		
		// Controllo caratteri invalidi
		for(int i = 0; i < invalidEmailChars.length(); i++) {
			if(email.contains(String.valueOf(invalidEmailChars.charAt(i)))) {
				throw new InvalidEmailException();
			}
		}
		
		// Troppe chiocciole
		int atCounter = 0;
		for (int i = 0; i < email.length(); i++) {
			atCounter++;
		}
		if(atCounter != 1) {
			throw new InvalidEmailException();
		}
		
		// Controllo lunghezza parte del nome
		String user = email.split("@")[0];
		if(user.length() == 0) {
			throw new InvalidEmailException();
		}

		// Controllo dominio
		String domain = email.split("@")[1];
		if(!domain.contains(".") || domain.charAt(domain.length()-1) == '.') {
			throw new InvalidEmailException();
		}
		
		// Controllo email già esistente
		if(Registered_dao.getRegisteredByEmail(email) != null) {
			throw new UsedEmailException();
		} 
		
		// Controllo lunghezza password
		if(password.length() < 8) {
			throw new InvalidPasswordException();
		}
		
		// Controllo requisiti password
		int digitChat = 0;
		int lowerChar = 0;
		int upperChar = 0;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			
			if(Character.isDigit(c)) {
				digitChat++;
			}
			if(Character.isLowerCase(c)) {
				lowerChar++;
			}
			if(Character.isUpperCase(c)) {
				upperChar++;
			}
		}
		if(digitChat == 0 || lowerChar == 0 || upperChar == 0) {
			throw new InvalidPasswordException();
		}
		
		Registered registered = new Registered(email, password, billingInfo);
		Registered_dao.insertRegistered(registered);
	}
}
