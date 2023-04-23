package cart.exception;

public class CartException extends IllegalArgumentException {

    public CartException() {
        super("Cart exception");
    }

    public CartException(String msg) {
        super(msg);
    }
}
