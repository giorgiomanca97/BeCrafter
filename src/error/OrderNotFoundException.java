package error;

public class OrderNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException() {
		super();
	}
	
	public OrderNotFoundException(String message) {
		super(message);
	}
	
	public OrderNotFoundException(Throwable throwable) {
		super(throwable);
	}
}
