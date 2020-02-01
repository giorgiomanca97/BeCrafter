package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import logic.bean.BuyBeer_Bean;
import logic.bean.CheckoutSummary_Bean;
import logic.entity.ContainerType;

public class TestBuyBeer_Controller {

	// Giorgio Manca 0239067
	@Test
	public void testAddProductToCart() {
		String message = "";
		String expected_beerId = "B001";
		ContainerType expected_containerType = ContainerType.BOTTLE;
		int expected_volume = 50;
		
		String actual_beerId = "";
		ContainerType actual_containerType = null;
		int actual_volume = 0;
		
		BuyBeer_Bean bb_Bean = new BuyBeer_Bean();
		CheckoutSummary_Bean cs_Bean = new CheckoutSummary_Bean();
		
		bb_Bean.setBeerId(expected_beerId);
		bb_Bean.setContainerType(expected_containerType);
		bb_Bean.setContainerVolume(expected_volume);
		
		try {
			bb_Bean.selectProductForSale();
			bb_Bean.loadSelectedProduct();
			bb_Bean.setQuantity(10);
			bb_Bean.addProductToCart();
			
			cs_Bean.selectProductInCart(0);
			cs_Bean.loadSelectedProduct();
		} catch (ProductNotFoundException e) {
			message = "Product Not Found";
		} catch (StorableIllegalQuantityException e) {
			message = "Storable Illegal Quantity";
		}
			
		actual_beerId = cs_Bean.getBeerId();
		actual_containerType = cs_Bean.getContainerType();
		actual_volume = cs_Bean.getContainerVolume();
		
		Object[] expecteds = {expected_beerId, expected_containerType, expected_volume};
		Object[] actuals = {actual_beerId, actual_containerType, actual_volume};
		
		assertArrayEquals(message, expecteds, actuals);
	}
	
	
}
