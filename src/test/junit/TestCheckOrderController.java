package test.junit;


import static org.junit.Assert.*;

import org.junit.Test;

import error.OrderNotFoundException;
import logic.bean.CheckOrderBean;

public class TestCheckOrderController {

	// Davide Bianchi 0228110
	@Test
	public void testSearchOrder() {
		String message = "";
		String id = "X000001";
		
		String orderId = "";
		String orderPurchaseDate = "";
		float orderOverallPrice = 0f;
		String orderEmail = "";
		String orderShippingCode = "";
		String orderShippingEmail = "";
		
		CheckOrderBean coBean = new CheckOrderBean();
		
		try {
			coBean.searchOrder(id);
			orderId = coBean.getOrderId();
			orderPurchaseDate = coBean.getDate();
			orderOverallPrice = coBean.getPrice();
			orderEmail = coBean.getEmail();
			orderShippingCode = coBean.getShippingCode();
			orderShippingEmail = coBean.getShippingCompany();
			
		} catch (OrderNotFoundException e) {
			message = "Order Not Found";
		}
		
		Object[] expecteds = {id, "2020/02/01", 92.25f, "test@provider.org", "not yet available", "not yet available"};
		Object[] actuals = {orderId, orderPurchaseDate, orderOverallPrice, orderEmail, orderShippingCode, orderShippingEmail};
		
		assertArrayEquals(message, expecteds, actuals);
	}

}
