package logic.designclasses;

public class CheckHelper {
	private CheckHelper() {
		
	}
	
	
	public static boolean isValidString(String string) {
		for(int i = 0; i < string.length(); i++){
			char c = string.charAt(i);
			if(!Character.isLetter(c) && !Character.isDigit(c) && c != ',' && c != ' ') {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isOnlyLetters(String string) {
		for(int i = 0; i < string.length(); i++){
			char c = string.charAt(i);
			if(!Character.isLetter(c)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isOnlyDigits(String string) {
		for(int i = 0; i < string.length(); i++){
			char c = string.charAt(i);
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isValidPhoneNumber(String string) {
		if(string.length() == 0 || (string.charAt(0) != '+' && !Character.isDigit(string.charAt(0)))) {
			return false;
		}
		
		for(int i = 1; i < string.length(); i++){
			char c = string.charAt(i);
			if(!Character.isDigit(c) && c != ' ') {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isValidCreditCard(String card) {
		String[] pieces = card.split("-");
		
		if(pieces.length != 4) {
			return false;
		}
		
		for (String string : pieces) {
			if(string.length() != 4 || !isOnlyDigits(string)) {
				return false;
			}
		}
		
		return true;
	}
}
