package cart.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import static cart.exception.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> applicationHandler(ConstraintViolationException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(NOT_VALID_PATH.getStatus().value(), NOT_VALID_PATH.getMessage()));
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> applicationHandler(RuntimeException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(JwpCartApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationHandler(JwpCartApplicationException e) {
        log.error("Error occurs {}", e.toString());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getStatus().value(), e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getStatus().value())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidErrorResponse> processValidationError(MethodArgumentNotValidException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(BAD_REQUEST).body(new ValidErrorResponse(e.getFieldErrors().stream().map(fieldError -> new ErrorResponse(BAD_REQUEST.value(), fieldError.getDefaultMessage())).collect(Collectors.toList())));
    }

}
