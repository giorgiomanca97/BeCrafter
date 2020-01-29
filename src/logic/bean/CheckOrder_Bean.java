package logic.bean;

import error.OrderNotFoundException;
import logic.CheckOrder_Controller;
import logic.designclasses.IdConverter;
import logic.entity.Order;
import logic.entity.Price;

public class CheckOrder_Bean {
	private String co_orderId;
	private String co_date;
	private String co_price;
	private String co_email;
	private String co_shippingCode;
	private String co_shippingCompany;
	
	
	public CheckOrder_Bean() {
		
	}
	
	
	// Getters and Setters
	public String getOrderId() {
		return co_orderId;
	}
	
	public void setOrderId(String orderId) {
		this.co_orderId = orderId;
	}
	
	
	public String getDate() {
		return co_date;
	}
	
	public void setDate(String date) {
		this.co_date = date;
	}
	
	
	public String getPrice() {
		return co_price;
	}
	
	public void setPrice(String price) {
		this.co_price = price;
	}
	
	
	public String getEmail() {
		return co_email;
	}
	
	public void setEmail(String email) {
		this.co_email = email;
	}
	
	
	public String getShippingCode() {
		return co_shippingCode;
	}
	
	public void setShippingCode(String shippingCode) {
		this.co_shippingCode = shippingCode;
	}
	
	
	public String getShippingCompany() {
		return co_shippingCompany;
	}
	
	public void setShippingCompany(String shippingCompany) {
		this.co_shippingCompany = shippingCompany;
	}
	// ==============================
	
	
	public void searchOrder() throws OrderNotFoundException {
		if(!IdConverter.isIdValid(co_orderId, IdConverter.Type.ORDER)) {
			throw new OrderNotFoundException();
		}
		
		Order order = CheckOrder_Controller.getInstance().searchOrder(co_orderId);
		co_date = order.getDate();
		co_price = Price.toText(order.getPrice());
		co_email = order.getEmail();
		co_shippingCode = order.getShippingCode();
		co_shippingCompany = order.getShippingCompany();
		
		if(co_shippingCode.length() == 0) {
			co_shippingCode = "not yet available";
		}
		
		if(co_shippingCompany.length() == 0) {
			co_shippingCompany = "not yet available";
		}
	}
	
}
