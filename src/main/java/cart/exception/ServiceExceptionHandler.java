package cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler {
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> handleExceptionHandler(ServiceException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.errorCode(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(errorResponse);
	}
}
