package error;

public class StorableIllegalQuantityException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public StorableIllegalQuantityException() {
		super();
	}
	
	public StorableIllegalQuantityException(String message) {
		super(message);
	}
	
	public StorableIllegalQuantityException(Throwable throwable) {
		super(throwable);
	}
}
