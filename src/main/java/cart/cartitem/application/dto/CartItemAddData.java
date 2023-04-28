package cart.cartitem.application.dto;

public class CartItemAddData {
    private final Long userId;
    private final Long productId;

    public CartItemAddData(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }
}
