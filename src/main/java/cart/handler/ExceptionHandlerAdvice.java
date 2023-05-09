package cart.handler;

import cart.enums.exceptions.ValidCode;
import cart.enums.exceptions.dtos.response.ErrorResponse;
import cart.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleException(BadRequestException exception) {
        ErrorResponse response = new ErrorResponse(exception.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> validHandleException(MethodArgumentNotValidException exception) {
        ErrorResponse response = makeValidationErrorResponse(exception.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    public ErrorResponse makeValidationErrorResponse(BindingResult bindingResult) {
        ErrorResponse response = new ErrorResponse();

        if (bindingResult.hasErrors()) {
            String annotationErrorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();//anotation 위에 직접 쓴 message
            String code = bindingResult.getFieldError().getCode();

            switch (Objects.requireNonNull(code).toLowerCase()) {
                case "notnull" :
                    response.initErrorMessage(ValidCode.NOT_NULL.getStatus(), annotationErrorMessage, ValidCode.NOT_NULL.getCode());
                    break;
                case "notblank":
                    response.initErrorMessage(ValidCode.NOT_BLANK.getStatus(), annotationErrorMessage, ValidCode.NOT_BLANK.getCode());
                    break;
                case "pattern":
                    response.initErrorMessage(ValidCode.PATTERN.getStatus(), annotationErrorMessage, ValidCode.PATTERN.getCode());
                    break;
                case "min":
                    response.initErrorMessage(ValidCode.MIN_VALUE.getStatus(), annotationErrorMessage, ValidCode.MIN_VALUE.getCode());
                    break;
                case "max":
                    response.initErrorMessage(ValidCode.MAX_VALUE.getStatus(), annotationErrorMessage, ValidCode.MAX_VALUE.getCode());
                    break;

            }
        }

        return response;
    }
}
