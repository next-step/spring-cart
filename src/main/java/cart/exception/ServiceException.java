package cart.exception;


public class ServiceException extends RuntimeException {
	private final ErrorType errorType;

	public ServiceException(ErrorType errorType) {
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}

