package test;

import static org.junit.Assert.*;

import org.junit.Test;

import error.id.OutOfRangeIdException;
import logic.designclasses.IdConverter;
import logic.entity.Volume;

public class TestVolume {

	@Test
	public void test60clToString() {
		assertEquals("60 cl", Volume.toText(60));
	}
	
	@Test
	public void test10000clToString() {
		assertEquals("100.0 l", Volume.toText(10000));
	}
	
	@Test
	public void test100clToString() {
		assertEquals("1.0 l", Volume.toText(100));
	}

}
