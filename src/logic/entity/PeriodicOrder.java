package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class PeriodicOrder extends Order {
    private int daysForRenewal;

    private int daysToRenewal;

    private String lastRenewalDate;

    private boolean active;

    private List<ShippingOrder> shippingOrders = new ArrayList<ShippingOrder> ();

    public PeriodicOrder(String id) {
    	super(id);
    }

    public int getDaysForRenewal() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.daysForRenewal;
    }

    public int getDaysToRenewal() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.daysToRenewal;
    }

    public String getLastRenewalDate() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.lastRenewalDate;
    }

    public List<ShippingOrder> getShippingOrders() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.shippingOrders;
    }

    public boolean isActive() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.active;
    }

    public ShippingOrder generateShippingOrder() {
    	return null;
    }

    public void stopRenewal() {
    }

}
