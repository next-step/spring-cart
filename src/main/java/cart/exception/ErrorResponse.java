package cart.exception;

public class ErrorResponse {
	private final int errorStatus;

	private final String errorMessage;

	public ErrorResponse(int errorStatus, String errorMessage) {
		this.errorStatus = errorStatus;
		this.errorMessage = errorMessage;
	}

}
