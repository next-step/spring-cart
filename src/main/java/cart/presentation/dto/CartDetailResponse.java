package cart.presentation.dto;

import cart.domain.CartEntity;
import cart.domain.ProductEntity;

public class CartDetailResponse {
    private final Long id;
    private final String cartItemName;
    private final String cartItemImageUrl;
    private final Long cartItemPrice;

    public CartDetailResponse(Long id, String cartItemName, String cartItemImageUrl, Long cartItemPrice) {
        this.id = id;
        this.cartItemName = cartItemName;
        this.cartItemImageUrl = cartItemImageUrl;
        this.cartItemPrice = cartItemPrice;
    }

    public static CartDetailResponse of(CartEntity cart, ProductEntity product) {
        return new CartDetailResponse(
                cart.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getPrice()
        );
    }

    public Long getId() {
        return id;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public String getCartItemImageUrl() {
        return cartItemImageUrl;
    }

    public Long getCartItemPrice() {
        return cartItemPrice;
    }
}
