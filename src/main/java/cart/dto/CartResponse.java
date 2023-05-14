package cart.dto;

import cart.domain.Cart;
import cart.domain.Product;

public class CartResponse {

    private Long id;
    private Long memberId;
    private ProductResponse product;

    public CartResponse() {
    }

    public CartResponse(Long id, Long memberId, ProductResponse product) {
        this.id = id;
        this.memberId = memberId;
        this.product = product;
    }

    public static CartResponse of(Cart cart, Product product) {
        return new CartResponse(cart.getId(), cart.getMemberId(), ProductResponse.of(product));
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public ProductResponse getProduct() {
        return product;
    }
}
