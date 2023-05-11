package cart.cartitem.web.dto;

import cart.cartitem.application.dto.CartItemInfomation;

public class CartItemResponse {

    private final Long id;
    private final String productName;
    private final String productImage;
    private final Long price;

    public CartItemResponse(Long id, String productName, String productImage, Long price) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
    }

    public static CartItemResponse from(CartItemInfomation cartItemInfomation) {
        return new CartItemResponse(cartItemInfomation.getId(), cartItemInfomation.getProductName(),
            cartItemInfomation.getProductImage(), cartItemInfomation.getPrice());
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
