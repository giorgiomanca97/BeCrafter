package error;

public class ProductNotFoundException extends Exception{
private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException() {
		super();
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(Throwable throwable) {
		super(throwable);
	}
}
