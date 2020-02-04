package logic.bean;


import error.EmptyCartException;
import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.PaymentRefusedException;
import error.WrongFieldException;
import error.id.IdException;
import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.LoginException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import logic.BuyBeer_Controller;
import logic.Login_Controller;
import logic.designclasses.CheckHelper;
import logic.entity.BillingInfo;
import logic.entity.Registered;

public class CheckoutPayment_Bean{
	private String cpEmail;
	private String cpPassword;
	private String cpFirstName;
	private String cpLastName;
	private String cpAddress;
	private String cpCity;
	private String cpCountry;
	private String cpPostalCode;
	private String cpPhoneNumber;
	private String cpCreditCard;
	
	
	public CheckoutPayment_Bean() {
		cpEmail = "";
		cpPassword = "";
		cpFirstName = "";
		cpLastName = "";
		cpAddress = "";
		cpCity = "";
		cpCountry = "";
		cpPostalCode = "";
		cpPhoneNumber = "";
		cpCreditCard = "";
	}


	// Getters and Setters
	public String getEmail() {
		return cpEmail;
	}

	public void setEmail(String email) {
		this.cpEmail = email;
	}


	public String getPassword() {
		return cpPassword;
	}

	public void setPassword(String password) {
		this.cpPassword = password;
	}


	public String getFirstName() {
		return cpFirstName;
	}

	public void setFirstName(String firstName) {
		this.cpFirstName = firstName;
	}


	public String getLastName() {
		return cpLastName;
	}

	public void setLastName(String lastName) {
		this.cpLastName = lastName;
	}


	public String getAddress() {
		return cpAddress;
	}

	public void setAddress(String address) {
		this.cpAddress = address;
	}


	public String getCity() {
		return cpCity;
	}

	public void setCity(String city) {
		this.cpCity = city;
	}


	public String getCountry() {
		return cpCountry;
	}

	public void setCountry(String country) {
		this.cpCountry = country;
	}


	public String getPostalCode() {
		return cpPostalCode;
	}

	public void setPostalCode(String postalCode) {
		this.cpPostalCode = postalCode;
	}


	public String getPhoneNumber() {
		return cpPhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.cpPhoneNumber = phoneNumber;
	}


	public String getCreditCard() {
		return cpCreditCard;
	}

	public void setCreditCard(String creditCard) {
		this.cpCreditCard = creditCard;
	}
	// ==============================
	
	
	public String loggedCustomer() {
		Registered customer = Login_Controller.getInstance().getLoggedCustomer();
		
		if(customer == null) {
			return null;
		} else {
			return customer.getEmail();
		}
	}
	
	public void loadLoggedCustomer() throws LoginException {	
		Registered registered = Login_Controller.getInstance().getLoggedCustomer();
		
		if(registered != null) {
			this.cpEmail = registered.getEmail();
			this.cpFirstName = registered.getBillingInfo().getFirstName();
			this.cpLastName = registered.getBillingInfo().getLastName();
			this.cpAddress = registered.getBillingInfo().getAddress();
			this.cpCity = registered.getBillingInfo().getCity();
			this.cpCountry = registered.getBillingInfo().getCountry();
			this.cpPostalCode = registered.getBillingInfo().getPostalCode();
			this.cpPhoneNumber = registered.getBillingInfo().getPhone();
			this.cpCreditCard = registered.getBillingInfo().getCard();
		} else {
			throw new LoginException();
		}
	}
	
	public String confirmPurchase() throws EmptyCartException, InvalidEmailException, EmptyFieldException, UsedEmailException, IllegalCharacterException, PaymentRefusedException, IdException, WrongFieldException  {
		if(cpEmail.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(!cpEmail.contains("@")) {
			throw new InvalidEmailException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		return BuyBeer_Controller.getInstance().confirmPurchase(cpEmail, billingInfo);
	}
	
	public void login() throws InexistentEmailException, WrongPasswordException, EmptyFieldException, IllegalCharacterException {
		if(cpEmail.length() == 0 || cpPassword.length() == 0) {
			throw new EmptyFieldException();
		}
		if(cpEmail.contains("'") || cpPassword.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		Login_Controller.getInstance().login(cpEmail, cpPassword);
	}
		
	
	private BillingInfo getBillingInfo() throws EmptyFieldException, IllegalCharacterException, WrongFieldException {
		BillingInfo billingInfo = new BillingInfo();
		
		if(cpFirstName.length() == 0 || cpLastName.length() == 0 || cpAddress.length() == 0 || 
				cpCity.length() == 0 || cpCountry.length() == 0 || cpPostalCode.length() == 0 || 
		   		cpPhoneNumber.length() == 0 || cpCreditCard.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(cpFirstName.contains("'") || cpLastName.contains("'") || cpAddress.contains("'") || 
				cpCity.contains("'") || cpCountry.contains("'") || cpPostalCode.contains("'") || 
		   		cpPhoneNumber.contains("'") || cpCreditCard.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		if(!CheckHelper.isOnlyLetters(cpFirstName) ||
		   !CheckHelper.isOnlyLetters(cpLastName) || 
		   !CheckHelper.isValidString(cpAddress) ||
		   !CheckHelper.isOnlyLetters(cpCity) ||
		   !CheckHelper.isOnlyLetters(cpCountry) ||
		   !CheckHelper.isOnlyDigits(cpPostalCode) ||
		   !CheckHelper.isValidPhoneNumber(cpPhoneNumber) ||
		   !CheckHelper.isValidCreditCard(cpCreditCard)) {
			throw new WrongFieldException();
		}
		
		billingInfo.setFirstName(cpFirstName);
		billingInfo.setLastName(cpLastName);
		billingInfo.setAddress(cpAddress);
		billingInfo.setCity(cpCity);
		billingInfo.setCountry(cpCountry);
		billingInfo.setPostalCode(cpPostalCode);
		billingInfo.setPhone(cpPhoneNumber);
		billingInfo.setCard(cpCreditCard);
		
		return billingInfo;
	}
}
