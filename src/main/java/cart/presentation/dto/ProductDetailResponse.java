package cart.presentation.dto;

import cart.domain.ProductEntity;

public class ProductDetailResponse {
    private final Long id;
    private final String name;
    private final String imageUrl;
    private final Long price;

    public ProductDetailResponse(Long id, String name, String imageUrl, Long price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public static ProductDetailResponse from(ProductEntity productEntity) {
        return new ProductDetailResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getPrice() {
        return price;
    }
}
