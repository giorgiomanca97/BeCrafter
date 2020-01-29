package logic.bean;


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
import logic.designclasses.BeanHelper;
import logic.entity.BillingInfo;
import logic.entity.Registered;

public class CheckoutPayment_Bean{
	private String cp_email;
	private String cp_password;
	private String cp_firstName;
	private String cp_lastName;
	private String cp_address;
	private String cp_city;
	private String cp_country;
	private String cp_postalCode;
	private String cp_phoneNumber;
	private String cp_creditCard;
	
	
	public CheckoutPayment_Bean() {
		
	}


	// Getters and Setters
	public String getEmail() {
		return cp_email;
	}

	public void setEmail(String email) {
		this.cp_email = email;
	}


	public String getPassword() {
		return cp_password;
	}

	public void setPassword(String password) {
		this.cp_password = password;
	}


	public String getFirstName() {
		return cp_firstName;
	}

	public void setFirstName(String firstName) {
		this.cp_firstName = firstName;
	}


	public String getLastName() {
		return cp_lastName;
	}

	public void setLastName(String lastName) {
		this.cp_lastName = lastName;
	}


	public String getAddress() {
		return cp_address;
	}

	public void setAddress(String address) {
		this.cp_address = address;
	}


	public String getCity() {
		return cp_city;
	}

	public void setCity(String city) {
		this.cp_city = city;
	}


	public String getCountry() {
		return cp_country;
	}

	public void setCountry(String country) {
		this.cp_country = country;
	}


	public String getPostalCode() {
		return cp_postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.cp_postalCode = postalCode;
	}


	public String getPhoneNumber() {
		return cp_phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.cp_phoneNumber = phoneNumber;
	}


	public String getCreditCard() {
		return cp_creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.cp_creditCard = creditCard;
	}
	// ==============================
	
	
	public void loadLoggedCustomer() throws LoginException {	
		Registered registered = Login_Controller.getInstance().getLoggedCustomer();
		
		if(registered != null) {
			this.cp_email = registered.getEmail();
			this.cp_firstName = registered.getBillingInfo().getFirstName();
			this.cp_lastName = registered.getBillingInfo().getLastName();
			this.cp_address = registered.getBillingInfo().getAddress();
			this.cp_city = registered.getBillingInfo().getCity();
			this.cp_country = registered.getBillingInfo().getCountry();
			this.cp_postalCode = registered.getBillingInfo().getPostalCode();
			this.cp_phoneNumber = registered.getBillingInfo().getPhone();
			this.cp_creditCard = registered.getBillingInfo().getCard();
		} else {
			throw new LoginException();
		}
	}
	
	public String confirmPurchase() throws InvalidEmailException, EmptyFieldException, UsedEmailException, IllegalCharacterException, PaymentRefusedException, IdException, WrongFieldException  {
		if(cp_email.length() == 0 || cp_password.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(!cp_email.contains("@")) {
			throw new InvalidEmailException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		return BuyBeer_Controller.getInstance().confirmPurchase(cp_email, billingInfo);
	}
	
	public void login() throws InexistentEmailException, WrongPasswordException, EmptyFieldException, IllegalCharacterException {
		if(cp_email.length() == 0 || cp_password.length() == 0) {
			throw new EmptyFieldException();
		}
		if(cp_email.contains("'") || cp_password.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		Login_Controller.getInstance().login(cp_email, cp_password);
	}
		
	
	private BillingInfo getBillingInfo() throws EmptyFieldException, IllegalCharacterException, WrongFieldException {
		BillingInfo billingInfo = new BillingInfo();
		
		if(cp_firstName.length() == 0 || cp_lastName.length() == 0 || cp_address.length() == 0 || 
				cp_city.length() == 0 || cp_country.length() == 0 || cp_postalCode.length() == 0 || 
		   		cp_phoneNumber.length() == 0 || cp_creditCard.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(cp_firstName.contains("'") || cp_lastName.contains("'") || cp_address.contains("'") || 
				cp_city.contains("'") || cp_country.contains("'") || cp_postalCode.contains("'") || 
		   		cp_phoneNumber.contains("'") || cp_creditCard.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		if(!BeanHelper.isOnlyLetters(cp_firstName) ||
		   !BeanHelper.isOnlyLetters(cp_lastName) || 
		   !BeanHelper.isValidString(cp_address) ||
		   !BeanHelper.isOnlyLetters(cp_city) ||
		   !BeanHelper.isOnlyLetters(cp_country) ||
		   !BeanHelper.isOnlyDigits(cp_postalCode) ||
		   !BeanHelper.isValidPhoneNumber(cp_phoneNumber) ||
		   !BeanHelper.isValidCreditCard(cp_creditCard)) {
			throw new WrongFieldException();
		}
		
		billingInfo.setFirstName(cp_firstName);
		billingInfo.setLastName(cp_lastName);
		billingInfo.setAddress(cp_address);
		billingInfo.setCity(cp_city);
		billingInfo.setCountry(cp_country);
		billingInfo.setPostalCode(cp_postalCode);
		billingInfo.setPhone(cp_phoneNumber);
		billingInfo.setCard(cp_creditCard);
		
		return billingInfo;
	}
}
