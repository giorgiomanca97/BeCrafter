package error;

public class IllegalCharacterException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IllegalCharacterException() {
		super();
	}
	
	public IllegalCharacterException(String message) {
		super(message);
	}
	
	public IllegalCharacterException(Throwable throwable) {
		super(throwable);
	}
}
