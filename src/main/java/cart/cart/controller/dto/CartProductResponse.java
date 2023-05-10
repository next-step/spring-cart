package cart.cart.controller.dto;

import cart.product.domain.Product;
import cart.product.domain.Products;

import java.math.BigDecimal;

public class CartProductResponse {

    private Integer id;
    private String productName;
    private String imageUrl;
    private BigDecimal price;

    private CartProductResponse() {
    }

    private CartProductResponse(Integer id, String productName, String imageUrl, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public static CartProductResponse of(Product product) {
        return new CartProductResponse(
                product.getId().intValue(),
                product.getName(),
                product.getImage(),
                product.price()
        );
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
