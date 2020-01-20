package logic.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Order {
    private String id;

    private String data;

    private String email;

    private float price;

    private BillingInfo billingInfo;

    private List<Product> products = new ArrayList<Product> ();

    public Order(String id) {
    }

    public String getId() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.id;
    }

    public String getData() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.data;
    }

    public void setData(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.data = value;
    }

    public String getEmail() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.email;
    }

    public void setEmail(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.email = value;
    }

    public float getPrice() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.price;
    }

    public void setPrice(float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.price = value;
    }

    public BillingInfo getBillingInfo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.billingInfo;
    }

    public void setBillingInfo(BillingInfo value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.billingInfo = value;
    }

}
