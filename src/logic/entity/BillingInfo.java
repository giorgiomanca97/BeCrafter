package logic.entity;


public class BillingInfo {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String phone;
    private String card;

    
    public BillingInfo() {
    	this.firstName = "";
    	this.lastName = "";
    	this.address = "";
    	this.city = "";
    	this.country = "";
    	this.postalCode = "";
    	this.phone = "";
    	this.card = "";
    }

    
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toString() {	
    	return "BillingInfo: " + firstName  + " " + lastName + " " + address + " " + city + " " + country + " " + postalCode + " " + phone + " " + card;
    }
}
