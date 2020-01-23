package logic.entity.dao;

import error.TextParseException;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.Product;

public class Product_dao {
	private static String SEP = "-";
	
	private Product_dao () {
		
	}
	
	public static String productToText(Product product) {
		StringBuilder stringBuilder = new StringBuilder();
		Beer beer = product.getBeer();
		Container container = product.getContainer();
		
		stringBuilder.append(beer);
		stringBuilder.append(SEP);
		stringBuilder.append(Container_dao.containerToText(container));
		
		return stringBuilder.toString();
	}
	
	public static Product textToProduct(String text) throws TextParseException {
		Product product;
		Beer beer;
		Container container;
		
		String[] pieces = text.split(SEP);
		
		if(pieces.length != 2) {
			throw new TextParseException();
		}
		
		try {
			beer = Beer_dao.getBeerById(pieces[0]);
			container = Container_dao.textToContainer(pieces[1]);
			
			product = new Product(beer, container);
			
			return product;
		} catch (Exception e){
			throw new TextParseException(e);
		}
	}
}
