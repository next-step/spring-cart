package cart.exception;

public class ServiceException extends RuntimeException {

	private final ErrorType errorType;

	public ServiceException(ErrorType errorType) {
		this.errorType = errorType;
	}

	public String errorCode() {
		return this.errorType.name();
	}
}
