package cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({ InvalidParameterException.class })
    protected ResponseEntity handleCustomException(InvalidParameterException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
