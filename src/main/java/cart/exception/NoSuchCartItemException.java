package cart.exception;

public class NoSuchCartItemException extends RuntimeException {
    public NoSuchCartItemException() {
        super("존재하지 않는 장바구니 상품입니다.");
    }
}
