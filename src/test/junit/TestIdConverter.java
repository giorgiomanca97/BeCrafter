package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import error.id.IdException;
import error.id.OutOfRangeIdException;
import error.id.UnsupportedIdException;
import logic.designclasses.IdConverter;

public class TestIdConverter {

	// Giorgio Manca 0239067
	@Test
	public void testIntToId() {
		String message = "";
		IdConverter.Type selectedType = IdConverter.Type.ORDER;
		
		String expectedId = "X000010";
		String actualId = "";

		
		try {
			actualId = IdConverter.intToId(10, selectedType);
		} catch (OutOfRangeIdException e) {
			message = "Out Of Range Id";
		}
		
		assertEquals(message, expectedId, actualId);
	}
	
	// Davide Bianchi 0228110
	@Test
	public void testIdToInt() {
		String message = "";
		String selectedId = "X000010";
		
		int expectedInt = 10;
		int actualInt = 0;

		
		try {
			actualInt = IdConverter.idToInt(selectedId);
		} catch (UnsupportedIdException e) {
			message = "Unsupported Id";
		} catch (OutOfRangeIdException e) {
			message = "Out Of Range Id";
		} catch (IdException e) {
			message = "Id error";
		}
		
		assertEquals(message, expectedInt, actualInt);
	}

}
