package logic.entity;


public class Shipment {
    private String shippingCode;

    private String company;

    private String departureDate;

    private String deliveryDate;

    public Shipment(String shippingCode) {
    	this.shippingCode = shippingCode;
    	this.company = "";
    	this.departureDate = "";
    	this.deliveryDate = "";
    }

    public String getShippingCode() {
        return this.shippingCode;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
