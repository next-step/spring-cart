package cart.exception;

public class ProductException extends IllegalArgumentException {

    public ProductException() {
        super("Product exception");
    }

    public ProductException(String msg) {
        super(msg);
    }
}
