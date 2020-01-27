package error.login;

public class UsedEmailException extends LoginException{
	private static final long serialVersionUID = 1L;

	public UsedEmailException() {
		super();
	}
	
	public UsedEmailException(String message) {
		super(message);
	}
	
	public UsedEmailException(Throwable throwable) {
		super(throwable);
	}
}
