package logic.dao;

import error.TextParseException;
import logic.entity.Beer;
import logic.entity.Container;
import logic.entity.Product;

public class ProductDao {
	private static String sep = "-";
	
	
	private ProductDao () {
		
	}
	
	
	public static String productToText(Product product) {
		StringBuilder stringBuilder = new StringBuilder();
		Beer beer = product.getBeer();
		Container container = product.getContainer();
		
		stringBuilder.append(beer.getId());
		stringBuilder.append(sep);
		stringBuilder.append(ContainerDao.containerToText(container));
		
		return stringBuilder.toString();
	}
	
	public static Product textToProduct(String text) throws TextParseException {		
		String[] pieces = text.split(sep);
		
		if(pieces.length != 2) {
			throw new TextParseException();
		}
		
		try {
			Beer beer = BeerDao.getBeerById(pieces[0]);
			Container container = ContainerDao.textToContainer(pieces[1]);
						
			return new Product(beer, container);
		} catch (Exception e){
			throw new TextParseException(e);
		}
	}
	
}
