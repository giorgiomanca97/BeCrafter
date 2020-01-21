package logic.entity;

import java.util.ArrayList;

public class PeriodicOrder extends Order {
	private Registered registered;
    private int daysForRenewal;
    private int daysToRenewal;
    private int renewalsCounter;
    private String lastRenewalDate;
    private boolean active;
    private ArrayList<ShippingOrder> shippingOrders;

    
    public PeriodicOrder(String id, Registered registered, int daysForRenewal) {
    	super(id);
    	this.registered = registered;
    	this.daysForRenewal = daysForRenewal;
    	this.daysToRenewal = daysForRenewal;
    	this.renewalsCounter = 0;
    	this.lastRenewalDate = "";
    	this.active = true;
    	this.shippingOrders = new ArrayList<ShippingOrder>();
    }

    
    public int getDaysForRenewal() {
        return this.daysForRenewal;
    }

    public int getDaysToRenewal() {
        return this.daysToRenewal;
    }
    
    public int getRenewalsCounter() {
    	return this.renewalsCounter;
    }

    public String getLastRenewalDate() {
        return this.lastRenewalDate;
    }

    public ArrayList<ShippingOrder> getShippingOrders() {
        return this.shippingOrders;
    }

    public boolean isActive() {
        return this.active;
    }

    public ShippingOrder generateShippingOrder() {
    	String orderId = super.getId();
    	String email = registered.getEmail();
    	float price = super.getPrice();
    	BillingInfo billingInfo = registered.getBillingInfo();
    	ArrayList<Product> products = super.getProducts();
    	
    	String newId = orderId + "_" + String.valueOf(this.renewalsCounter + 1);
    	ShippingOrder order = new ShippingOrder(newId);
    	order.setDate("");
    	order.setEmail(email);
    	order.setPrice(price);
    	order.setBillingInfo(billingInfo);
    	for (Product product : products) {
			order.addProduct(product);
		}
    	
    	shippingOrders.add(order);
    	this.renewalsCounter++;
    	
    	return order;
    }

    public void stopRenewal() {
    	this.active = false;
        this.daysToRenewal = -1;
    }
}
