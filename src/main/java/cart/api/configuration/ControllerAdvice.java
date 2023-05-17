package cart.api.configuration;

import cart.domain.exception.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException() {
        return ResponseEntity.badRequest().body("해당 상품이 존재하지 않습니다.");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 정보가 올바르지 않습니다.");
    }
}
