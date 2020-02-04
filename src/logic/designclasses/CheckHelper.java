package logic.designclasses;


public class CheckHelper {
	private static String invalidEmailChars = "',:;!?^*+/%[](){}";
	
	private CheckHelper() {
		
	}
	
	public static boolean isValidEmail(String string) {
		if(string.length() == 0) {
			return false;
		}
		
		// Controllo caratteri invalidi
		for(int i = 0; i < invalidEmailChars.length(); i++) {
			if(string.contains(String.valueOf(invalidEmailChars.charAt(i)))) {
				return false;
			}
		}
		
		// Troppe chiocciole
		int atCounter = 0;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if(c == '@') {
				atCounter++;
			}
		}
		if(atCounter != 1) {
			return false;
		}
		
		String[] pieces = string.split("@");
		if(pieces.length == 2) {
			// Controllo lunghezza parte del nome
			String user = string.split("@")[0];
			if(user.length() == 0) {
				return false;
			}

			// Controllo dominio
			String domain = string.split("@")[1];
			if(domain.length() == 0 || !domain.contains(".") || domain.charAt(domain.length()-1) == '.') {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public static boolean isValidPassword(String string) {
		// Controllo lunghezza password
		if(string.length() < 8) {
			return false;
		}
		
		// Controllo requisiti password
		int digitChat = 0;
		int lowerChar = 0;
		int upperChar = 0;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			
			if(Character.isDigit(c)) {
				digitChat++;
			}
			if(Character.isLowerCase(c)) {
				lowerChar++;
			}
			if(Character.isUpperCase(c)) {
				upperChar++;
			}
		}
		
		return (digitChat > 0 && lowerChar > 0 && upperChar > 0);
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
