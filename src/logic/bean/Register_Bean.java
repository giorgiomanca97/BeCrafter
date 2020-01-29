package logic.bean;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.WrongFieldException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.UsedEmailException;
import logic.Login_Controller;
import logic.designclasses.BeanHelper;
import logic.entity.BillingInfo;

public class Register_Bean {
	private String r_email;
	private String r_password;
	private String r_firstName;
	private String r_lastName;
	private String r_address;
	private String r_city;
	private String r_country;
	private String r_postalCode;
	private String r_phoneNumber;
	private String r_creditCard;
	
	
	public Register_Bean() {
		
	}


	// Getters and Setters
	public String getEmail() {
		return r_email;
	}

	public void setEmail(String email) {
		this.r_email = email;
	}


	public String getPassword() {
		return r_password;
	}

	public void setPassword(String password) {
		this.r_password = password;
	}


	public String getFirstName() {
		return r_firstName;
	}

	public void setFirstName(String firstName) {
		this.r_firstName = firstName;
	}


	public String getLastName() {
		return r_lastName;
	}

	public void setLastName(String lastName) {
		this.r_lastName = lastName;
	}


	public String getAddress() {
		return r_address;
	}

	public void setAddress(String address) {
		this.r_address = address;
	}


	public String getCity() {
		return r_city;
	}

	public void setCity(String city) {
		this.r_city = city;
	}


	public String getCountry() {
		return r_country;
	}

	public void setCountry(String country) {
		this.r_country = country;
	}


	public String getPostalCode() {
		return r_postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.r_postalCode = postalCode;
	}


	public String getPhoneNumber() {
		return r_phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.r_phoneNumber = phoneNumber;
	}


	public String getCreditCard() {
		return r_creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.r_creditCard = creditCard;
	}
	// ==============================
	
	
	public void register() throws InvalidEmailException, UsedEmailException, InvalidPasswordException, EmptyFieldException, IllegalCharacterException, WrongFieldException {
		if(r_email.length() == 0 || r_password.length() == 0) {
			throw new EmptyFieldException();
		}
		if(r_email.contains("'") || r_password.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		Login_Controller.getInstance().register(r_email, r_password, billingInfo);
	}
	
	
	private BillingInfo getBillingInfo() throws EmptyFieldException, IllegalCharacterException, WrongFieldException {
		BillingInfo billingInfo = new BillingInfo();
		
		if(r_firstName.length() == 0 || r_lastName.length() == 0 || r_address.length() == 0 || 
				r_city.length() == 0 || r_country.length() == 0 || r_postalCode.length() == 0 || 
		   		r_phoneNumber.length() == 0 || r_creditCard.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(r_firstName.contains("'") || r_lastName.contains("'") || r_address.contains("'") || 
				r_city.contains("'") || r_country.contains("'") || r_postalCode.contains("'") || 
		   		r_phoneNumber.contains("'") || r_creditCard.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		if(!BeanHelper.isOnlyLetters(r_firstName) ||
		   !BeanHelper.isOnlyLetters(r_lastName) || 
		   !BeanHelper.isValidString(r_address) ||
		   !BeanHelper.isOnlyLetters(r_city) ||
		   !BeanHelper.isOnlyLetters(r_country) ||
		   !BeanHelper.isOnlyDigits(r_postalCode) ||
		   !BeanHelper.isValidPhoneNumber(r_phoneNumber) ||
		   !BeanHelper.isValidCreditCard(r_creditCard)) {
			throw new WrongFieldException();
		}
		
		billingInfo.setFirstName(r_firstName);
		billingInfo.setLastName(r_lastName);
		billingInfo.setAddress(r_address);
		billingInfo.setCity(r_city);
		billingInfo.setCountry(r_country);
		billingInfo.setPostalCode(r_postalCode);
		billingInfo.setPhone(r_phoneNumber);
		billingInfo.setCard(r_creditCard);
		
		return billingInfo;
	}
}
