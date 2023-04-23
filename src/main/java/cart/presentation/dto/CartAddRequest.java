package cart.presentation.dto;

import cart.domain.CartEntity;

public class CartAddRequest {
    private Long productId;

    public CartAddRequest() {
    }

    public CartAddRequest(Long productId) {
        this.productId = productId;
    }

    public CartEntity toEntity(Long memberId) {
        return new CartEntity(this.productId, memberId);
    }

    public Long getProductId() {
        return productId;
    }
}
