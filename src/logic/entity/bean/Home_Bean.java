package logic.entity.bean;


import java.util.ArrayList;

import logic.Home_Controller;
import logic.entity.BeerColor;
import logic.entity.BeerFiltering;
import logic.entity.BeerType;
import logic.entity.ContainerType;
import logic.entity.Product;

public class Home_Bean {
	public Home_Bean() {
		
	}
	
	public ArrayList<Product_Bean> showProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings, String searchName){
		 ArrayList<Product> products = Home_Controller.getInstance().showProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
		 ArrayList<Product_Bean> converted = new ArrayList<Product_Bean>();
		 
		 for (Product product : products) {
			 converted.add(generateBean(product));
		}
		 return converted;
	}	
	
	private Product_Bean generateBean(Product product) {
		Product_Bean product_Bean = new Product_Bean();
		
		product_Bean.setBeerId(product.getBeer().getId());
		product_Bean.setBeerName(product.getBeer().getName());
		product_Bean.setBeerType(product.getBeer().getType());
		product_Bean.setBeerColor(product.getBeer().getColor());
		product_Bean.setBeerAlcohol(product.getBeer().getAlcoholContent());
		product_Bean.setBeerFiltering(product.getBeer().getFiltering());
		product_Bean.setContainerType(product.getContainer().getType());
		product_Bean.setContainerVolume(product.getContainer().getVolume());
		product_Bean.setPrice(product.getPrice());
		
		return product_Bean;
	}
}
