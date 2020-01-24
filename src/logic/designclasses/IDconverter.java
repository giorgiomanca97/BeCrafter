package logic.designclasses;

public class IDconverter {
	private IDconverter() {
		
	}
	
	
	public static String intToId(int value, Type type) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(type.getValue());
		
		String string = String.valueOf(value);
		
		for (int i = 0; i < type.getDigits() - string.length(); i++) {
			stringBuilder.append(0);
		}
		stringBuilder.append(string);
		
		return stringBuilder.toString();
	}
	
	public static int idToInt(String id) {
		return Integer.valueOf(id.substring(1));
	}
	
	
	public enum Type{
		BEER("B",3),
		RECIPE("R", 3),
		ORDER("O", 6);
		
		private String value;
		private int digits;
		
		private Type(String value, int digits) {
			this.value = value;
			this.digits = digits;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public int getDigits() {
			return this.digits;
		}
	}
}
