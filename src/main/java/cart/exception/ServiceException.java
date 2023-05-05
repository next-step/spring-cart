package cart.exception;

public class ServiceException extends RuntimeException {

    private final ErrorType errorType;

    public ServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public String errorCode() {
        return errorType.name();
    }

}
