package logic.bean;


import error.EmptyFieldException;
import error.IllegalCharacterException;
import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.LoginException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import logic.BuyBeer_Controller;
import logic.Login_Controller;
import logic.entity.BillingInfo;
import logic.entity.Registered;

public class Customer_Bean {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String country;
	private String postalCode;
	private String phoneNumber;
	private String creditCard;
	
	
	public Customer_Bean() {
		
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	public void loadLoggedCustomer() throws LoginException {	
		Registered registered = Login_Controller.getInstance().getLoggedCustomer();
		
		if(registered != null) {
			this.email = registered.getEmail();
			this.firstName = registered.getBillingInfo().getFirstName();
			this.lastName = registered.getBillingInfo().getLastName();
			this.address = registered.getBillingInfo().getAddress();
			this.city = registered.getBillingInfo().getCity();
			this.country = registered.getBillingInfo().getCountry();
			this.postalCode = registered.getBillingInfo().getPostalCode();
			this.phoneNumber = registered.getBillingInfo().getPhone();
			this.creditCard = registered.getBillingInfo().getCard();
		} else {
			throw new LoginException();
		}
	}
	
	public String confirmPurchase() throws InvalidEmailException, EmptyFieldException, UsedEmailException, IllegalCharacterException, Exception  {
		if(email.length() == 0 || !email.contains("@")) {
			throw new InvalidEmailException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		return BuyBeer_Controller.getInstance().confirmPurchase(email, billingInfo);
	}
	
	public void login() throws InexistentEmailException, WrongPasswordException, EmptyFieldException, IllegalCharacterException {
		if(email.length() == 0 || password.length() == 0) {
			throw new EmptyFieldException();
		}
		if(email.contains("'") || password.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		Login_Controller.getInstance().login(email, password);
	}
	
	public void register() throws InvalidEmailException, UsedEmailException, InvalidPasswordException, EmptyFieldException, IllegalCharacterException {
		if(email.length() == 0 || password.length() == 0) {
			throw new EmptyFieldException();
		}
		if(email.contains("'") || password.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		Login_Controller.getInstance().register(email, password, billingInfo);
	}
	
	
	private BillingInfo getBillingInfo() throws EmptyFieldException, IllegalCharacterException {
		BillingInfo billingInfo = new BillingInfo();
		
		if(firstName.length() == 0 || lastName.length() == 0 || address.length() == 0 || 
				city.length() == 0 || country.length() == 0 || postalCode.length() == 0 || 
		   		phoneNumber.length() == 0 || creditCard.length() == 0) {
			throw new EmptyFieldException();
		}
		
		if(firstName.contains("'") || lastName.contains("'") || address.contains("'") || 
				city.contains("'") || country.contains("'") || postalCode.contains("'") || 
		   		phoneNumber.contains("'") || creditCard.contains("'")) {
			throw new IllegalCharacterException();
		}
		
		billingInfo.setFirstName(firstName);
		billingInfo.setLastName(lastName);
		billingInfo.setAddress(address);
		billingInfo.setCity(city);
		billingInfo.setCountry(country);
		billingInfo.setPostalCode(postalCode);
		billingInfo.setPhone(phoneNumber);
		billingInfo.setCard(creditCard);
		
		return billingInfo;
	}
}
