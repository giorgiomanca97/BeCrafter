package logic.bean;

import logic.CheckOrder_Controller;
import logic.entity.Order;
import logic.entity.Price;

public class CheckOrder_Bean {
	private String orderId;
	private String date;
	private String price;
	private String email;
	private String shippingCode;
	private String shippingCompany;
	
	public CheckOrder_Bean() {
		
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getShippingCode() {
		return shippingCode;
	}
	
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	
	
	public String getShippingCompany() {
		return shippingCompany;
	}
	
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	
	public void searchOrder() {
		Order order = CheckOrder_Controller.getInstance().searchOrder(this.orderId);
		setDate(order.getDate());
		setPrice(Price.toText( order.getPrice() ));
		setEmail(order.getEmail());
		setShippingCode(order.getShippingCode());
		setShippingCompany(order.getShippingCompany());
	}
	
}
