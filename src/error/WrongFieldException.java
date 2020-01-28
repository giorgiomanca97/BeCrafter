package error;

public class WrongFieldException extends Exception{
	private static final long serialVersionUID = 1L;

	public WrongFieldException() {
		super();
	}
	
	public WrongFieldException(String message) {
		super(message);
	}
	
	public WrongFieldException(Throwable throwable) {
		super(throwable);
	}
}
