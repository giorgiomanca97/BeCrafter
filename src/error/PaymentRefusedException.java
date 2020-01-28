package error;

public class PaymentRefusedException extends Exception {
	private static final long serialVersionUID = 1L;

	public PaymentRefusedException() {
		super();
	}
	
	public PaymentRefusedException(String message) {
		super(message);
	}
	
	public PaymentRefusedException(Throwable throwable) {
		super(throwable);
	}
}
