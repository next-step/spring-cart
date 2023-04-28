package cart.cartitem.domain;

import java.util.Objects;

public class CartItem {
    private final CartItemUserId userId;
    private final CartItemProductId productId;
    private final CartItemProductName productName;
    private final CartItemProductImage productImage;
    private final CartItemProductPrice productPrice;

    public CartItem(
            Long userId,
            Long productId,
            String productName,
            String productImage,
            Long productPrice
    ) {
        this(
                new CartItemUserId(userId),
                new CartItemProductId(productId),
                new CartItemProductName(productName),
                new CartItemProductImage(productImage),
                new CartItemProductPrice(productPrice)
        );
    }

    public CartItem(CartItemUserId userId, CartItemProductId productId, CartItemProductName productName, CartItemProductImage productImage, CartItemProductPrice productPrice) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public CartItemUserId getUserId() {
        return userId;
    }

    public String getProductNameValue() {
        return productName.getValue();
    }

    public String getProductImageValue() {
        return productImage.getValue();
    }

    public Long getProductPriceValue() {
        return productPrice.getLongValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(userId, cartItem.userId) && Objects.equals(productId, cartItem.productId) && Objects.equals(productName, cartItem.productName) && Objects.equals(productImage, cartItem.productImage) && Objects.equals(productPrice, cartItem.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId, productName, productImage, productPrice);
    }
}
