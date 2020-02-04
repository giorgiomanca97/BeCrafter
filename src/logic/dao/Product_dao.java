package logic.dao;

import error.TextParseException;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.Product;

public class Product_dao {
	private static String sep = "-";
	
	
	private Product_dao () {
		
	}
	
	
	public static String productToText(Product product) {
		StringBuilder stringBuilder = new StringBuilder();
		Beer beer = product.getBeer();
		Container container = product.getContainer();
		
		stringBuilder.append(beer.getId());
		stringBuilder.append(sep);
		stringBuilder.append(Container_dao.containerToText(container));
		
		return stringBuilder.toString();
	}
	
	public static Product textToProduct(String text) throws TextParseException {		
		String[] pieces = text.split(sep);
		
		if(pieces.length != 2) {
			throw new TextParseException();
		}
		
		try {
			Beer beer = Beer_dao.getBeerById(pieces[0]);
			Container container = Container_dao.textToContainer(pieces[1]);
			
			Product product = new Product(beer, container);
			
			return product;
		} catch (Exception e){
			throw new TextParseException(e);
		}
	}
	
}
