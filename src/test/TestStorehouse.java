package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.entity.RawMaterial;
import logic.entity.RawMaterialType;
import logic.entity.Storehouse;

public class TestStorehouse {

	@Test
	public void testAddSameProduct() {
		Storehouse storehouse = new Storehouse();
		RawMaterial wheat1 = new RawMaterial(RawMaterialType.WHEAT);
		RawMaterial wheat2 = new RawMaterial(RawMaterialType.WHEAT);
		wheat1.addQuantity(10);
		wheat2.addQuantity(20);
		storehouse.add(wheat1);
		storehouse.add(wheat2);
		
		assertEquals(30, storehouse.get(RawMaterialType.WHEAT).getQuantity());
	}
	
	@Test
	public void testAddDifferentProduct() {
		Storehouse storehouse = new Storehouse();
		RawMaterial wheat = new RawMaterial(RawMaterialType.WHEAT);
		RawMaterial hop = new RawMaterial(RawMaterialType.HOP);
		wheat.addQuantity(10);
		hop.addQuantity(20);
		storehouse.add(wheat);
		storehouse.add(hop);
		
		Object[] expecteds = {10, 20};
		Object[] actuals = {storehouse.get(RawMaterialType.WHEAT).getQuantity(), storehouse.get(RawMaterialType.HOP).getQuantity()};
		
		assertArrayEquals(expecteds, actuals);
	}

}
