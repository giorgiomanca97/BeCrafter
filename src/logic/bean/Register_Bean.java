package logic.bean;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.WrongFieldException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.PasswordMatchingException;
import error.login.UsedEmailException;
import logic.Login_Controller;
import logic.designclasses.CheckHelper;
import logic.entity.BillingInfo;

public class Register_Bean {
	private String rEmail;
	private String rPassword;
	private String rConfirmPassword;
	private String rFirstName;
	private String rLastName;
	private String rAddress;
	private String rCity;
	private String rCountry;
	private String rPostalCode;
	private String rPhoneNumber;
	private String rCreditCard;
	
	
	public Register_Bean() {
		rEmail = "";
		rPassword = "";
		rConfirmPassword = "";
		rFirstName = "";
		rLastName = "";
		rAddress = "";
		rCity = "";
		rCountry = "";
		rPostalCode = "";
		rPhoneNumber = "";
		rPhoneNumber = "";
		rCreditCard = "";
	}


	// Getters and Setters
	public String getEmail() {
		return rEmail;
	}

	public void setEmail(String email) {
		this.rEmail = email;
	}


	public String getPassword() {
		return rPassword;
	}

	public void setPassword(String password) {
		this.rPassword = password;
	}

	
	public String getConfirmPassword() {
		return rConfirmPassword;
	}

	public void setConfirmPassword(String password) {
		this.rConfirmPassword = password;
	}
	

	public String getFirstName() {
		return rFirstName;
	}

	public void setFirstName(String firstName) {
		this.rFirstName = firstName;
	}


	public String getLastName() {
		return rLastName;
	}

	public void setLastName(String lastName) {
		this.rLastName = lastName;
	}


	public String getAddress() {
		return rAddress;
	}

	public void setAddress(String address) {
		this.rAddress = address;
	}


	public String getCity() {
		return rCity;
	}

	public void setCity(String city) {
		this.rCity = city;
	}


	public String getCountry() {
		return rCountry;
	}

	public void setCountry(String country) {
		this.rCountry = country;
	}


	public String getPostalCode() {
		return rPostalCode;
	}

	public void setPostalCode(String postalCode) {
		this.rPostalCode = postalCode;
	}


	public String getPhoneNumber() {
		return rPhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.rPhoneNumber = phoneNumber;
	}


	public String getCreditCard() {
		return rCreditCard;
	}

	public void setCreditCard(String creditCard) {
		this.rCreditCard = creditCard;
	}
	// ==============================
	
	
	public void register() throws InvalidEmailException, UsedEmailException, InvalidPasswordException, EmptyFieldException, IllegalCharacterException, WrongFieldException, PasswordMatchingException {
		if(rEmail.length() == 0 || rPassword.length() == 0 || rConfirmPassword.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(rEmail.contains("'") || rPassword.contains("'") || rConfirmPassword.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		if(!rPassword.equals(rConfirmPassword)) {
			throw new PasswordMatchingException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		Login_Controller.getInstance().register(rEmail, rPassword, billingInfo);
	}
	
	
	private BillingInfo getBillingInfo() throws EmptyFieldException, IllegalCharacterException, WrongFieldException {
		BillingInfo billingInfo = new BillingInfo();
		
		if(rFirstName.length() == 0 || rLastName.length() == 0 || rAddress.length() == 0 || 
				rCity.length() == 0 || rCountry.length() == 0 || rPostalCode.length() == 0 || 
		   		rPhoneNumber.length() == 0 || rCreditCard.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(rFirstName.contains("'") || rLastName.contains("'") || rAddress.contains("'") || 
				rCity.contains("'") || rCountry.contains("'") || rPostalCode.contains("'") || 
		   		rPhoneNumber.contains("'") || rCreditCard.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		if(!CheckHelper.isOnlyLetters(rFirstName) ||
		   !CheckHelper.isOnlyLetters(rLastName) || 
		   !CheckHelper.isValidString(rAddress) ||
		   !CheckHelper.isOnlyLetters(rCity) ||
		   !CheckHelper.isOnlyLetters(rCountry) ||
		   !CheckHelper.isOnlyDigits(rPostalCode) ||
		   !CheckHelper.isValidPhoneNumber(rPhoneNumber) ||
		   !CheckHelper.isValidCreditCard(rCreditCard)) {
			throw new WrongFieldException();
		}
		
		billingInfo.setFirstName(rFirstName);
		billingInfo.setLastName(rLastName);
		billingInfo.setAddress(rAddress);
		billingInfo.setCity(rCity);
		billingInfo.setCountry(rCountry);
		billingInfo.setPostalCode(rPostalCode);
		billingInfo.setPhone(rPhoneNumber);
		billingInfo.setCard(rCreditCard);
		
		return billingInfo;
	}
}
