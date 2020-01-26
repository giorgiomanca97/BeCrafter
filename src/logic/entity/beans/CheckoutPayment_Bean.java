package logic.entity.beans;

public class CheckoutPayment_Bean {
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String country;
	private String postalCode;
	private String phoneNumber;
	private String crediCard;
	
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
		return crediCard;
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

	public void setCrediCard(String crediCard) {
		this.crediCard = crediCard;
	}
	
	
	public boolean confirmPurchase() {
		return true;
	}
}
