package logic.entity;


public class ShippingOrder extends Order {
    private Shipment shipment;

    
    public ShippingOrder(String id) {
    	super(id);
    	this.shipment = null;
    }

    
    public Shipment getShipment() {
        return this.shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

}
