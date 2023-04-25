package cart.cartitem.web.request;

import cart.cartitem.application.dto.CartItemAddData;

public class CartItemAddRequest {
    private Long productId;

    public CartItemAddRequest() {
    }

    public CartItemAddRequest(Long productId) {
        this.productId = productId;
    }

    public CartItemAddData toCartItemAddDataWithUserId(Long userId) {
        return new CartItemAddData(userId, productId);
    }

    public Long getProductId() {
        return productId;
    }
}
