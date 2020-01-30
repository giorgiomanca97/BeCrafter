package logic.entity;


import java.util.ArrayList;

public class Registered {
    private String email;
    private String password;
    private BillingInfo billingInfo;
    private ArrayList<Order> orders;

    
    public Registered(String email, String password, BillingInfo billingInfo) {
    	this.email = email;
    	this.password = password;
    	this.billingInfo = billingInfo;
    	this.orders = new ArrayList<>();
    }

    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BillingInfo getBillingInfo() {
        return this.billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public Order getOrderById(String id) {
    	for (Order order : orders) {
			if(order.getId() == id) {
				return order;
			}
		}
    	return null;
    }

    public void addOrder(Order order) {
    	orders.add(order);
    }

    public void removeOrderById(String id) {
    	Order o;
    	
    	for (int i = 0; i < orders.size(); i++) {
			o = orders.get(i);
    		
    		if(o.getId() == id) {
				orders.remove(i);
				return;
			}
		}
    }

    public void removeAllOrders() {
    	orders = new ArrayList<>();
    }

}
