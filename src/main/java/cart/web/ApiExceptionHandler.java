package cart.web;

import cart.infrastructure.security.AccessDeniedException;
import cart.infrastructure.security.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, AuthenticationException.class})
    public String badRequestExceptionHandler(RuntimeException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public String unauthorizedExceptionHandler(AccessDeniedException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String defaultExceptionHandler(RuntimeException exception) {
        return "알 수 없는 오류가 발생했습니다. 관리자에게 문의주세요!";
    }

}
