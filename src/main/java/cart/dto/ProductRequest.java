package cart.dto;

import cart.domain.Product;

public class ProductRequest {

    private String name;
    private String imageUrl;
    private int price;

    public ProductRequest() {
    }

    public ProductRequest(String name, String imageUrl, int price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product toEntity() {
        return new Product(name, imageUrl, price);
    }

    public Product toEntity(Long id) {
        return new Product(id, name, imageUrl, price);
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }
}
