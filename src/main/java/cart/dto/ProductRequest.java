package cart.dto;

import cart.domain.Product;

public class ProductRequest {

    private String name;
    private String imageUrl;
    private Long price;

    public ProductRequest() {
    }

    public ProductRequest(String name, String imageUrl, Long price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product toEntity() {
        return new Product(name, imageUrl, price);
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
