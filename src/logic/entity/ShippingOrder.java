package logic.entity;


public class ShippingOrder extends Order {
    private String shipment;
    private String company;

    
    public ShippingOrder(String id) {
    	super(id);
    	this.shipment = null;
    }

    
    @Override
	public OrderType getType() {
		return OrderType.SHIPPING;
	}
    
    public String getShipment() {
        return this.shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
