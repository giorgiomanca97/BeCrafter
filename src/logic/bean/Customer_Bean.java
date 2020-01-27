package logic.bean;


import error.EmptyFieldException;
import error.login.InexistentEmailException;
import error.login.InvalidEmailException;
import error.login.InvalidPasswordException;
import error.login.UsedEmailException;
import error.login.WrongPasswordException;
import logic.BuyBeer_Controller;
import logic.Login_Controller;
import logic.entity.BillingInfo;

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
	
	
	public String confirmPurchase() throws InvalidEmailException, EmptyFieldException, UsedEmailException, Exception  {
		if(email.length() == 0 || !email.contains("@")) {
			throw new InvalidEmailException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		return BuyBeer_Controller.getInstance().confirmPurchase(email, billingInfo);
	}
	
	public void login() throws InexistentEmailException, WrongPasswordException, EmptyFieldException {
		if(email.length() == 0 || password.length() == 0) {
			throw new EmptyFieldException();
		}
		
		Login_Controller.GetInstance().login(email, password);
	}
	
	public void register() throws InvalidEmailException, UsedEmailException, InvalidPasswordException, EmptyFieldException {
		if(email.length() == 0 || password.length() == 0) {
			throw new EmptyFieldException();
		}
		
		BillingInfo billingInfo = getBillingInfo();
		
		Login_Controller.GetInstance().register(email, password, billingInfo);
	}
	
	
	private BillingInfo getBillingInfo() throws EmptyFieldException {
		BillingInfo billingInfo = new BillingInfo();
		
		if(firstName.length() == 0 || lastName.length() == 0 || address.length() == 0 || 
				city.length() == 0 || country.length() == 0 || postalCode.length() == 0 || 
		   		phoneNumber.length() == 0 || creditCard.length() == 0) {
			throw new EmptyFieldException();
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
