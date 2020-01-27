package error.login;

public class LoginException extends Exception{
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super();
	}
	
	public LoginException(String message) {
		super(message);
	}
	
	public LoginException(Throwable throwable) {
		super(throwable);
	}
}
