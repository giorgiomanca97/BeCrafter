package logic.entity.beans;

import logic.entity.ContainerType;
import logic.entity.Volume;

public class BuyBeer_Bean {
	public void buyProduct(Product_Bean product_Bean) {
		String beerId = product_Bean.getBeerId();
		ContainerType containerType = product_Bean.getContainerType();
		Volume volume = new Volume(product_Bean.getContainerVolume());
		
	}
}
