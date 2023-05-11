package cart.cartitem.application.dto;

import cart.cartitem.domain.CartItemWithProduct;

public class CartItemInfomation {

    private final Long id;
    private final String productName;
    private final String productImage;
    private final Long price;

    public CartItemInfomation(Long id, String productName, String productImage, Long price) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
    }

    public static CartItemInfomation from(CartItemWithProduct cartItemWithProduct) {
        return new CartItemInfomation(cartItemWithProduct.getId(),
            cartItemWithProduct.getProductName(),
            cartItemWithProduct.getProductImage(),
            cartItemWithProduct.getProductPrice());
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

    public Long getPrice() {
        return price;
    }
}
