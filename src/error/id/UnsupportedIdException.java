package error.id;

public class UnsupportedIdException extends IdException {
	private static final long serialVersionUID = 1L;

	public UnsupportedIdException() {
		super();
	}
	
	public UnsupportedIdException(String message) {
		super(message);
	}
	
	public UnsupportedIdException(Throwable throwable) {
		super(throwable);
	}
}
