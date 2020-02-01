package test;

import static org.junit.Assert.*;

import org.junit.Test;

import error.OrderNotFoundException;
import logic.CheckOrder_Controller;
import logic.entity.Order;

public class TestCheckOrder_Controller {

	@Test
	public void testSearchOrder() {
		String message = "";
		String Id = "X000001";
		
		String orderId = "";
		String orderPurchaseDate = "";
		float orderOverallPrice = 0f;
		String orderEmail = "";
		String orderShippingCode = "";
		String orderShippingEmail = "";
		
		try {
			Order order = CheckOrder_Controller.getInstance().searchOrder(Id);
			orderId = order.getId();
			orderPurchaseDate = order.getDate();
			orderOverallPrice = order.getPrice();
			orderEmail = order.getEmail();
			orderShippingCode = order.getShippingCode();
			orderShippingEmail = order.getShippingCompany();
			
		} catch (OrderNotFoundException e) {
			message = "Order Not Found";
		}
		
		Object[] expecteds = {Id, "2020/02/01", 92.25f, "test@provider.org", "not yet available", "not yet available"};
		Object[] actuals = {orderId, orderPurchaseDate, orderOverallPrice, orderEmail, orderShippingCode, orderShippingEmail};
		
		assertArrayEquals(message, expecteds, actuals);
	}

}
