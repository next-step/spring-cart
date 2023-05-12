package cart.exception.handler.dto;

public class ErrorResponse {

    private String errorCode;
    private String message;

    private ErrorResponse() {
    }

    public ErrorResponse(String errorCode, String message) {

        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
