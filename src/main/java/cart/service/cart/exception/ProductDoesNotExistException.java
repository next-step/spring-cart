package cart.service.cart.exception;

public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException() {
        super();
    }

    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
