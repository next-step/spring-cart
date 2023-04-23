package cart.presentation.dto;

import cart.repository.ProductEntity;

public class ProductUpdateRequest {
    private final String name;
    private final String imageUrl;
    private final Long price;

    public ProductUpdateRequest(String name, String imageUrl, Long price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductEntity toEntity() {
        return new ProductEntity(this.name, this.imageUrl, this.price);
    }
}
