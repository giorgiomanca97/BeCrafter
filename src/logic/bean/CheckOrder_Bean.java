package logic.bean;

import error.OrderNotFoundException;
import logic.CheckOrder_Controller;
import logic.designclasses.IdConverter;
import logic.entity.Order;

public class CheckOrder_Bean {
	private String coOrderId;
	private String coDate;
	private float coPrice;
	private String coEmail;
	private String coShippingCode;
	private String coShippingCompany;
	
	
	public CheckOrder_Bean() {
		coOrderId = "";
		coDate = "";
		coPrice = 0f;
		coEmail = "";
		coShippingCode = "";
		coShippingCompany = "";
	}
	
	
	// Getters and Setters
	public String getOrderId() {
		return coOrderId;
	}
	
	public void setOrderId(String orderId) {
		this.coOrderId = orderId;
	}
	
	
	public String getDate() {
		return coDate;
	}
	
	public void setDate(String date) {
		this.coDate = date;
	}
	
	
	public float getPrice() {
		return coPrice;
	}
	
	public void setPrice(float price) {
		this.coPrice = price;
	}
	
	
	public String getEmail() {
		return coEmail;
	}
	
	public void setEmail(String email) {
		this.coEmail = email;
	}
	
	
	public String getShippingCode() {
		return coShippingCode;
	}
	
	public void setShippingCode(String shippingCode) {
		this.coShippingCode = shippingCode;
	}
	
	
	public String getShippingCompany() {
		return coShippingCompany;
	}
	
	public void setShippingCompany(String shippingCompany) {
		this.coShippingCompany = shippingCompany;
	}
	// ==============================
	
	
	public void searchOrder(String id) throws OrderNotFoundException {
		if(!IdConverter.isIdValid(id, IdConverter.Type.ORDER)) {
			throw new OrderNotFoundException();
		}
		
		Order order = CheckOrder_Controller.getInstance().searchOrder(id);
		coOrderId = order.getId();
		coDate = order.getDate();
		coPrice = order.getPrice();
		coEmail = order.getEmail();
		coShippingCode = order.getShippingCode();
		coShippingCompany = order.getShippingCompany();
		
		if(coShippingCode.length() == 0) {
			coShippingCode = "not yet available";
		}
		
		if(coShippingCompany.length() == 0) {
			coShippingCompany = "not yet available";
		}
	}
	
}
