package logic.designclasses;


import error.id.IdException;
import error.id.OutOfRangeIdException;
import error.id.UnsupportedIdException;

public class IdConverterTest {
	private IdConverterTest() {
		
	}
	
	
	public static Type getTypeOf(String id) throws UnsupportedIdException {
		String identifier = id.substring(0,1);
		
		for (Type t : IdConverterTest.Type.values()) {
			if(identifier.equals(t.getIdentifier())) {
				return t;
			}
		}
		
		throw new UnsupportedIdException();
	}
	
	public static String intToId(int value, Type type) throws OutOfRangeIdException {
		if(value > type.maxValue()) {
			throw new OutOfRangeIdException();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		String number = String.valueOf(value);
		
		stringBuilder.append(type.getIdentifier());
		for (int i = 0; i < type.getDigits() - number.length(); i++) {
			stringBuilder.append(0);
		}
		stringBuilder.append(number);
		
		return stringBuilder.toString();
	}
	
	public static int idToInt(String id) throws IdException, UnsupportedIdException, OutOfRangeIdException {		
		Type type = getTypeOf(id);
		
		try {
			int value = Integer.valueOf(id.substring(1));
			
			if(value > type.maxValue()) {
				throw new OutOfRangeIdException();
			}
			
			return value;
		} catch (NumberFormatException nfe) {
			throw new IdException(nfe);
		}
	}
	
	public static String nextId(String id) throws IdException, UnsupportedIdException, OutOfRangeIdException {
		int value = idToInt(id);
		Type type = getTypeOf(id);
		
		return intToId(value + 1, type);
	}
	
	
	public enum Type{
		BEER("B",3),
		RECIPE("R", 3),
		ORDER("O", 6);
		
		private String identifier;
		private int digits;
		
		private Type(String identifier, int digits) {
			this.identifier = identifier;
			this.digits = digits;
		}
		
		public String getIdentifier() {
			return this.identifier;
		}
		
		public int getDigits() {
			return this.digits;
		}
		
		public int maxValue() {
			return (int) Math.pow(10, this.digits) - 1;
		}
	}
}
