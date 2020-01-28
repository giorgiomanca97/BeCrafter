package logic;

import error.OrderNotFoundException;
import logic.entity.Order;
import logic.entity.dao.Order_dao;

public class CheckOrder_Controller {
	
	private static CheckOrder_Controller instance = null;
	
	synchronized public static CheckOrder_Controller getInstance() {
		if(instance == null) {
			instance = new CheckOrder_Controller();
		}
		
		return instance;
	}
	
	public Order searchOrder(String orderId) throws OrderNotFoundException{
		Order order = Order_dao.getOrderById(orderId);
		if(order == null) {
			throw new OrderNotFoundException();
		}
		return order;
	}
	
}
