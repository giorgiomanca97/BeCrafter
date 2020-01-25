package error;

public class OutOfRangeIdException extends IdException {
	private static final long serialVersionUID = 1L;

	public OutOfRangeIdException() {
		super();
	}
	
	public OutOfRangeIdException(String message) {
		super(message);
	}
	
	public OutOfRangeIdException(Throwable throwable) {
		super(throwable);
	}
}
