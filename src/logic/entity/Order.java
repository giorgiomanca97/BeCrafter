package logic.entity;


import java.util.ArrayList;
import java.util.List;

import logic.designclasses.StorableCloneList;
import logic.entity.interfaces.Storable;

public class Order {
    private String id;
    private String email;
    private String date;
    private float price;
    private String shippingCode;
    private String shippingCompany;
    private BillingInfo billingInfo;
    private StorableCloneList products;

    
    public Order(String id) {
    	this.id = id;
    	this.date = "";
    	this.email = "";
    	this.price = 0;
    	this.billingInfo = null;
    	this.products = new StorableCloneList();
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
    
    public String getShippingCode() {
		return shippingCode;
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

    public BillingInfo getBillingInfo() {
        return this.billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
    
    
    public List<Product> getProducts(){
    	List<Product> result = new ArrayList<Product>();
    	
    	List<Storable> storables = products.getAll();
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
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("Order: " + id + " " + email + " " + date + " " + Price.toText(price) + "€ " + shippingCode + " " + shippingCompany + "\n");
    	stringBuilder.append(billingInfo.toString() + "\n");
    	for (Storable storable : products.getAll()) {
    		stringBuilder.append(storable.toString() + "\n");
		}
    	
    	return stringBuilder.toString();
    }
}
