package logic.entity.dao;


import logic.entity.ShippingOrder;

public class ShippingOrder_dao {
	private static String SEP = ";";
	
	
	private ShippingOrder_dao() {
		
	}
	
	
	public static String shippingOrderToText(ShippingOrder shippingOrder) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("SO");
		stringBuilder.append(SEP);
		stringBuilder.append(shippingOrder.getId());
		stringBuilder.append(SEP);
		stringBuilder.append(shippingOrder.getShipment());
		stringBuilder.append(SEP);
		stringBuilder.append(shippingOrder.getCompany());
		
		return stringBuilder.toString();
	}
	
	public static ShippingOrder textToShippingOrder(String text) {
		
		String[] pieces = text.split(SEP);
		
		return null;
	}
}
