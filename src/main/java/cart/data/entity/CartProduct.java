package cart.data.entity;

import cart.business.domain.Product;
import cart.presentation.dto.RequestProduct;

import java.time.LocalDateTime;

public class CartProduct {

    private Long productId;
    private String productName;
    private int productPrice;
    private String productImageUrl;
    private LocalDateTime createdAt = LocalDateTime.now();

    public CartProduct(Product product) {
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.productImageUrl = product.getImageUrl();
    }

    public CartProduct(Long productId, String productName, int productPrice, String productImageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void modifyProduct(RequestProduct request) {
        this.productName = request.getName();
        this.productPrice = request.getPrice();
        this.productImageUrl = request.getImageUrl();
    }
}
