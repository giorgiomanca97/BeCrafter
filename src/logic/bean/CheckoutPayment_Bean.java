package logic.bean;


import error.login.InvalidEmailException;
import logic.BuyBeer_Controller;
import logic.entity.BillingInfo;

public class CheckoutPayment_Bean {
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String country;
	private String postalCode;
	private String phoneNumber;
	private String creditCard;
	
	public CheckoutPayment_Bean() {
		
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	
	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	
	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	
	public String getPostalCode() {
		return postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	public String getCrediCard() {
		return creditCard;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	
	public String confirmPurchase() throws Exception {
		if(email.length() == 0 || !email.contains("@")) {
			throw new InvalidEmailException();
		}
		
		if(firstName == "" || lastName == "" || address == "" || city == "" ||
				country == "" || postalCode == "" || phoneNumber == "" ||
				creditCard == "") {
			throw new Exception();
		}
		
		BillingInfo billingInfo = new BillingInfo();
		billingInfo.setFirstName(firstName);
		billingInfo.setLastName(lastName);
		billingInfo.setAddress(address);
		billingInfo.setCity(city);
		billingInfo.setCountry(country);
		billingInfo.setPostalCode(postalCode);
		billingInfo.setPhone(phoneNumber);
		billingInfo.setCard(creditCard);
		
		return BuyBeer_Controller.getInstance().confirmPurchase(email, billingInfo);
	}
}
