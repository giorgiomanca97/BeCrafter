package logic;

import logic.entity.Order;

public class CheckOrder_Controller {
	
	private static CheckOrder_Controller instance = null;
	
	synchronized public static CheckOrder_Controller getInstance() {
		if(instance == null) {
			instance = new CheckOrder_Controller();
		}
		
		return instance;
	}
	
	public Order searchOrder(String orderId) {
		Order order = new Order(orderId);
		//TODO: search in database and set bean
		return order;
	}
	
}
