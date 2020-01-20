package logic.entity;


public class ShippingOrder extends Order {
    private Shipment shipment;

    public ShippingOrder(String id) {
    	super(id);
    }

    public Shipment getShipment() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.shipment;
    }

    public void setShipment(Shipment value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.shipment = value;
    }

}
