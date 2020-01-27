package error.login;

public class InexistentEmailException extends LoginException {
	private static final long serialVersionUID = 1L;

	public InexistentEmailException() {
		super();
	}
	
	public InexistentEmailException(String message) {
		super(message);
	}
	
	public InexistentEmailException(Throwable throwable) {
		super(throwable);
	}
}
