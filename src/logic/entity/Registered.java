package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class Registered {
    private String email;

    private String password;

    private BillingInfo billingInfo;

    private List<Order> orders = new ArrayList<Order> ();

    public Registered(String email, String password) {
    }

    public String getEmail() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.email;
    }

    public void setEmail(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.email = value;
    }

    public String getPassword() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.password;
    }

    public void setPassword(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.password = value;
    }

    public BillingInfo getBillingInfo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.billingInfo;
    }

    public void setBillingInfo(BillingInfo value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.billingInfo = value;
    }

    public List<Order> getOrders() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.orders;
    }

    public Order getOrderById(String id) {
    	return null;
    }

    public void addOrder(Order order) {
    }

    public void removeOrderById(String id) {
    }

    public void removeAllOrders() {
    }

}
