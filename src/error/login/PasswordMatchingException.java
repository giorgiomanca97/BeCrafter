package error.login;

public class PasswordMatchingException extends LoginException{
	private static final long serialVersionUID = 1L;

	public PasswordMatchingException() {
		super();
	}
	
	public PasswordMatchingException(String message) {
		super(message);
	}
	
	public PasswordMatchingException(Throwable throwable) {
		super(throwable);
	}
}
