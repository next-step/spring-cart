package cart.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "해당 권한이 없습니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "해당 정보를 찾을 수 없습니다.");
	private final HttpStatus errorStatus;
	private final String errorMessage;

	ErrorType(HttpStatus errorStatus, String errorMessage) {
		this.errorStatus = errorStatus;
		this.errorMessage = errorMessage;
	}

	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

	public int getStatusValue() {
		return errorStatus.value();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
