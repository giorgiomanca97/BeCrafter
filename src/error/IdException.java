package error;

public class IdException extends Exception{
	private static final long serialVersionUID = 1L;

	public IdException() {
		super();
	}
	
	public IdException(String message) {
		super(message);
	}
	
	public IdException(Throwable throwable) {
		super(throwable);
	}
}
