package error;

public class IllegalFieldException extends Exception{
	private static final long serialVersionUID = 1L;

	public IllegalFieldException() {
		super();
	}
	
	public IllegalFieldException(String message) {
		super(message);
	}
	
	public IllegalFieldException(Throwable throwable) {
		super(throwable);
	}
}
