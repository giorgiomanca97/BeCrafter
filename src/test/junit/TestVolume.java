package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.entity.Volume;

public class TestVolume {

	@Test
	public void testVolumeConversion() {
		int volume1 = 60;
		int volume2 = 10000;
		int volume3 = 150;
		
		String expected1 = "60 cl";
		String expected2 = "100.0 l";
		String expected3 = "1.5 l";
		
		String actual1 = Volume.toText(volume1);
		String actual2 = Volume.toText(volume2);
		String actual3 = Volume.toText(volume3);
		
		String[] expecteds = {expected1, expected2, expected3};
		String[] actuals = {actual1, actual2, actual3};
		
		assertArrayEquals(expecteds, actuals);
	}
}
