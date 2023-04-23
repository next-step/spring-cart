package cart.presentation.dto;

import cart.domain.ProductEntity;

public class ProductCreateRequest {
    private final String name;
    private final String imageUrl;
    private final long price;

    public ProductCreateRequest(String name, String imageUrl, long price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductEntity toEntity() {
        return new ProductEntity(this.name, this.imageUrl, this.price);
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getPrice() {
        return price;
    }
}
