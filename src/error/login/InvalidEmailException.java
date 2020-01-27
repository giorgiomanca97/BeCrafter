package error.login;

public class InvalidEmailException extends LoginException {
	private static final long serialVersionUID = 1L;

	public InvalidEmailException() {
		super();
	}
	
	public InvalidEmailException(String message) {
		super(message);
	}
	
	public InvalidEmailException(Throwable throwable) {
		super(throwable);
	}
}
