package error.login;

public class InvalidPasswordException extends LoginException {
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super();
	}
	
	public InvalidPasswordException(String message) {
		super(message);
	}
	
	public InvalidPasswordException(Throwable throwable) {
		super(throwable);
	}
}
