package logic.entity;


import java.util.ArrayList;

import logic.designclasses.CloneStorableList;
import logic.entity.interfaces.Storable;

public abstract class Order {
    private String id;
    private String date;
    private String email;
    private float price;
    private BillingInfo billingInfo;
    private CloneStorableList products;

    
    public Order(String id) {
    	this.id = id;
    	this.date = "";
    	this.email = "";
    	this.price = 0;
    	this.billingInfo = null;
    	this.products = new CloneStorableList();
    }

    
    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BillingInfo getBillingInfo() {
        return this.billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
    
    
    public ArrayList<Product> getProducts(){
    	ArrayList<Product> result = new ArrayList<Product>();
    	
    	ArrayList<Storable> storables = products.getAll();
    	for (Storable storable : storables) {
			result.add((Product) storable);
		}
    	
    	return result;
    }
    
    public void addProduct(Product product) {
		products.add(product);
    }
    
    public Product removeProduct(Product product) {
		return (Product) products.remove(product);
    }
    public Product removeProduct(Beer beer, Container container) {
		return removeProduct(new Product(beer, container));
    }
}
