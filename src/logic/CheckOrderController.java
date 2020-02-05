package logic;

import error.OrderNotFoundException;
import logic.dao.OrderDao;
import logic.entity.Order;

public class CheckOrderController {
	
	private static CheckOrderController instance = null;
	
	public static synchronized CheckOrderController getInstance() {
		if(instance == null) {
			instance = new CheckOrderController();
		}
		
		return instance;
	}
	
	public Order searchOrder(String orderId) throws OrderNotFoundException{
		Order order = OrderDao.getOrderById(orderId);
		if(order == null) {
			throw new OrderNotFoundException();
		}
		return order;
	}
	
}
