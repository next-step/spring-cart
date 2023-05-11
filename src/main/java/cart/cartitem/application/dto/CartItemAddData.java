package cart.cartitem.application.dto;

import cart.cartitem.domain.CartItem;

public class CartItemAddData {

    private final Long productId;
    private final Long memberId;

    public CartItemAddData(Long productId, Long memberId) {
        this.productId = productId;
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public CartItem toCartItem() {
        return new CartItem(null, productId, memberId);
    }
}
