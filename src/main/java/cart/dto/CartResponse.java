package cart.dto;

import cart.domain.Cart;

import java.util.List;
import java.util.stream.Collectors;

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

    public static CartResponse of(Cart cart) {
        return new CartResponse(cart.getId(), cart.getMember().getId(), ProductResponse.of(cart.getProduct()));
    }

    public static List<CartResponse> lisfOf(List<Cart> carts) {
        return carts.stream()
            .map(CartResponse::of)
            .collect(Collectors.toList());
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
