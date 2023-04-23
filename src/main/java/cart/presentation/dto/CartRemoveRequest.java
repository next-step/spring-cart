package cart.presentation.dto;

public class CartRemoveRequest {
    private final Long cartItemId;

    public CartRemoveRequest(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }
}
