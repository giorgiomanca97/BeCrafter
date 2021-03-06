package logic.designclasses;


import error.id.IdException;
import error.id.OutOfRangeIdException;
import error.id.UnsupportedIdException;

public class IdConverter {
	
	private IdConverter() {
		
	}
	
	
	public static Type getTypeOf(String id) throws UnsupportedIdException {
		if(id.length() != 0) {
			String identifier = id.substring(0,1);
			
			for (Type t : IdConverter.Type.values()) {
				if(identifier.equals(t.getIdentifier())) {
					return t;
				}
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
		try {
			Type type = getTypeOf(id);
			
			if(id.length() != (1 + type.getDigits())) {
				throw new UnsupportedIdException();
			}
			
			int value = Integer.parseUnsignedInt(id.substring(1, id.length()));
			
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
	
	public static boolean isIdValid(String id, Type type) {
		try {
			Type t = getTypeOf(id);
			if(t == type) {
				idToInt(id);
				return true;
			} else {
				return false;
			}
		} catch (IdException ie) {
			return false;
		}
	}
	
	
	public enum Type{
		BEER("B",3),
		RECIPE("R", 3),
		ORDER("X", 6);
		
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
