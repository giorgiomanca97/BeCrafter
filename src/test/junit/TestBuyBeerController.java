package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.ProductNotFoundException;
import error.StorableIllegalQuantityException;
import logic.bean.BuyBeerBean;
import logic.bean.CheckoutSummaryBean;
import logic.entity.ContainerType;

public class TestBuyBeerController {

	// Giorgio Manca 0239067
	@Test
	public void testAddProductToCart() {
		String message = "";
		String expectedBeerId = "B001";
		ContainerType expectedContainerType = ContainerType.BOTTLE;
		int expectedVolume = 50;
		
		String actualBeerId = "";
		ContainerType actualContainerType = null;
		int actualVolume = 0;
		
		BuyBeerBean bbBean = new BuyBeerBean();
		CheckoutSummaryBean csBean = new CheckoutSummaryBean();
		
		bbBean.setBeerId(expectedBeerId);
		bbBean.setContainerType(expectedContainerType);
		bbBean.setContainerVolume(expectedVolume);
		
		try {
			bbBean.selectProductForSale();
			bbBean.loadSelectedProduct();
			bbBean.setQuantity(10);
			bbBean.addProductToCart();
			
			csBean.selectProductInCart(0);
			csBean.loadSelectedProduct();
		} catch (ProductNotFoundException e) {
			message = "Product Not Found";
		} catch (StorableIllegalQuantityException e) {
			message = "Storable Illegal Quantity";
		}
			
		actualBeerId = csBean.getBeerId();
		actualContainerType = csBean.getContainerType();
		actualVolume = csBean.getContainerVolume();
		
		Object[] expecteds = {expectedBeerId, expectedContainerType, expectedVolume};
		Object[] actuals = {actualBeerId, actualContainerType, actualVolume};
		
		assertArrayEquals(message, expecteds, actuals);
	}
	
	
}
