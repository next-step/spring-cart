package cart.cartitem.application.dto;

import cart.cartitem.domain.CartItemEntity;

public class CartItemInformation {
    private final Long id;
    private final String productName;
    private final String productImage;
    private final Long productPrice;

    public CartItemInformation(Long id, String productName, String productImage, Long productPrice) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public static CartItemInformation fromCartItemEntity(CartItemEntity cartItemEntity) {
        return new CartItemInformation(
                cartItemEntity.getIdValue(),
                cartItemEntity.getProductNameValue(),
                cartItemEntity.getProductImageValue(),
                cartItemEntity.getProductPriceValue()
        );
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public Long getProductPrice() {
        return productPrice;
    }
}
