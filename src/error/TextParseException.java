package error;

public class TextParseException extends Exception{
	private static final long serialVersionUID = 1L;

	public TextParseException() {
		super();
	}
	
	public TextParseException(String message) {
		super(message);
	}
	
	public TextParseException(Throwable throwable) {
		super(throwable);
	}
}
